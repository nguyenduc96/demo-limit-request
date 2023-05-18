package com.example.demo.model.bpm_test;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisbursementInfoDTO implements Serializable {
    private Integer id;
    private Integer loansProfileId;
    private String customerId;
    private String disbursementType;
    private String accountNumber;
    private String accountName;
    private String bank;
    private String branchBank;
    private String mcReferenceNumber;
    private String remark;
    private String payPartnerCode;
    private String createdBy;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private String createdDate;
    private String updateBy;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private String updateDate;
    private String customerName;
    private String personId;
    private String phoneNumber;
    private String disbursementModel;
    private String smlCode;
    private String bankCode;
    private String bankName;
}
