package com.example.chatgpt.Controller;


import com.example.chatgpt.Entity.Accept.GetFile;
import com.example.chatgpt.Entity.ClassFile;
import com.example.chatgpt.Entity.Result;
import com.example.chatgpt.Service.ClassFileService;
import com.example.chatgpt.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private ClassFileService classFileService;
    @RequestMapping("/getAll")
    public Result getAllFiles(String module_id,String project_id) throws Exception {
       List<ClassFile> classFiles= classFileService.getClassByID(project_id,module_id);
       List<GetFile> files=new ArrayList<>();
       for(ClassFile classFile:classFiles){
           GetFile file=new GetFile();
           file.setLocation(classFile.getLocation());
           file.setProject_id(project_id);
           file.setModule_id(module_id);
           file.setType(classFile.getType());
//           File file1=new File(classFile.getLocation());
//           file.setJavaFile(FileUtil.getMultipartFile(file1));
           files.add(file);
       }
        Map<String,Object> data=new HashMap<>();
       data.put("data",files);
        return Result.ok().data(data);
    }
}
