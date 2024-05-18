package br.com.capelaum.courses.exceptions;

import br.com.capelaum.courses.enums.CourseStatusEnum;

public class InvalidCourseStatusException extends RuntimeException {
    public InvalidCourseStatusException() {
        super("Status de Curso inválido. Status válidos: [" + CourseStatusEnum.values() + "]");
    }
}
