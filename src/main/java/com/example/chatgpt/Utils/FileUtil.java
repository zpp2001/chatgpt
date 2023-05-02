package com.example.chatgpt.Utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

public class FileUtil {
    public static MultipartFile getMultipartFile(File file) throws Exception {
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), new FileInputStream(file));
        return multipartFile;

}


}
