package br.com.capelaum.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class CreateCourseService {

    @Autowired
    CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        return this.courseRepository.save(courseEntity);
    }

}
