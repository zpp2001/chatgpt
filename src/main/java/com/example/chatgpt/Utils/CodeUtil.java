package com.example.chatgpt.Utils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CodeUtil {
    public static final Pattern FIELD_PATTERN=Pattern.compile("^public\\s+(?:class|interface)\\s+\\{(?:...)\\}$");
    //获取代码生成的模板
    public static Template getClassTemplate() throws IOException {
        Configuration configuration=new Configuration();
        FileTemplateLoader ftl=new FileTemplateLoader(new File("templates"));
        configuration.setTemplateLoader(ftl);
        Template template=configuration.getTemplate("classTemplate.ftl");
        return template;
    }
    public static Template getInterfaceTemplate() throws IOException{
        Configuration configuration=new Configuration();
        FileTemplateLoader ftl=new FileTemplateLoader(new File("templates"));
        configuration.setTemplateLoader(ftl);
        Template template=configuration.getTemplate("interfaceTemplate.ftl");
        return template;
    }
    //类图初级解析
    public static List<Map<String,Object>> getClassDataModel(MultipartFile file,String project,String module) throws IOException {
       List< Map<String,Object>> dataModels=new ArrayList<>();
        InputStreamReader inputStreamReader=new InputStreamReader(file.getInputStream());
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String str;

        //逐行读入进行解析
        while ((str=bufferedReader.readLine())!=null){
            if(str.contains("class")){
                Map<String,Object> dataModel=new HashMap<>();
                dataModel.put("project",project);
                dataModel.put("module",module);
                String[] strings=str.split(" ");
                dataModel.put("className",strings[1]);
                //如果类内有语句
                if(str.contains("{")){
                    List<String> statements=new ArrayList<>();
                    while (!(str=bufferedReader.readLine()).contains("}")){
                        statements.add(str);
                    }
                    dataModel.put("statements",statements);
//                    statements.clear();
                }
                dataModels.add(dataModel);
            }
        }
        return dataModels;
    }
    public static void createCode(String code){
        String[] strings=code.split("/n");
        int i=0;
        while (strings[i]!=null)
        {
            String str=strings[i];
            //为一个java文件
            if(str.contains("interface")||strings[1].contains("class")){
                String file;
                while (strings[i].contains("interface")||strings[i].contains("class")) continue;
            }
        }

    }
}
