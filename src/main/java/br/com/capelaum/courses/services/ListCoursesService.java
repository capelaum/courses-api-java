package br.com.capelaum.courses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.repositories.CourseRepository;

@Service
public class ListCoursesService {

    @Autowired
    CourseRepository courseRepository;

    public List<CourseEntity> execute(String name, String category) {
        if (StringUtils.hasText(name) && StringUtils.hasText(category)) {
            return courseRepository.findByNameContainingAndCategoryContaining(name, category);
        }

        if (StringUtils.hasText(name)) {
            return courseRepository.findByNameContaining(name);
        }

        if (StringUtils.hasText(category)) {
            return courseRepository.findByCategoryContaining(category);
        }

        return courseRepository.findAll();
    }
}
