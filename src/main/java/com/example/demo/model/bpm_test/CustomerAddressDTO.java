package com.example.demo.model.bpm_test;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerAddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String customerId;

    private String type;

    private String address;

    private String apartmentNumber;

    private String province;

    private String district;

    private String ward;

    private String provinceName;

    private String districtName;

    private String wardName;

    private String accommodationType;

    private Integer lifetimeInYear;

    private Integer lifetimeInMonth;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private String createdDate;

    private String createdBy;

    private String updateBy;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private String updateDate;

}
