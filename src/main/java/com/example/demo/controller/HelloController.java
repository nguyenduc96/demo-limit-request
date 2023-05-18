package com.example.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.example.demo.model.Customer;
import com.example.demo.model.bpm_test.CreateLoansRequest;
import com.example.demo.model.bpm_test.CustomerRelativesDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @PostMapping("/bpm")
    public ResponseEntity<?> testBPM(@RequestBody CreateLoansRequest request) {
        return ResponseEntity.ok(isCheckRelative(request));
    }

    private boolean isCheckRelative(CreateLoansRequest createLoansRequest) {
        boolean checkRelative = false;
        for(CustomerRelativesDTO customerRelativesReq : createLoansRequest.getCustomerRelativesRequests()) {
            if (ObjectUtils.isEmpty(customerRelativesReq.getRelativesPhone())) return false;
            if ("MARI02".equals(createLoansRequest.getCustomerRequest().getCustMaritalStatus())) {
                if (List.of("SPOUSE", "RELATIVES").contains(customerRelativesReq.getType())) {
                    checkRelative = true;
                } else return false;
            } else {
                if (List.of("REFERENCE_PERSON", "RELATIVES").contains(customerRelativesReq.getType())) {
                    checkRelative = true;
                }
            }
        }
        return checkRelative;
    }

    @GetMapping("/hello")
    public Map<String, String> printHello(@RequestParam(value = "name", required = false) String name) throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("value", "Hello " + name);

        return map;
    }


    @PostMapping("/customer")
    public ResponseEntity<?> getCust(@Valid @RequestBody Customer customer) {
        log.info(JSON.toJSONString(customer));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/get-mac")
    public String getMac(HttpServletRequest httpRequest) {
        try {
           httpRequest.getHeaderNames();
            List<String> strings = new ArrayList<>();
           String cmd = "ipconfig /all";
           Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Pattern pattern = Pattern.compile(".*Physical Addres.*: (.*)");
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    strings.add(matcher.group(1));
                }
            }
            if (!strings.isEmpty()) {
                return String.join("\n", strings.toArray(strings.toArray(new String[0])));
            }
        } catch (Exception ex) {
            return null;
        }
        return "Khong co gi";
    }

    @GetMapping("/get-ip-v4")
    public String getIp(HttpServletRequest request) {
        return getClientIpAddr(request);
    }

    public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-original-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    private String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

    private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }

        return result;
    }
}
