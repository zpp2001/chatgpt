package com.example.chatgpt.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;

@Data
@Getter
@Setter
public class Project {
    private String id;
    private String name;
    private String description;
}
