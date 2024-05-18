package br.com.capelaum.courses.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capelaum.courses.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    List<CourseEntity> findByNameContaining(String name);

    List<CourseEntity> findByCategoryContaining(String category);

    List<CourseEntity> findByNameContainingAndCategoryContaining(String name, String category);
}
