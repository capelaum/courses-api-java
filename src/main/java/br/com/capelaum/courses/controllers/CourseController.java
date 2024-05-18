package br.com.capelaum.courses.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.capelaum.courses.dto.UpdateCourseRequestDTO;
import br.com.capelaum.courses.entities.CourseEntity;
import br.com.capelaum.courses.exceptions.CourseNotFoundException;
import br.com.capelaum.courses.exceptions.InvalidCourseStatusException;
import br.com.capelaum.courses.services.CreateCourseService;
import br.com.capelaum.courses.services.ListCoursesService;
import br.com.capelaum.courses.services.UpdateCourseService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @Autowired
    private ListCoursesService listCoursesService;

    @Autowired
    private UpdateCourseService updateCourseService;

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

    @GetMapping("/")
    public ResponseEntity<Object> list(@RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {

        try {
            var courses = this.listCoursesService.execute(name, category);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro inesperado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody UpdateCourseRequestDTO updateRequestDTO) {
        try {
            CourseEntity updatedCourse = this.updateCourseService.execute(id, updateRequestDTO);
            return ResponseEntity.ok(updatedCourse);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro inesperado");
        }
    }

}
