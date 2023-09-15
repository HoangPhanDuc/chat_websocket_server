package com.example.chatserver.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

@RestController
public class FileController {

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        String Path_Directory = "D:\\JavaProject\\ChatServer\\src\\main\\file";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
    }

    @GetMapping("/getFile")
    public Properties showFile() {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:D:\\JavaProject\\ChatServer\\src\\main\\file");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

//    @GetMapping("/downloadFile")
//    public Properties getFile() {
//        Properties properties = new Properties();
//        try {
//            File file = ResourceUtils.getFile("classpath:D:\\JavaProject\\ChatServer\\src\\main\\file");
//            OutputStream out = new OutputStream(file);
//            properties.load(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties;
//    }

}