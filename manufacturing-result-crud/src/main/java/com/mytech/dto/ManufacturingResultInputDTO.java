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
@EqualsAndHashCode(callSuper = true)
public class ManufacturingResultInputDTO extends ManufacturingResultDTO{
    @Tolerate
    public ManufacturingResultInputDTO(){}

/*    @Override
    public void setBarcode(@NotBlank(message = "barcode is blank(either null or empty)") String barcode) {
        this.barcode=barcode;
    }

    @Override
    public @NotBlank(message = "barcode is blank(either null or empty)") String getBarcode() {
        return this.barcode;
    }*/

    public ComplexManufacturingResult convertToManufacturingResult(){
        ManufacturingResultInputDTOConverter converter=new ManufacturingResultInputDTOConverter();
        return converter.convert(this);
    }

    private static class ManufacturingResultInputDTOConverter implements Converter<ManufacturingResultInputDTO, ComplexManufacturingResult>{

        @Override
        public ComplexManufacturingResult convert(ManufacturingResultInputDTO manufacturingResultInputDTO) {
            ComplexManufacturingResult result= new ComplexManufacturingResult();
            BeanUtils.copyProperties(manufacturingResultInputDTO,result);
            return result;
        }
    }//ManufacturingResultInputDTOConverter
}//ManufacturingResultInputDTO
