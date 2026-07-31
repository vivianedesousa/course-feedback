package br.com.coursefeedback.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCourseRequestDTO {
    // NOTblank cobre se nao e null, se ta vazio , espaco em braco
    @NotBlank(message = "The course name is required.")
    @Size(
            min = 3,
            max = 100,
            message = "The course name must contain between 3 and 100 characters."
    )
    private String name;

    @NotBlank(message = "The course description is required.")
    @Size(
            min = 10,
            max = 150,
            message = "The course description must contain between 10 and 200 characters."
    )
    private String description;

    public CreateCourseRequestDTO() {
    }

    public CreateCourseRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
