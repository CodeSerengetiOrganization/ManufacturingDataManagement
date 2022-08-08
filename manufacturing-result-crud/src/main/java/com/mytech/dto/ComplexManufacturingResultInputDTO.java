package com.mytech.dto;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import javax.validation.constraints.NotNull;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-26
 * @description :
 */
@Data
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ComplexManufacturingResultInputDTO extends ManufacturingResultDTO {
    @NotNull(message = "manufacturing result value is null")
    Double result;
    @Tolerate
    public ComplexManufacturingResultInputDTO(){}


    public ManufacturingResult convertToManufacturingResult(){
        ComplexManufacturingResultInputDTO.ComplexInputDTOConverter converter=new ComplexManufacturingResultInputDTO.ComplexInputDTOConverter();
        return converter.convert(this);
    }
    private static class ComplexInputDTOConverter implements Converter<ComplexManufacturingResultInputDTO, ManufacturingResult> {


        @Override
        public ManufacturingResult convert(ComplexManufacturingResultInputDTO complexInputDTO) {
            ComplexManufacturingResult result= new ComplexManufacturingResult();
            BeanUtils.copyProperties(complexInputDTO,result);
            return result;
        }
    }//ManufacturingResultInputDTOConverter
}
