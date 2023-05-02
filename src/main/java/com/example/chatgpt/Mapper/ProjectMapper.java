package com.example.chatgpt.Mapper;

import com.example.chatgpt.Entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ProjectMapper {
    public void addProject(Project project);
    public List<Project> getAllProjects();
}
