package com.example.chatgpt.Controller;


import com.example.chatgpt.Entity.Result;
import com.example.chatgpt.Service.ClassFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassFileService classFileService;

    @RequestMapping("/getClassByID")
    public Result getClassByID(String project_id,String module_id){
        return Result.ok();
    }
}
