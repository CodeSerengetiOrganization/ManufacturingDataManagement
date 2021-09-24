package com.mytech.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-17
 * @description :
 */
@Data
@SuperBuilder
public class ManufacturingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Integer id;

    @Column(name="f_barcode")
    @NotBlank(message = "barcode is blank(either null or empty)")
    private String barcode;


    @Column(name = "f_product_code")
    @NotNull(message = "productCode is null")
    private Integer productCode;

    @Column(name = "f_station_code")
    @NotNull(message = "stationCode is null")
    private Integer stationCode;

    @Column(name = "f_station_channel_no")
    @NotNull(message = "stationChannelNo is null")
    private Integer stationChannelNo;

    @Column(name = "f_feature_type")
    @NotBlank(message = "featureType is null")
    private String featureType;

    @Column(name = "f_feature_name")
    @NotBlank(message = "featureName is null")
    private String featureName;

    @Column(name = "f_test_item")
    @NotBlank(message = "testItem is null")
    private String testItem;

    @Column(name = "f_result")
    @NotNull(message = "manufacturing result value is null")
    private Double result;

    @Column(name = "f_operator")
    @NotBlank(message = "operator is blank(either null or empty)")
    private String operator;

    @Column(name = "f_start_time")
    @NotNull(message = "startTime is null")
    private LocalDateTime startTime;

    @Column(name = "f_end_time")
    @NotNull(message = "endTime is null")
    private LocalDateTime endTime;

    @Column(name="f_comment")
    private String comment;

    @Tolerate
    public ManufacturingResult(){}

}//class
