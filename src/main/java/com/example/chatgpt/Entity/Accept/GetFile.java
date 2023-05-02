package com.example.chatgpt.Entity.Accept;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GetFile {
    private String project_id;
    private String module_id;
//    private MultipartFile javaFile;
    private String location;
    private String type;


}
