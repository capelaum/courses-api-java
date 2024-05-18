package br.com.capelaum.courses.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capelaum.courses.exceptions.CourseNotFoundException;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class DeleteCourseService {

    @Autowired
    CourseRepository courseRepository;

    public void execute(UUID id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException());

        courseRepository.delete(course);
    }
}
