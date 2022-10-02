package com.mytech.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.exception.serviceexception.FileFormatNotEligible;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import com.mytech.savecommand.*;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-04-25
 * @description :
 * This is a service class to provide functionality that converting local test file to objects and then save into database.
 */
@Service
public class LocalResultConvertService {

    @Autowired
    ComplexResultService resultService;
    @Autowired
    SaveCommandInvoker saveCommandInvoker;
    @Autowired
    CommandFactory commandFactory;

    //this is the method used by controller layer.
    public List<ManufacturingResult> convertAndSaveLocalTestFiles(String url,String fileExtension) throws IOException {
        System.out.println("url in service:"+url);
        ArrayList<String> fileNameList = Lists.newArrayList();
        ArrayList<String> savedFileNameList=Lists.newArrayList();
        HashSet<ManufacturingResult> resultSetFromAllFiles=Sets.newHashSet();
//        fileNameList.add("testFile2019.01.01");
        FileNameExtensionFilter filter=new FileNameExtensionFilter("extension filter only filter txt files","txt");
        List<File> filesInFolder = Files.walk(Paths.get(url))
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                .map(Path::toFile)
                .collect(Collectors.toList());
        if(filesInFolder==null) throw FolderNotFoundException.builder()
                .message("service message:the folder select could not found.")
                .build();
        for (File file: filesInFolder) {
            if(!file.isFile()){
                throw FileFormatNotEligible.builder()
                        .message("this file is not a normal file").build();
            }
            fileNameList.add(file.getName());
            System.out.println("in service，fileNameList size is:"+fileNameList.size()); //for diagnostic
            System.out.println("in service, the file name:"+file.getName()); //for diagnostic
//            if(fileNameList.size()<1){
//                System.out.println();
//            }
            parseLocalTestFile(file,7,8,"BAD");
            Set<ManufacturingResult> resultSetFromSingleFile = buildManufacturingResultFromFile(file, 7, 8, "BAD");
//                resultSetFromAllFiles.add(resultSetFromSingleFile);
            resultSetFromAllFiles.addAll(resultSetFromSingleFile);
        }//for

        if (resultSetFromAllFiles==null || resultSetFromAllFiles.size()<=0) {
            System.out.println("resultSetFromAllFiles is null or empty");
            return null;
        }
        System.out.println("start to go through resultSetFromAllFiles in methohd convertAndSaveLocalTestFiles");
        for (ManufacturingResult result:resultSetFromAllFiles) {
            if(result instanceof ComplexManufacturingResult){
                System.out.println("complex:"+(ManufacturingResult)result);
            }
            if(result instanceof SimpleManufacturingResult){
                System.out.println("simple:"+(SimpleManufacturingResult)result);
            }
        }
        System.out.println("start to go through resultSetFromAllFiles in methohd convertAndSaveLocalTestFiles");
/*
        //use for-loop before saveAll functionality of CRUD service  is ready
        for (ManufacturingResult result:resultSetFromAllFiles) {
            resultService.save(result);
        }
        return resultSetFromAllFiles;
        */

//        return (List<ManufacturingResult>) saveCommandInvoker.saveAll(commandFactory.getCommand("complexResult"),resultSetFromAllFiles);
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultSetFromAllFiles);
        for (IManufacturingResultSaveCommand command:commandSet) {
            if(command instanceof SaveSimpleResultCommand){
                System.out.println("simple command:"+command);
            }
            if(command instanceof SaveComplexResultCommand){
                System.out.println("complex command:"+command);
            }
        }
        List<ManufacturingResult> savedResults = saveCommandInvoker.saveResult(commandSet);
        return savedResults;
    }//convertAndSaveLocalTestFiles

    /**
     * using
     * parse the content of text file,extract manufacturing results
     * @param file the File object to parse
     * @param arrSizeIfPass the size of a passed result
     * @param arrSizeIfFail the size of a failed result
     * @param failSymbolString the string to indicate the part is failed
     * @return a Set of of ManufacturingResult which contains all extracted manufacutring result from the text file
     * @throws IOException
     */
    private Set<ManufacturingResult> buildManufacturingResultFromFile(File file, int arrSizeIfPass, int arrSizeIfFail, String failSymbolString) throws IOException {
        HashSet<ManufacturingResult> manufacturingResultSet= Sets.newHashSet();
        String[] splitFileName = new String[0];
        String testResult =null;
        String robotChanel =null;
        int erpNo =-1;
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
            robotChanel=splitFileName[normalStartIndex+1];
            erpNo=Integer.parseInt(splitFileName[normalStartIndex+2]);
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
            //read the test file here
            //build ManufacturingResult template object, this object is only for public properties
            String simuBarcode=getBarCodeSimu();
            int simuStationCode=getStationCode();
            int simuStationChanelNo = getStationChanelNo(robotChanel);
            String simuOperator=getOperator();
            boolean simuSimpleTestResult= "PASS".equals(testResult)? true:false;
            ComplexManufacturingResult complexResultTemplate = ComplexManufacturingResult.builder()
                    .productCode(erpNo)
                    .barcode(simuBarcode)
                    .startTime(manufacturingTime)
                    .endTime(manufacturingTime)
                    .comment("shift:" + shiftNo)
                    .stationCode(simuStationCode)
                    .testItem(getTestItem())
                    .stationChannelNo(simuStationChanelNo)
                    .operator(simuOperator)
                    .build();
            HashMap<String, Double> testItemMap = readFileContent(file);

            //build ComplextManufacturingResult object
            System.out.println("start to show elements in testItemMap in method buildManufacturingResultFromFile");
            for (Map.Entry<String,Double> entries:testItemMap.entrySet()) {
                //todo: create an instance here and copy all public properties into this new instance, making sure different instance will save into database
                System.out.println(entries.getKey()+":"+entries.getValue());
                String testItem=entries.getKey();
                Double testValue=entries.getValue();
                ComplexManufacturingResult complexResult=new ComplexManufacturingResult();
                BeanUtils.copyProperties(complexResultTemplate,complexResult);
                complexResult.setFeatureName(testItem);
                complexResult.setFeatureType(testItem);
                complexResult.setResult(testValue);
//                complexResult.setBarcode(getBarCodeSimu());
                manufacturingResultSet.add(complexResult);
//                System.out.println("complexResult Object Hashcode:"+complexResult.hashCode());
//                System.out.println("complexResult Object:"+complexResult.toString());
            }//for


            //build SimpleManufacturingResult object
            SimpleManufacturingResult simpleResult = SimpleManufacturingResult.builder()
                    .productCode(erpNo)
                    .barcode(simuBarcode)
                    .result(simuSimpleTestResult)
                    .startTime(manufacturingTime)
                    .endTime(manufacturingTime)
                    .comment("shift:" + shiftNo)
                    .stationCode(simuStationCode)
                    .stationChannelNo(simuStationChanelNo)
                    .operator(simuOperator).build();
            manufacturingResultSet.add(simpleResult);
        }//if
        //show the manufacturingResultSet, which is to return
        for (ManufacturingResult resultM: manufacturingResultSet) {
            if(resultM instanceof ComplexManufacturingResult){
                ComplexManufacturingResult result = (ComplexManufacturingResult) resultM;
                System.out.println("from manufacturingResultSet:"+result.toString());
            }
            if(resultM instanceof SimpleManufacturingResult){
                SimpleManufacturingResult result = (SimpleManufacturingResult) resultM;
                System.out.println("from manufacturingResultSet:"+result.toString());
            }
        }
        return manufacturingResultSet;
    }//parseLocalTestFile

    /**
     * parse the content of local text file in
     * @param file
     * @param arrSizeIfPass
     * @param arrSizeIfFail
     * @param failSymbolString
     * @throws IOException
     */
    private void parseLocalTestFile(File file,int arrSizeIfPass,int arrSizeIfFail,String failSymbolString) throws IOException {
        String[] splitFileName = new String[0];
        String testResult =null;
        String robotChanel =null;
        int erpNo =-1;
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
            robotChanel=splitFileName[normalStartIndex+1];
            erpNo=Integer.parseInt(splitFileName[normalStartIndex+2]);
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
            //read the test file here
            //build ManufacturingResult object, this object is only for public properties
            ComplexManufacturingResult complexResult = ComplexManufacturingResult.builder().productCode(erpNo)
                    .startTime(manufacturingTime)
                    .endTime(manufacturingTime)
                    .comment("shift:" + shiftNo)
                    .stationCode(getStationCode())
                    .stationChannelNo(getStationChanelNo(robotChanel))
                    .operator(getOperator())
                    .testItem(getTestItem())
                    .build();
            HashMap<String, Double> testItemMap = readFileContent(file);
            //add test items and result readings into the ManufacturingResult object.
            for (Map.Entry<String,Double> entries:testItemMap.entrySet()) {
                //todo: create an instance here and copy all public properties into this new instance, making sure different instance will save into database
                System.out.println(entries.getKey()+":"+entries.getValue());
                String testItem=entries.getKey();
                Double testValue=entries.getValue();
                complexResult.setFeatureName(testItem);
                complexResult.setFeatureType(testItem);
                complexResult.setResult(testValue);
                complexResult.setBarcode(getBarCodeSimu());
                System.out.println("complexResult Object Hashcode:"+complexResult.hashCode());
                System.out.println("complexResult Object:"+complexResult.toString());
            }

        }//if
//        System.out.println("splitFileName array size:"+splitFileName.length);
//        for (String s:splitFileName) {
//            System.out.println(s);
//        }
//        System.out.println("testResult:"+testResult);
//        System.out.println("robotStation:"+robotStation);
//        System.out.println("erpNo:"+erpNo);
//        System.out.println("julianDay:"+julianDay);
//        System.out.println("localDateYearDay:"+localDateYearDay);
//        System.out.println("localTime:"+localTime);
//        System.out.println("manufacturingTime:"+manufacturingTime);
//        System.out.println("shiftNo:"+shiftNo);
//        System.out.println("serialNo:"+serialNo);
//        System.out.println("timeString:"+timeString);

    }//parseLocalTestFile

    /**
     * using
     * read the content of text file, get test items and its result readings, this method is to use the real manufacturing text file.
     * @param file
     * @return A map contains test item for key, test result readings for value.
     * @throws IOException
     */
    public HashMap<String, Double> readFileContent(File file) throws IOException {
        HashMap<String, Double> testItemMap = Maps.newHashMap();
        if (file !=null){
            if (file.isFile()){
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
//                lines.forEach(System.out::println);
                List<String> lineList = lines.collect(Collectors.toList());
                System.out.println("print all lines begins in method readFileContent:");
                for (String line:lineList) {
                    System.out.println(line);
                    String[] split = line.split(":");
                    testItemMap.put(split[0],Double.parseDouble(split[1]));
                }//for
                System.out.println("print all lines ends in method readFileContent:");
                lines.close();//close the stream as it is an I/O operation;
/*                System.out.println("start to show testItemMap content");
                for (Map.Entry<String,Double> testResult:testItemMap.entrySet() ) {
                    System.out.println(testResult.getKey()+":"+testResult.getValue());
                }*/
            }
        }
        return testItemMap;
    }//readFileContent

    /**
     * using
     * read the content of text file, get test items and its result readings, this method is to use the simulated manufacturing text file.
     * @param file
     * @return A map contains test item for key, test result readings for value.
     * @throws IOException
     */
    private HashMap<String, Double> readFileContent2(File file) throws IOException {
        HashMap<String, Double> testItemMap = Maps.newHashMap();
        if (file !=null){
            if (file.isFile()){
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
                List<String> lineList = lines.collect(Collectors.toList());
                for (String line:lineList) {
                    System.out.println("line Content:"+line);
                    String[] split = line.split(",");
                    testItemMap.put(split[0],Double.parseDouble(split[1]));
                    for (Map.Entry<String,Double> entries:testItemMap.entrySet()) {
                        System.out.println(entries.getKey()+":"+entries.getValue());
                    }
//                    System.out.println("PT20 U:"+split[102]);
//                    System.out.println("PT17 P:"+split[103]);
//                    System.out.println("PT17 U:"+split[104]);
//                    for (int i = 0; i <split.length ; i++) {
//                        System.out.println("line["+i+"]:"+split[i]);
//                    }
                }
                lines.close();//close the stream as it is an I/O operation;
            }
        }
        return testItemMap;
    }//readFileContent


    private String getBarCodeSimu(){
        String prefix="GM_CCB";
        String randomBarcode=String.format("%4d",ThreadLocalRandom.current().nextInt(1, 10000));
        return prefix+randomBarcode;
    }

    private String getOperator(){
        return "ServiceLayer Alice";
    }

    private int getStationChanelNo(String stationName){
        if (stationName != null) {
            if ("RBTA".equals(stationName)){
                return 1;
            }else if("RBTB".equals(stationName)){
                return 2;
            }
        }
        return -1;
    }

    private int getStationCode(){
        return 2;//means ScannerCell at Aurora location
    }

    private String getTestItem(){
        return "Height";
    }
}//class
