package com.example.chatgpt.Mapper;

import com.example.chatgpt.Entity.ClassFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassFileMapper {
    public void insertClassFile(ClassFile file);
    public List<ClassFile> getClassByID(String project_id,String module_id);
}
