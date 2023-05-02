package com.example.chatgpt.Service.ServiceImpl;

import com.example.chatgpt.Entity.Project;
import com.example.chatgpt.Mapper.ProjectMapper;
import com.example.chatgpt.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public void addProject(Project project) {
        projectMapper.addProject(project);
    }
    @Override
    public List<Project> getAllProjects(){
        return projectMapper.getAllProjects();
    }
}
