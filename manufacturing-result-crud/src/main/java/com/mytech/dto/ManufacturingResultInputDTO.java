package com.mytech.dto;

import com.mytech.domain.ManufacturingResult;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import javax.validation.constraints.NotBlank;

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

    public ManufacturingResult convertToManufacturingResult(){
        ManufacturingResultInputDTOConverter converter=new ManufacturingResultInputDTOConverter();
        return converter.convert(this);
    }

    private static class ManufacturingResultInputDTOConverter implements Converter<ManufacturingResultInputDTO, ManufacturingResult>{

        @Override
        public ManufacturingResult convert(ManufacturingResultInputDTO manufacturingResultInputDTO) {
            ManufacturingResult result= new ManufacturingResult();
            BeanUtils.copyProperties(manufacturingResultInputDTO,result);
            return result;
        }
    }//ManufacturingResultInputDTOConverter
}//ManufacturingResultInputDTO
