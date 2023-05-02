package com.example.chatgpt.Mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public String getPwd(String username);
}
