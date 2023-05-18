package com.example.demo.model.bpm_test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateLoansRequest implements Serializable {
    private String requestId;
    private Integer loansOfferId;
    private String sourceCreate;
    private String createBy;
    private String deviceId;
    private String locationDevice;
    private String partnerCode;
    private CustomerRequest customerRequest;
    private List<CustomerRelativesDTO> customerRelativesRequests;   //spouse
    private List<CustomerAddressDTO> customerAddressRequests;
    private List<IdentityPaperDTO> identityPaperRequests;
    private List<LoansFileUploadDTO> loansFileUploadRequests;
    private DisbursementInfoDTO disbursementInfoRequest;//0
    private CustomerLocationDTO locationRequest;//0
}
