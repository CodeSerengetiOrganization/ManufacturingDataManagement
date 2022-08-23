package com.mytech;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.exception.serviceexception.FileFormatNotEligible;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import com.mytech.exception.serviceexception.ServiceException;
import com.mytech.service.ComplexResultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-08
 * @description :
 */
@SpringBootTest
public class ServiceExceptionTests {
    @Autowired
    ComplexResultService complexService;
    /*
    * to test whether the FolderNotFoundException annotations are good enough to create a concrete exception instance
    * */
    @Test
    public void createConcreteServiceException(){
        ServiceException folderNotFoundException = FolderNotFoundException.builder()
                .code(001L)
                .cause(new RuntimeException("a new RuntimeException for Service"))
                .message("a concrete FolderNotFoundException built by test")
                .build();
//        FolderNotFoundException.builder().

        System.out.println("folderNotFoundException:"+folderNotFoundException.toString());

        ServiceException fileFormatNotELigible = FileFormatNotEligible.builder()
                .code(001L)
                .message("built by test")
                .cause(new RuntimeException("a RuntimeException for Service"))
                .build();
        System.out.println("fileFormatNotELigible:"+fileFormatNotELigible.toString());
    }

    public void testThrowFolderNotFoundExceptionWhenItNotExtendRuntimeException(){
/*        ServiceException folderNotFoundException = FolderNotFoundException.builder()
                .code(001L)
                .e(new RuntimeException("a new RuntimeException for Service"))
                .message("a concrete FolderNotFoundException built by test")
                .build();*/
//        throw folderNotFoundException;
    }

/*    @Test
    public void throw_service_exception_to_see_if_controller_advice_can_catch_it(){
        complexService.throwServiceException();
    }*/
}//class
