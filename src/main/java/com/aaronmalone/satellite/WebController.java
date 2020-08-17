package com.aaronmalone.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

  @Autowired
  private ObservationRepository observationRepository;

  @GetMapping(path = "/todo-rename-me-obs")
  public ModelAndView allObservations() {
    ModelAndView mav = new ModelAndView("all-observations");
    mav.addObject("observations", observationRepository.findAll());
    return mav;
  }
}
