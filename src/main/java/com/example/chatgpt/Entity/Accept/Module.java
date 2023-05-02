package com.example.chatgpt.Entity.Accept;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Module {
    private String id;
    private String name;
    private String description;
    private MultipartFile classFile;
    private MultipartFile sequenceFile;
    private String ifAI;
}