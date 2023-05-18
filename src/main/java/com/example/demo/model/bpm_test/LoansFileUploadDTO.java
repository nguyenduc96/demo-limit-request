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
public class LoansFileUploadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer loansProfileId;
    private String customerId;
    private String type;
    private String url;
    private String name;
    private String sourceUpload;
    private String createdBy;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private String createdDate;

}
