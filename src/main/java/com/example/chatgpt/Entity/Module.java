package com.example.chatgpt.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Module {
    private String id;
    private String name;
    private String description;
    private String classFile;
    private String sequenceFile;
    private String project_id;
    private boolean ifAI;
}
