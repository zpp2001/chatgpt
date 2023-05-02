package com.example.chatgpt.Controller;

import com.example.chatgpt.Entity.Accept.Module;
import com.example.chatgpt.Entity.ClassFile;
import com.example.chatgpt.Entity.Result;
import com.example.chatgpt.Service.ClassFileService;
import com.example.chatgpt.Service.ModuleService;
import com.example.chatgpt.Utils.CodeUtil;
import com.example.chatgpt.Utils.HttpUtil;
import com.example.chatgpt.Utils.IdUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

@RestController
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ClassFileService classFileService;
    @RequestMapping("/getAll")
    public Result getAllModules(String project_id){
        List<com.example.chatgpt.Entity.Module> moduleList=moduleService.getAllMoudles(project_id);
        //处理类图和时序图
        for (com.example.chatgpt.Entity.Module module:moduleList){
            if(module.getClassFile()!=null) module.setClassFile("已上传");
            if(module.getSequenceFile()!=null) module.setSequenceFile("已上传");
            if(module.getClassFile()==null) module.setClassFile("未上传");
            if(module.getSequenceFile()==null) module.setSequenceFile("未上传");
        }
        Map<String,Object> data=new HashMap<>();
        data.put("data",moduleList);
        return Result.ok().data("modules",moduleList);
    }
    @RequestMapping("/deleteByid")
    public Result deleteModulesByID(String id,String project_id){
        if(id==null||project_id==null) return Result.error();
        moduleService.deleteModulesByID(id,project_id);
        return Result.ok();
    }

    @RequestMapping("/onlyClass")
    public Result getCode(Module module, String projectId,String projectName) throws IOException, TemplateException {
        com.example.chatgpt.Entity.Module module1=new com.example.chatgpt.Entity.Module();
        //如果模块为空，则名字为文件名
        if(module.getName().isEmpty()){
            String fileName=module.getClassFile().getOriginalFilename();
            String name=fileName.substring(0,(fileName.lastIndexOf('.')));
           System.out.println(name);
           module.setName(name);
        }
        module1.setName(module.getName());
        module1.setProject_id(projectId);
        module1.setDescription(module.getDescription());
        module1.setId(IdUtils.generateShortUUID());
//        module1.setIfAI(module.isIfAI());
        File classFile=new File("file\\"+module.getClassFile().getOriginalFilename());
        classFile.createNewFile();
        FileOutputStream fos=new FileOutputStream(classFile);
        fos.write(module.getClassFile().getBytes());
        fos.close();
        //如果采用代码智能补全
        if(module.getIfAI().equals("true")){
            String file=new String(module.getClassFile().getBytes());
            String question="请给予puml类图"+file+"生成java代码，并将代码以字符串形式直接返回";
            if(module1.getDescription()!=null)
                 question="请给予puml类图"+file+"生成java代码，并以文件名+java代码的形式返回结果，其中项目描述为"+module1.getDescription();
            Map<String,String> params=new HashMap<>();
            params.put("text",question);
            String url=HttpUtil.url+question;
           String result= HttpUtil.get(url);
           System.out.println(result);
            String []code=CodeUtil.FIELD_PATTERN.split(result);
//            HashMap<String, String> classMap = new HashMap<>();
//            if(matcher.find()) {
//                String word = matcher.group();
//                classMap.put(word, result);
//            }
                return  Result.ok();
        }
        System.out.println(classFile.getCanonicalPath());
        module1.setClassFile(classFile.getPath());
        moduleService.addModule(module1);
        //创建文件夹
        String location="code\\"+projectName+"\\"+module1.getName();
        File file=new File(location);
        if(!file.exists()) file.mkdir();
        List<Map<String,Object>> dataModels= CodeUtil.getClassDataModel(module.getClassFile(),projectName, module.getName() );
        for(Map<String,Object> datamodel:dataModels){
            ClassFile classFile1=new ClassFile();
            classFile1.setModule_id(module1.getId());
            classFile1.setProject_id(module1.getProject_id());
            Template template=CodeUtil.getClassTemplate();
            String str=location+"\\"+datamodel.get("className")+".java";
            classFile1.setLocation(str);
            classFile1.setName(datamodel.get("className").toString());
            classFileService.insertClassFile(classFile1);
            template.process(datamodel,new FileWriter(new File(str)));
        }
        System.out.println(module.getClassFile());
        return Result.ok();
    }

}
