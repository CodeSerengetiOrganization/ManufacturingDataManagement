package com.mytech;

import com.mytech.domain.ManufacturingResult;
import com.mytech.service.LocalResultConvertService;
import com.mytech.service.ResultInFileLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//    @Test
//    public void givenEmptyFolderPathShouldReturnEmptyList() throws IOException {
//        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder\\EmptyFolder";
//        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url,"txt");
//        Assertions.assertTrue(fileNameList.size()==0,"the folder is NOT empty");
//    }

//    @Test
//    public void ifFolderInUrlShouldOnlyReturnFileNames() throws IOException {
//        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder\\BothFileAndFolderInThisFolder";
//        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url,"txt");
//        String fileNameString = fileNameList.toString();
//        System.out.println("fileNameString:"+fileNameString);
//        Assertions.assertTrue(fileNameString.contains("testFile2019.01.01"),"txt files are not found");
//        Assertions.assertTrue(!fileNameString.contains("New folder"),"data files are found");
//    }

//    @Test
//    public void ifBothTxtAndDataExtendedFilesShouldOnlyReturnTxtFileName() throws IOException {
//        String url="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\programmerB\\LocalTestFileFolder\\BothTxtAndDataExtended";
//        List<String> fileNameList = convertService.convertAndSaveLocalTestFiles(url,"txt");
//        String fileNameString = fileNameList.toString();
//        System.out.println("fileNameString:"+fileNameString);
//        Assertions.assertTrue(fileNameString.contains("txtExtended"),"txt files are not found");
//        Assertions.assertTrue(!fileNameString.contains("dataExtended"),"data files are found");
//    }

    @Test
    public void givenCorrectFilePathShouldPrintFileNameInfo() throws IOException {
        String urlWithFileName="G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\Result_RBTA_11664_48_1_2_03.18.44.txt";
        convertService.convertAndSaveLocalTestFiles(urlWithFileName,"txt");
    }
    @Test
    public void privateGivenPassFileShouldGetPassResult() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int arrSizeIfPass=7;
        int arrSizeIfFail=8;
        String failSymbol="BAD";
        File file=new File("G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\Result_RBTA_11664_48_1_2_03.18.44.txt");
        Class<?> clazz = Class.forName("com.mytech.service.LocalResultConvertService");
        Method parseLocalTestFileMethod = clazz.getDeclaredMethod("parseLocalTestFile", File.class,int.class,int.class,String.class);
        parseLocalTestFileMethod.setAccessible(true);
        parseLocalTestFileMethod.invoke(convertService,file,arrSizeIfPass,arrSizeIfFail,failSymbol);
    }

    @Test
    public void privateGivenFailedFileShouldGetFailResult() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int arrSizeIfPass=7;
        int arrSizeIfFail=8;
        String failSymbol="BAD";
        File file=new File("G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\BAD_Result_RBTA_11664_48_1_8_03.25.35.txt");
        Class<?> clazz = Class.forName("com.mytech.service.LocalResultConvertService");
        Method parseLocalTestFileMethod = clazz.getDeclaredMethod("parseLocalTestFile", File.class,int.class,int.class,String.class);
        parseLocalTestFileMethod.setAccessible(true);
        parseLocalTestFileMethod.invoke(convertService,file,arrSizeIfPass,arrSizeIfFail,failSymbol);
    }

    /*
    * In this test, parseLocalTestFileMethod could successfully throw FileNotFoundException, but then wrapped to java.lang.reflect.InvocationTargetException by org.opentest4j.
    * So, consider it as it pass the test
    * */
    @Test
    public void privateGivenInvalidFileShouldThrowException() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int arrSizeIfPass=7;
        int arrSizeIfFail=8;
        String failSymbol="BAD";
        File file=new File("G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\INVALID_FILE_NAME.txt");
        Class<?> clazz = Class.forName("com.mytech.service.LocalResultConvertService");
        Method parseLocalTestFileMethod = clazz.getDeclaredMethod("parseLocalTestFile", File.class,int.class,int.class,String.class);
        parseLocalTestFileMethod.setAccessible(true);
//        parseLocalTestFileMethod.invoke(convertService,file,arrSizeIfPass,arrSizeIfFail,failSymbol);
        Assertions.assertThrows(FileNotFoundException.class,()->{parseLocalTestFileMethod.invoke(convertService,file,arrSizeIfPass,arrSizeIfFail,failSymbol);});
    }

    @Test
    public void privateGivenValidFileShouldPrintAllLines() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        File file=new File("G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\BAD_Result_RBTA_11664_48_1_8_03.25.35.txt");
        File file=new File("G:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\48\\BAD_Result_RBTA_11664_48_1_8_03.25.35.txt");
        Class<?> clazz = Class.forName("com.mytech.service.LocalResultConvertService");
        Method readFileContentMethod = clazz.getDeclaredMethod("readFileContent", File.class);
        readFileContentMethod.setAccessible(true);
        readFileContentMethod.invoke(convertService,file);
    }

    @Test
    public void privateGetBarCodeSimuShouldReturnString() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.mytech.service.LocalResultConvertService");
        Method getBarCodeSimuMethod = clazz.getDeclaredMethod("getBarCodeSimu");
        getBarCodeSimuMethod.setAccessible(true);
        String randomBarcode = (String) getBarCodeSimuMethod.invoke(convertService);
        System.out.println("randomBarcode:"+randomBarcode);
        Assertions.assertTrue(randomBarcode.length()==10,"String length is not correct" );

    }

    @Test
    public void giveRightUrlAndExtensionShouldSaveResultIntoComplexResultTable() throws IOException {
        List<ManufacturingResult> returnedList = convertService.convertAndSaveLocalTestFiles("E:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\ScannerFiles\\49\\BAD_Result_RBTA_11664_48_1_8_03.25.35.txt", "txt");
        for (ManufacturingResult result:returnedList) {
            System.out.println(result);
        }
    }

    /**
     * This method has no business purpose, just to see if this test class could run or not.
     */
    @Test
    public void justToSeeIfTestClassCouldRun(){
        System.out.println("Test could run");
    }


    @Test
    public void testReadFileContent() throws IOException {
        File file= new File("E:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\LocalTextFiles\\BAD_Result_RBTA_11664_48_1_8_03.25.35.txt");
        convertService.readFileContent(file);
    }

    @Test
    public void testReadFileContentToObjects() throws IOException {
        File file=new File("E:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\LocalTextFiles2\\failed-sample.txt");
        Set<ResultInFileLine> results = convertService.readFileContentToObjects(file);
        for (ResultInFileLine result:results) {
            System.out.println(result);
        }
    }

    @Test
    public void testConvertAndSaveLocalTestFiles_give_right_url_should_save_into_database_manual_check() throws IOException {
        String url="E:\\JavaProjects\\KirchhoffManufacturingDataManagementSystem\\LocalTextFiles2";
        List<ManufacturingResult> resultList = convertService.convertAndSaveLocalTestFiles(url, "txt");

    }
}//LocalResultConvertServiceTest
