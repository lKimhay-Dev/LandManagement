package com.klsoft.lms.service;

import com.klsoft.lms.dto.ProjectDto;
import com.klsoft.lms.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProjectService {
    ResponseEntity<List<ProjectDto>> findAll();

    ResponseDto findOne(int projectId);

    ResponseDto create(ProjectDto projectDto);

    ResponseDto update(ProjectDto projectDto);

    ResponseDto delete(int id);
}
