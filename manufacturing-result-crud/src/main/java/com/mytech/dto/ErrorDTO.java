package com.mytech.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-09
 * @description :
 */
@Data
@Builder
@ToString
public class ErrorDTO {
    private Long errorCode;
    private String errorMessage;
    private LocalDateTime occurDateTime;
}
