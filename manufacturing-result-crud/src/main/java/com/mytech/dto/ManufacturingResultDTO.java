package com.mytech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@Data
@SuperBuilder
@AllArgsConstructor
public class ManufacturingResultDTO {
    @NotBlank(message = "barcode is blank(either null or empty)")
    public String barcode;


    @NotNull(message = "productCode is null")
    Integer productCode;

    @NotNull(message = "stationCode is null")
    Integer stationCode;

    @NotNull(message = "stationChannelNo is null")
    Integer stationChannelNo;

    @NotBlank(message = "featureType is null")
    String featureType;

    @NotBlank(message = "featureName is null")
    String featureName;

    @NotBlank(message = "testItem is null")
    String testItem;

    @NotNull(message = "manufacturing result value is null")
    Double result;

    @NotBlank(message = "operator is blank(either null or empty)")
    String operator;

    @NotNull(message = "startTime is null")
    LocalDateTime startTime;

    @NotNull(message = "endTime is null")
    LocalDateTime endTime;

    String comment;

    @Tolerate
    public ManufacturingResultDTO(){}
}
