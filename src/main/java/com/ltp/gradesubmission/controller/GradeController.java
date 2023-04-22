package com.ltp.gradesubmission.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.service.GradeService;

@Controller
public class GradeController {

  @Autowired
  GradeService gradeService;

  // List<Grade> studentGrades = Arrays.asList(
  //   new Grade("Henry", "Computer Science", "A"),
  //   new Grade("Mark", "Art", "B"),
  //   new Grade("Jonah", "Chemistry", "A")

  // );


    /**
   * 
   * @param model
   * @param name
   * @return
   */
  @GetMapping("/")
  public String gradeForm(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("formObject", gradeService.getGradeById(id));
    return "form";
  }
  
  /**
   * 
   * @param model
   * @return
   */
  @GetMapping("/grades")
  public String getGrades(Model model) {

    model.addAttribute("grad", gradeService.getGrades());
    
    return "grades";

  }


  /**
   * 
   * @param grade
   * @return String
   */
  @PostMapping("/handleSubmit") 
  public String submitForm(@Valid @ModelAttribute("formObject") Grade grade, BindingResult result) {
    if (result.hasErrors()) {
      return "form";
    }
    gradeService.submitGrade(grade);

    return "redirect:/grades";
    
  } 

  
  
   
   
}
