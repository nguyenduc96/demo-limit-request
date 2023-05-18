package com.example.demo.model.bpm_test;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerRequest implements Serializable {

    private String custName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private String custDob;
    private String custGender;
    private String listPhone;
    private String phone;
    private String custCareer;
    private String custEducation;
    private String custMaritalStatus;
    private String custEmail;
    private String incomeRange;
    private String custProfessional;
    private String tempSamePermAddress;
    private String companyName;
    private String custPosition;
}
