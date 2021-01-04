package gr.hua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.service.ApplicationService;

@Controller
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping("/applications")
	public String retrieveAllApplications(Model model){
		List<ApplicationResponse> allApplications = applicationService.retrieveAllApplications();
		List<ApplicationResponse> applicationsForSupervisor = applicationService.retrieveApplicationsForSupervisor();
		List<ApplicationResponse> applicationsForPDEmployee = applicationService.retrieveApplicationsForPDEmployee();
		List<ApplicationResponse> applicationsForManager = applicationService.retrieveApplicationsForManager();
		model.addAttribute("allApplications", allApplications);
		model.addAttribute("applicationsForSupervisor", applicationsForSupervisor);
		model.addAttribute("applicationsForPDEmployee", applicationsForPDEmployee);
		model.addAttribute("applicationsForManager", applicationsForManager);
		return "viewApplications";
	}
	
	
}
