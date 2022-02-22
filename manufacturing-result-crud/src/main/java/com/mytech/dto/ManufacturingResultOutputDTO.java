package com.mytech.dto;

import com.mytech.domain.ComplexManufacturingResult;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper =true)
public class ManufacturingResultOutputDTO extends ManufacturingResultDTO {
    @Tolerate
    public ManufacturingResultOutputDTO(){}

    public static ManufacturingResultOutputDTO convertToDTO(ComplexManufacturingResult complexManufacturingResult){
        ManufacturingResultOutputDTOConverter converter=new ManufacturingResultOutputDTOConverter();
        return converter.convert(complexManufacturingResult);
    }

    private static class ManufacturingResultOutputDTOConverter implements Converter<ComplexManufacturingResult,ManufacturingResultOutputDTO>{

        @Override
        public ManufacturingResultOutputDTO convert(ComplexManufacturingResult complexManufacturingResult) {
            ManufacturingResultOutputDTO outputDTO =new ManufacturingResultOutputDTO();
            BeanUtils.copyProperties(complexManufacturingResult,outputDTO);
            return outputDTO;
        }
    }
}//ManufacturingResultOutputDTO
