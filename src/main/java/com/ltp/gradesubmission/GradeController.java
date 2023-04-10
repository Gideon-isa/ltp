package com.ltp.gradesubmission;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

  List<Grade> studentList = new ArrayList<>();

  // List<Grade> studentGrades = Arrays.asList(
  //   new Grade("Henry", "Computer Science", "A"),
  //   new Grade("Mark", "Art", "B"),
  //   new Grade("Jonah", "Chemistry", "A")

  // );
  

  @GetMapping("/grades")
  public String getGrades(Model model) {

  model.addAttribute("grad", studentList);
  
  return "grades";

  }

  @GetMapping("/form")
  public String gradeForm(Model model) {
    model.addAttribute("formObject", new Grade());
    return "form";
  }

  @PostMapping("/handleSubmit") 
    public String submitForm(Grade grade) {
      studentList.add(grade);
       return "redirect:/grades";
      
    } 
  
   
   
}
