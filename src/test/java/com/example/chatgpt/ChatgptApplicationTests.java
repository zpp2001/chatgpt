package com.example.chatgpt;

//import com.example.chatgpt.Utils.HttpRestUtils;
import com.example.chatgpt.Utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ChatgptApplicationTests {


    @Test
    void contextLoads() throws Exception {
       String url="http://localhost:8000?text=";
//        HttpMethod method=HttpMethod.GET;
        Map<String,String> params=new HashMap<>();
        String question= "请根据puml类图@startuml\n" +
                "interface UserLoginService\n" +
                "class UserResource\n" +
                "interface UserRemoveService\n" +
                "interface RestController\n" +
                "interface UserRegisterService\n" +
                "interface UserInfoUpdateService\n" +
                "\n" +
                "@enduml生成对应的java代码，其功能描述如下 ：" +
                "登录功能的逻辑实现在项目中由userManagement微服务负责，首先对数据库表中的user表进行映射，之后编写函数来实现功能，该功能前后端通信的接口为@GetMapping（\"v1/user\"），当前端输入相应的用户名以及密码并提交后，后端调用controller层里的userLogin函数获取前端所传来的用户名和密码，之后以此为参数调用service层中的userLogin函数，该函数会调用getUserByUsernamePassword函数，用来实现登录的身份验证，当用户在前端输入用户名和密码后，后端会在数据库的表中搜索符合用户名和密码的数据，如果搜索到相关数据后，则表示登录成功，会调用update函数用来更新用户的登录次数以及最后一次登录时间，之后向前端返回登录成功的信息，前端接收信息后，跳转到首页页面，并将该用户信息放入session以及localStorage中，方便之后使用；反之则代表登录失败，将消息返回前端后，前端会提示用户登录失败，并让用户重新输入用户名和密码"+
                "用文件名+代码的形式给出";
        params.put("text",question);
        url+=question;
//        System.out.println(params.get("text"));
//        String result= HttpRestUtils.get(url,params);
        String s = HttpUtil.get(url);
        System.out.println(s);
    }

}
