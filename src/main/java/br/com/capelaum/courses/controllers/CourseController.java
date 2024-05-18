package br.com.capelaum.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.exceptions.InvalidCourseStatusException;
import br.com.capelaum.courses.services.CreateCourseService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var createdCourse = this.createCourseService.execute(courseEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (InvalidCourseStatusException | ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro inesperado");
        }

    }

}
