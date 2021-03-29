package com.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	
	private IStudentService StudentService;

	@Autowired
	public void setStudentService(IStudentService StudentService) {
		this.StudentService = StudentService;
	}
	
	// /index
	@RequestMapping(value="", method=RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("list", StudentService.listAllStudents());
        return model;
    }
	

	   @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	    public ModelAndView deleteStudents(@PathVariable long id) {
		   	StudentService.deleteStudent(id);
	        return new ModelAndView("redirect:/index");
	    }
	   

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView StudentRegister(@ModelAttribute("Student")Student Student){
    	ModelAndView model = new ModelAndView("index");
    	if(Student!=null){
    	StudentService.saveStudent(Student);
    	model.addObject("warning", "Student Registration Success");
    	
    	}
    	else{
    		model.addObject("danger","Something Going Bad" );
    		
    	}
    	return new ModelAndView("redirect:/index");
    }
    	  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    	    public ModelAndView edit(@PathVariable("id") long id) {
    		  ModelAndView model = new ModelAndView("edit");
    	        Student Student = StudentService.getStudentById(id);
    	        model.addObject("Student", Student);
    	        return model;
    	    }
    	  
    	   @RequestMapping(value = "/update", method = RequestMethod.POST)
    	    public ModelAndView update(@RequestParam("id") long id,
    	                               @RequestParam("name") String name,@RequestParam("surname") String surname,
    	    						   @RequestParam("adress") String adress) {
    	        Student Student = StudentService.getStudentById(id);
    	        Student.setName(name);
    	        Student.setSurname(surname);
    	        Student.setAdress(adress);
    	        StudentService.saveStudent(Student);
    	        return new ModelAndView("redirect:/index");
    	    }
   
}
