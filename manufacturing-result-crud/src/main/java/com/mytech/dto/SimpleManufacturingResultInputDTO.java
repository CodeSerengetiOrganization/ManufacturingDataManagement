package com.mytech.dto;

import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import javax.validation.constraints.NotNull;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SimpleManufacturingResultInputDTO extends ManufacturingResultDTO{
    @NotNull(message = "manufacturing result value is null")
    Boolean result;
    @Tolerate
    public SimpleManufacturingResultInputDTO(){}

/*    @Override
    public void setBarcode(@NotBlank(message = "barcode is blank(either null or empty)") String barcode) {
        this.barcode=barcode;
    }

    @Override
    public @NotBlank(message = "barcode is blank(either null or empty)") String getBarcode() {
        return this.barcode;
    }*/

/*
    public ComplexManufacturingResult convertToManufacturingResult(){
        SimpleInputDTOConverter converter=new SimpleInputDTOConverter();
        return converter.convert(this);
    }

    private static class SimpleInputDTOConverter implements Converter<SimpleManufacturingResultInputDTO, ComplexManufacturingResult>{

        @Override
        public ComplexManufacturingResult convert(SimpleManufacturingResultInputDTO manufacturingResultInputDTO) {
            ComplexManufacturingResult result= new ComplexManufacturingResult();
            BeanUtils.copyProperties(manufacturingResultInputDTO,result);
            return result;
        }

    }//SimpleInputDTOConverter
    */
    public ManufacturingResult convertToManufacturingResult(){
        SimpleInputDTOConverter converter=new SimpleInputDTOConverter();
        return converter.convert(this);
    }
    private static class SimpleInputDTOConverter implements Converter<SimpleManufacturingResultInputDTO, ManufacturingResult>{

        @Override
        public ManufacturingResult convert(SimpleManufacturingResultInputDTO simpleManufacturingResultInputDTO) {
            SimpleManufacturingResult result= new SimpleManufacturingResult();
            BeanUtils.copyProperties(simpleManufacturingResultInputDTO,result);
            return result;
        }

    }//SimpleInputDTOConverter
}//SimpleManufacturingResultInputDTO
