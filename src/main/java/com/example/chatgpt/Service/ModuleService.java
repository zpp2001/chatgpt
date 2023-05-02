package com.example.chatgpt.Service;

import com.example.chatgpt.Entity.Module;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ModuleService {
    public void addModule(Module module);
    public List<Module> getAllMoudles(String project_id);
    public void deleteModulesByID(String id, String project_id);
}
