package com.mytech;

import com.mytech.service.LocalResultConvertService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-04-25
 * @description :
 */
@SpringBootTest
public class LocalResultConvertServiceTest {
    @Autowired
    LocalResultConvertService convertService;

/*    @Test
    public void givenCorrectUrlShouldReturnFileNameList() throws IOException {
        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder";
        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url);
        String fileNameString = fileNameList.toString();
        System.out.println("fileNameString:"+fileNameString);
        Assertions.assertTrue(!fileNameList.isEmpty(), "the folder is empty");
        Assertions.assertTrue(fileNameString.contains("testFile2019.01.01"));

    }

    @Test
    public void givenIncorrectUrlShouldThrowIOException(){
//        String incorrectUrl="incorrect\\path";
        String incorrectUrl="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder.txt";
        Assertions.assertThrows(IOException.class,()->convertService.convertAndSaveLocalTestFiles(incorrectUrl));
//        List<String> fileList = ;
    }*/
    @Test
    public void givenEmptyFolderPathShouldReturnEmptyList() throws IOException {
        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder\\EmptyFolder";
        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url,"txt");
        Assertions.assertTrue(fileNameList.size()==0,"the folder is NOT empty");
    }

    @Test
    public void ifFolderInUrlShouldOnlyReturnFileNames() throws IOException {
        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder\\BothTxtAndDataExtended";
        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url,"txt");
        String fileNameString = fileNameList.toString();
        System.out.println("fileNameString:"+fileNameString);
        Assertions.assertTrue(fileNameString.contains("txtExtended"),"txt files are not found");
        Assertions.assertTrue(!fileNameString.contains("dataExtended"),"data files are found");
    }

    @Test
    public void ifBothTxtAndDataExtendedFilesShouldOnlyReturnTxtFileName(){

    }

}//LocalResultConvertServiceTest
