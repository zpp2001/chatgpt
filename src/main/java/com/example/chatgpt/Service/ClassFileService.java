package com.example.chatgpt.Service;

import com.example.chatgpt.Entity.ClassFile;

import java.util.List;

public interface ClassFileService {
    public void insertClassFile(ClassFile file);
    public List<ClassFile> getClassByID(String project_id, String module_id);
}
