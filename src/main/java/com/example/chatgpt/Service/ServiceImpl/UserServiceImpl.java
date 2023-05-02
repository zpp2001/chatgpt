package com.example.chatgpt.Service.ServiceImpl;

import com.example.chatgpt.Mapper.UserMapper;
import com.example.chatgpt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getPwd(String username){
        return userMapper.getPwd(username);
    }
}
