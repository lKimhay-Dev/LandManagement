package com.klsoft.lms.controller;

import com.klsoft.lms.dto.ProjectDto;
import com.klsoft.lms.service.ProjectService;
import com.klsoft.lms.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectRestController {

    public ProjectService projectService;

    public ProjectRestController(ProjectService ps) {
        this.projectService = ps;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        return this.projectService.findAll();
    }

    @GetMapping("/{projectId}")
    public ResponseDto findOne(@PathVariable int projectId) {
        return this.projectService.findOne(projectId);
    }

    @PostMapping
    public ResponseDto create(@RequestBody ProjectDto proDto) {
        return this.projectService.create(proDto);
    }

    @PostMapping("/update")
    public ResponseDto update(@RequestBody ProjectDto proDto) {
        return this.projectService.update(proDto);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseDto delete(@PathVariable int projectId) {
        return this.projectService.delete(projectId);
    }
}
