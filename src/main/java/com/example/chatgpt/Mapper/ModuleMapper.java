package com.example.chatgpt.Mapper;

import com.example.chatgpt.Entity.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {
    public void addModule(Module module);
    public List<Module> getAllMoudles(String project_id);
    public void deleteModulesByID(String id, String project_id);
}
