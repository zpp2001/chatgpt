package com.example.chatgpt.Controller;

import com.example.chatgpt.Entity.Project;
import com.example.chatgpt.Entity.Result;
import com.example.chatgpt.Service.ProjectService;
import com.example.chatgpt.Utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @RequestMapping("/create")
    public Result createProject(@RequestParam("name") String name,@RequestParam("description") String description){
        if(name!=null) {
            Project project=new Project();
            project.setName(name);
            project.setDescription(description);
            project.setId(IdUtils.generateShortUUID());
            //创建项目相关文件夹
            File file=new File("code\\"+project.getName());
            if(!file.exists()) file.mkdir();
            projectService.addProject(project);
            return Result.ok();
        }
        return Result.error();
    }
    @PostMapping("/getAll")
    public Result getAllProjects(){
        List<Project> projects=projectService.getAllProjects();
        return Result.ok().data("projects",projects);
    }
}
