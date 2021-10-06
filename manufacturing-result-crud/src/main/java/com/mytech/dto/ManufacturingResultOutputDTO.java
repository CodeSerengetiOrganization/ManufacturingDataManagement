package com.mytech.dto;

import com.mytech.domain.ManufacturingResult;
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

    public static ManufacturingResultOutputDTO convertToDTO(ManufacturingResult manufacturingResult){
        ManufacturingResultOutputDTOConverter converter=new ManufacturingResultOutputDTOConverter();
        return converter.convert(manufacturingResult);
    }

    private static class ManufacturingResultOutputDTOConverter implements Converter<ManufacturingResult,ManufacturingResultOutputDTO>{

        @Override
        public ManufacturingResultOutputDTO convert(ManufacturingResult manufacturingResult) {
            ManufacturingResultOutputDTO outputDTO =new ManufacturingResultOutputDTO();
            BeanUtils.copyProperties(manufacturingResult,outputDTO);
            return outputDTO;
        }
    }
}//ManufacturingResultOutputDTO
