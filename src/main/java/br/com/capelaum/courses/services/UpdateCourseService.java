package br.com.capelaum.courses.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.capelaum.courses.dto.UpdateCourseRequestDTO;
import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.exceptions.CourseNotFoundException;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class UpdateCourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, UpdateCourseRequestDTO updateRequestDTO) {
        var course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        if (StringUtils.hasText(updateRequestDTO.getName())) {
            course.setName(updateRequestDTO.getName());
        }

        if (StringUtils.hasText(updateRequestDTO.getCategory())) {
            course.setCategory(updateRequestDTO.getCategory());
        }

        return courseRepository.save(course);
    }
}
