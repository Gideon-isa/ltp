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
  @GetMapping("/")
  public String gradeForm(Model model, @RequestParam(required = false) String id) {
    int index = getGradeIndex(id);
    // bind the Grade object to the model with name "formObject"
    model.addAttribute("formObject", index == Constants.NOT_FOUND ? new Grade() : index);
    return "form";
  }

  /**
   * 
   * @param grade
   * @return String
   */
  @PostMapping("/handleSubmit") 
  public String submitForm(Grade grade) {
    int index = getGradeIndex(grade.getId());
    if (index == Constants.NOT_FOUND ) {
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
  public Integer getGradeIndex(String id) {
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return Constants.NOT_FOUND ;
  }
  
   
   
}
