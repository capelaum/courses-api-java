package br.com.capelaum.courses.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.exceptions.CourseNotFoundException;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class DeleteCourseService {

    @Autowired
    CourseRepository courseRepository;

    public void execute(UUID id) {
        Optional<CourseEntity> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent()) {
            throw new CourseNotFoundException();
        }

        CourseEntity course = courseOptional.get();

        courseRepository.delete(course);
    }
}
