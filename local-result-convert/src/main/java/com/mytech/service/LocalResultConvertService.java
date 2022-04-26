package com.mytech.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
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

/*    public List<String> listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                //add file name into list
            }//if-else
        }//for
        return null;
    }//listFilesForFolder*/

}//class
