package com.example.hogvarts2.service;

import com.example.hogvarts2.dto.FacultyDtoIn;
import com.example.hogvarts2.dto.FacultyDtoOut;
import com.example.hogvarts2.entity.Faculty;
import com.example.hogvarts2.mapper.FacultyMapper;
import com.example.hogvarts2.mapper.StudentMapper;
import com.example.hogvarts2.repository.FacultyRepository;
import com.example.hogvarts2.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    private FacultyRepository facultyRepository;
    private FacultyService facultyService;

    @BeforeEach
    public void beforeEach() {
        facultyRepository = mock(FacultyRepository.class);
        FacultyMapper facultyMapper = new FacultyMapper();
        facultyService = new FacultyService(
                facultyRepository,
                mock(StudentRepository.class),
                facultyMapper,
                new StudentMapper(facultyMapper, facultyRepository)
        );
    }

    @Test
    public void createTest() {
        FacultyDtoIn facultyDtoIn = new FacultyDtoIn();
        facultyDtoIn.setName("name");
        facultyDtoIn.setColor("color");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("name");
        faculty.setColor("color");

        FacultyDtoOut expected = new FacultyDtoOut();
        expected.setId(1L);
        expected.setName("name");
        expected.setColor("color");

        when(facultyRepository.save(any())).thenReturn(faculty);

        FacultyDtoOut actual = facultyService.create(facultyDtoIn);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
        verify(facultyRepository, new Times(1)).save(any());
    }

}
