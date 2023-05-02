package com.example.chatgpt.Service;

import com.example.chatgpt.Entity.Project;
import com.example.chatgpt.Mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {
    void addProject(Project project);
    public List<Project> getAllProjects();
}
