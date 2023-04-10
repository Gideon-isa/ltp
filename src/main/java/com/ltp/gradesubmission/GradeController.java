package com.ltp.gradesubmission;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Controller
public class GradeController {

  List<Grade> studentList = new ArrayList<>();

  // List<Grade> studentGrades = Arrays.asList(
  //   new Grade("Henry", "Computer Science", "A"),
  //   new Grade("Mark", "Art", "B"),
  //   new Grade("Jonah", "Chemistry", "A")

  // );
  
  /**
   * 
   * @param model
   * @return
   */
  @GetMapping("/grades")
  public String getGrades(Model model) {

  model.addAttribute("grad", studentList);
  
  return "grades";

  }
  /**
   * 
   * @param model
   * @param name
   * @return
   */
  @GetMapping("/form")
  public String gradeForm(Model model, @RequestParam(required = false) String name) {
    Grade grade = new Grade();
    // bind the object to the model
    model.addAttribute("formObject", 
    getGradeIndex(name) == -1000 ? grade : studentList.get(getGradeIndex(name)));
    return "form";
  }

  /**
   * 
   * @param grade
   * @return String
   */
  @PostMapping("/handleSubmit") 
  public String submitForm(Grade grade) {
    int index = getGradeIndex(grade.getName());
    if (index == -1000) {
      studentList.add(grade);
    }else{
      studentList.set(index, grade);
    }
    return "redirect:/grades";
    
  } 
  /**
   * 
   * @param name
   * @return Integer
   */
  public Integer getGradeIndex(String name) {
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).getName().equals(name)) {
        return i;
      }
    }
    return -1000;
  }
  
   
   
}
