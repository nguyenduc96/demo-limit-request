package com.example.demo.model.bpm_test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String customerId;

    private String wardName;

    private String wardRatio3Months;

    private String wardRatioRange3Months;

    private String wardRatio1Month;

    private String wardRatioRange1Month;

    private String latitude;

    private String longitude;
}
