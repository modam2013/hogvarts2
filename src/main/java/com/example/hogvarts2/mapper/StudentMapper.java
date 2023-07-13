package com.example.hogvarts2.mapper;

import com.example.hogvarts2.dto.StudentDtoIn;
import com.example.hogvarts2.dto.StudentDtoOut;
import com.example.hogvarts2.entity.Student;
import com.example.hogvarts2.exception.FacultyNotFoundException;
import com.example.hogvarts2.repository.FacultyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapper {
    private final FacultyMapper facultyMapper;
    private final FacultyRepository facultyRepository;

    public StudentMapper(FacultyMapper facultyMapper,
                         FacultyRepository facultyRepository) {
        this.facultyMapper = facultyMapper;
        this.facultyRepository = facultyRepository;
    }

    public StudentDtoOut toDto(Student student) {
        StudentDtoOut studentDtoOut = new StudentDtoOut();
        studentDtoOut.setId(student.getId());
        studentDtoOut.setName(student.getName());
        studentDtoOut.setAge(student.getAge());
        Optional.ofNullable(student.getFaculty())
                .ifPresent(faculty -> studentDtoOut.setFaculty(facultyMapper.toDto(faculty)));
        return studentDtoOut;
    }

    public Student toEntity(StudentDtoIn studentDtoIn) {
        Student student = new Student();
        student.setAge(studentDtoIn.getAge());
        student.setName(studentDtoIn.getName());
        Optional.ofNullable(studentDtoIn.getFacultyId()).ifPresent(facultyId ->
                facultyRepository.findById(facultyId)
                        .orElseThrow(() -> new FacultyNotFoundException(facultyId))
        );
        return student;
    }

}
