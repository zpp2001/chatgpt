package com.example.chatgpt.Service.ServiceImpl;

import com.example.chatgpt.Entity.ClassFile;
import com.example.chatgpt.Mapper.ClassFileMapper;
import com.example.chatgpt.Service.ClassFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassFileServiceImpl implements ClassFileService {
    @Autowired
    private ClassFileMapper classFileMapper;
    @Override
    public void insertClassFile(ClassFile file){
        classFileMapper.insertClassFile(file);
    };

    @Override
    public List<ClassFile> getClassByID(String project_id, String module_id){
        return classFileMapper.getClassByID(project_id,module_id);
    }
}
