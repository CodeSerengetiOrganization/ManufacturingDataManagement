package com.mytech;

import com.mytech.controller.LocalResultConvertController;
import com.mytech.domain.ManufacturingResult;
import com.mytech.exception.apiexception.ApiFolderNotFoundException;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import com.mytech.service.LocalResultConvertService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LocalResultConvertControllerTests {
    @Mock
    private LocalResultConvertService service;

    private LocalResultConvertController controller;

    @InjectMocks
    private LocalResultConvertController controller2;


    @BeforeEach
    void setup(){
//        controller.setLocalResultConvertService(service);
        controller=new LocalResultConvertController(service);
    }
    @Test
    public void mock_service_layer_throws_FileNotFoundException_controller_should_wrapper_it() throws IOException {

        FolderNotFoundException mockTestException= FolderNotFoundException.builder()
                .message("mock test exception message")
                .cause(new RuntimeException()).build();
        BDDMockito.given(service.convertAndSaveLocalTestFiles(Mockito.anyString(),Mockito.anyString()))
                .willThrow(mockTestException);
//        BDDMockito.doThrow(mockTestException).when(localResultConvertService).convertAndSaveLocalTestFiles("url","txt");
//        Mockito.when(service.convertAndSaveLocalTestFiles(Mockito.anyString(),Mockito.anyString())).thenThrow(mockTestException);
        ApiFolderNotFoundException apiFolderNotFoundException = Assertions.assertThrows(ApiFolderNotFoundException.class, () -> {
            controller2.convertLocalResultTextFiles("url");
        });
        System.out.println("message:"+apiFolderNotFoundException.getMessage());
        System.out.println("code:"+apiFolderNotFoundException.getCode());

    }

    @Test
    public void mock_service_throw_exception(){
        FolderNotFoundException mockTestException= FolderNotFoundException.builder()
                .code(123456L)
                .message("mock test exception message")
                .cause(new RuntimeException()).build();
        try {
            Mockito.when(service.convertAndSaveLocalTestFiles(Mockito.anyString(),Mockito.anyString())).thenThrow(mockTestException);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            service.convertAndSaveLocalTestFiles("url","txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
