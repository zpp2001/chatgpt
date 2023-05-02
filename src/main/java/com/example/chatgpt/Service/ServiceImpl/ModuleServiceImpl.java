package com.example.chatgpt.Service.ServiceImpl;

import com.example.chatgpt.Entity.Module;
import com.example.chatgpt.Mapper.ModuleMapper;
import com.example.chatgpt.Service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public void addModule(Module module){
        moduleMapper.addModule(module);

    }
    @Override
    public List<Module> getAllMoudles(String project_id){
        return moduleMapper.getAllMoudles(project_id);
    }
    @Override
    public void deleteModulesByID(String id, String project_id){
        moduleMapper.deleteModulesByID(id,project_id);
    }
}
