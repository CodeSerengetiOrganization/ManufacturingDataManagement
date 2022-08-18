package com.mytech;

import com.mytech.dto.SimpleManufacturingResultInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-10-05
 * @description :
 */
@SpringBootTest
public class DtoInheritanceTests {
    @Test
    public void testInputDtoSetter(){
        SimpleManufacturingResultInputDTO inputDTO=new SimpleManufacturingResultInputDTO();
        inputDTO.setBarcode("a barcode");
        inputDTO.setOperator("Bob");
        System.out.println("inputDTO in test:"+inputDTO);
    }
}
