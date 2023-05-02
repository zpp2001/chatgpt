package com.example.chatgpt.Controller;

import com.example.chatgpt.Entity.Project;
import com.example.chatgpt.Entity.Result;
import com.example.chatgpt.Service.ProjectService;
import com.example.chatgpt.Utils.CodeUtil;
import com.example.chatgpt.Utils.HttpUtil;
import com.example.chatgpt.Utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class ChatController {
    @Autowired
    private ProjectService projectService;
    /*
    * 该函数用于接收创建的项目信息并存入数据库
    * */
    @RequestMapping("/upload")
    public Result getCode(Project project){
        project.setId(IdUtils.generateShortUUID());
        projectService.addProject(project);
        return Result.ok();
    }
    @RequestMapping("/files")
    public Result getFile(MultipartFile[]files) throws IOException {
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
            InputStream inputStream=file.getInputStream();
            String content=new String(file.getBytes());
//            System.out.println(question);
           String question="请根据puml类图"+content+
                   "生成java代码,以xxx.java\n"+
                   "java代码的形式给出答案"+
                   "项目的描述为登录功能的逻辑实现在项目中由userManagement微服务负责，首先对数据库表中的user表进行映射，之后编写函数来实现功能，该功能前后端通信的接口为@GetMapping（\"v1/user\"），当前端输入相应的用户名以及密码并提交后，后端调用controller层里的userLogin函数获取前端所传来的用户名和密码，之后以此为参数调用service层中的userLogin函数，该函数会调用getUserByUsernamePassword函数，用来实现登录的身份验证，当用户在前端输入用户名和密码后，后端会在数据库的表中搜索符合用户名和密码的数据，如果搜索到相关数据后，则表示登录成功，会调用update函数用来更新用户的登录次数以及最后一次登录时间，之后向前端返回登录成功的信息，前端接收信息后，跳转到首页页面，并将该用户信息放入session以及localStorage中，方便之后使用；反之则代表登录失败，将消息返回前端后，前端会提示用户登录失败，并让用户重新输入用户名和密码。";
            String result=HttpUtil.get(HttpUtil.url+question);
//            System.out.println(result);

            }

        return Result.ok();
    }
}
