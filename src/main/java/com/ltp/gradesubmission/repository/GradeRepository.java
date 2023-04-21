package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import com.ltp.gradesubmission.Grade;

public class GradeRepository {
    // private - should not be directly accessible  
    private List<Grade> studentList = new ArrayList<>();

    public Grade getGrade(int index) {
        return studentList.get(index);
    }

    public void addGrade( Grade grade) {
        studentList.add(grade);
    }

    public void updateGrade(Grade grade, int index) {
        studentList.set(index, grade);
    }

    public List<Grade> getGrades() {
        return studentList;
    }
}
