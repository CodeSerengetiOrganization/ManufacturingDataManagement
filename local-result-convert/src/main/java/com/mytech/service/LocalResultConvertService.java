package com.mytech.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.JulianFields;
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
        String robotStation =null;
        String erpNo =null;
        String julianDay =null;
        String shiftNo =null;
        String serialNo =null;
        String timeString =null;
        LocalDate localDateYearDay = null;
        LocalTime localTime=null;
        LocalDateTime manufacturingTime=null;
//        String chronoDayString=null;
        int normalStartIndex =0;
        if (!file.exists()) throw new FileNotFoundException("File ["+file.getName()+"] is not found");
        if (file !=null){
            String fileNameWithoutExtension = FilenameUtils.removeExtension(file.getName());
            splitFileName=fileNameWithoutExtension.split("_");
            if(splitFileName.length==arrSizeIfFail && splitFileName[0].equals(failSymbolString)){//this is a FAILED product
                testResult="FAIL";
                normalStartIndex=1;
            }else if (splitFileName.length==arrSizeIfPass) {
                // this product is a PASSED product
                testResult="PASS";
            }else{
                //unknown result
                testResult="UNKNOWN";
            }//if-else
            robotStation=splitFileName[normalStartIndex+1];
            erpNo=splitFileName[normalStartIndex+2];
            julianDay=splitFileName[normalStartIndex+3];
            shiftNo=splitFileName[normalStartIndex+4];
            serialNo=splitFileName[normalStartIndex+5];
            timeString=splitFileName[normalStartIndex+6];
            localDateYearDay = LocalDate.ofYearDay(Integer.parseInt("2022"), Integer.parseInt(julianDay));
            DateTimeFormatter kirTimeFormatter=DateTimeFormatter.ofPattern("HH.mm.ss");
            localTime = LocalTime.parse(timeString, kirTimeFormatter);
            manufacturingTime= LocalDateTime.of(localDateYearDay,localTime);
//            chronoDayString = LocalDate.MIN.with(JulianFields.JULIAN_DAY, Long.parseLong(julianDay))
//                    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//            LocalDate.MIN.with(JulianFields.)

        }//if
        System.out.println("splitFileName array size:"+splitFileName.length);
        for (String s:splitFileName) {
            System.out.println(s);
        }
        System.out.println("testResult:"+testResult);
        System.out.println("robotStation:"+robotStation);
        System.out.println("erpNo:"+erpNo);
        System.out.println("julianDay:"+julianDay);
        System.out.println("localDateYearDay:"+localDateYearDay);
        System.out.println("localTime:"+localTime);
        System.out.println("manufacturingTime:"+manufacturingTime);
        System.out.println("shiftNo:"+shiftNo);
        System.out.println("serialNo:"+serialNo);
        System.out.println("timeString:"+timeString);

    }//parseLocalTestFile

}//class
