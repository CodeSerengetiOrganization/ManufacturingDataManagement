package com.mytech;

import com.mytech.dto.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-09
 * @description :
 */
@SpringBootTest
public class ErrorDTOTests {
    @Test
    public void createErrorDtoInstanceAndPrint(){
        ErrorDTO errorDto = ErrorDTO.builder()
                .errorCode(001L)
                .errorMessage("an instance created by test mehtod")
                .occurDateTime(LocalDateTime.now())
                .build();
        System.out.println("errorDto:"+errorDto);
    }
}
