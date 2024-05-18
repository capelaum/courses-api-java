package br.com.capelaum.courses.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Curso não encontrado");
    }
}
