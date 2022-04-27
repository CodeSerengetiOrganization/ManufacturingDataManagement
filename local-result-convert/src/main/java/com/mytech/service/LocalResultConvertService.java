package com.mytech.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-04-25
 * @description :
 * This is a service class to provide functionality that converting local test file to objects and then save into database.
 */
@Service
public class LocalResultConvertService {

    public List<String> convertAndSaveLocalTestFiles(String url,String fileExtension) throws IOException {
        ArrayList<String> fileNameList = Lists.newArrayList();
//        fileNameList.add("testFile2019.01.01");
        FileNameExtensionFilter filter=new FileNameExtensionFilter("extension filter only filter txt files","txt");
        List<File> filesInFolder = Files.walk(Paths.get(url))
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File file: filesInFolder) {
            if (file.isFile()){
                fileNameList.add(file.getName());
            }//if
        }//for
        return fileNameList;
    }//convertAndSaveLocalTestFiles

    private void parseLocalTestFile(File file,int arrSizeIfPass,int arrSizeIfFail,String failSymbolString) throws FileNotFoundException {
        String[] splitFileName = new String[0];
        String testResult =null;
        if (!file.exists()) throw new FileNotFoundException("File ["+file.getName()+"] is not found");
        if (file !=null){
            splitFileName=file.getName().split("_");
            if(splitFileName.length==arrSizeIfFail && splitFileName[0].equals(failSymbolString)){//this is a FAILED product
                testResult="FAIL";
            }else if (splitFileName.length==arrSizeIfPass) {
                // this product is a PASSED product
                testResult="PASS";
            }else{
                //unknown result
                testResult="UNKNOWN";
            }
        }
        System.out.println("splitFileName array size:"+splitFileName.length);
        for (String s:splitFileName) {
            System.out.println(s);
        }
        System.out.println("testResult:"+testResult);
    }//parseLocalTestFile

}//class
