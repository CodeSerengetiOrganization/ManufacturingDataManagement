package com.mytech.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString
public class ResultInFileLine{
    private String item;
    private Double value;
    private Boolean blResult;
}//ResultInFileLine