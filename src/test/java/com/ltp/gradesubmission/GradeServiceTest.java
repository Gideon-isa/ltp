package com.ltp.gradesubmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;


@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    // @Mock createsn the GradeRepository
    @Mock
    private GradeRepository gradeRepository;

    // @InjectMocks creates an object and injects the mock (i.e. in this case, it the gradeRepository)
    // you created and injects it into the gradeService mock objec object
    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        // Arrange: Mock the data needed to carry out the unit test
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Chemistry", "A+"),
            new Grade("Hermione", "Arithmancy", "A+")
        ));

        //Act: Call the method that you want to test
        List<Grade> result = gradeRepository.getGrades();

        //Assert: Check if the method is behaving correctly
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Arithmancy", result.get(1).getSubject());
    }

    @Test
    public void gradeIndex() {
        // Arrange: Mock the data needed to carry out the unit test

        //Grade grade = new Grade("Harry", "Chemistry", "A+");
        
        // This is where you mock: eg the getGrades() in the getGradeIndex method is mocked
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Chemistry", "A+"),
            new Grade("Hermione", "Arithmancy", "A+")
        ));

        // when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        // when(gradeRepository.getGrade(0)).thenReturn(grade);

        List<Grade> result = gradeService.getGrades();

        // Act:

        // int valid = gradeService.getGradeIndex(grade.getId());
        // int notFound = gradeService.getGradeIndex("123");

        int valid = gradeService.getGradeIndex(result.get(0).getId());
        int notFound = gradeService.getGradeIndex("12");

        // Assert:
        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        // Arrange:
        //Grade grade = new Grade("Harry", "Chemistry", "A+");

        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Chemistry", "A+"),
            new Grade("Hermione", "Arithmancy", "A+")
        ));

        List<Grade> result = gradeService.getGrades();
        when(gradeRepository.getGrade(0)).thenReturn(result.get(0));
        //when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));

        
        // 
        String id = result.get(0).getId();
        Grade resu = gradeService.getGradeById(id);

        // 
        assertEquals(result.get(0), resu);
    }

    @Test
    public void addGradeTest() {
        Grade grade = new Grade("Harry", "Mathematics", "A+");

        // Arrange
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Hermione", "Arithmancy", "A+");
        gradeService.submitGrade(newGrade);
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        Grade grade = new Grade("Harry", "Mathematics", "A+");

        // Arrange
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        grade.setScore("A-");
        gradeService.submitGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(grade, 0);

    }
    
}
