package br.com.capelaum.courses.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.enums.CourseStatusEnum;
import br.com.capelaum.courses.exceptions.CourseNotFoundException;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class ToggleCourseActiveService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException());

        var updatedStatus = course.getStatus() == CourseStatusEnum.ACTIVE ? CourseStatusEnum.INACTIVE
                : CourseStatusEnum.ACTIVE;

        course.setStatus(updatedStatus);

        return courseRepository.save(course);
    }
}
