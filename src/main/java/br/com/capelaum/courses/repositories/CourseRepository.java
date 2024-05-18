package br.com.capelaum.courses.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capelaum.courses.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
