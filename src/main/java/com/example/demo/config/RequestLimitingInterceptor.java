package com.example.demo.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestLimitingInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS = 1; // Giới hạn số lượng request
    private static final int TIME_WINDOW = 60; // Khoảng thời gian tính bằng giây
    private Map<String, List<Long>> requestMap = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        long now = Instant.now().getEpochSecond();
        List<Long> requests;
        if (requestMap.containsKey(ipAddress)) {
            requests = requestMap.get(ipAddress);
            requests.removeIf(time -> time + TIME_WINDOW < now);
            if (requests.size() >= MAX_REQUESTS) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write(ipAddress + ": Too many requests to server!");
                return false;
            }
        } else {
            requests = new ArrayList<>();
        }
        requests.add(now);
        requestMap.put(ipAddress, requests);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}