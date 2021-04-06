package com.klsoft.lms.service.impl;

import com.klsoft.lms.dto.ProjectDto;
import com.klsoft.lms.entity.Project;
import com.klsoft.lms.repository.ProjectRepository;
import com.klsoft.lms.service.ProjectService;
import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.utils.Status;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projects = new ArrayList<>();
        this.projectRepository.findAll().forEach(project -> {
            projects.add(modelMapper.map(project, ProjectDto.class));
        });
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseDto findOne(int projectId) {
        Optional<Project> project = this.projectRepository.findById(projectId);
        if (project.isPresent()) {
            ProjectDto projects = modelMapper.map(project.get(), ProjectDto.class);
            return new ResponseDto("Find User Successfully", Status.SUCCESS.value(), HttpStatus.OK.value(), projects);
        }
        return new ResponseDto("Find User Failed", Status.FAILED.value(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

    }

    @Override
    public ResponseDto create(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        this.projectRepository.save(project);
        return new ResponseDto("Create Successfully", Status.SUCCESS.value(), HttpStatus.OK.value(), true);

    }

    @Override
    public ResponseDto update(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        this.projectRepository.save(project);
        return new ResponseDto("Update Successfully", Status.SUCCESS.value(), HttpStatus.OK.value(), true);

    }

    @Override
    public ResponseDto delete(int id) {
        try {
            this.projectRepository.deleteById(id);
            return new ResponseDto("Delete Successfully", Status.SUCCESS.value(), HttpStatus.OK.value(), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto("Delete Failed", Status.FAILED.value(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
        }
    }
}
