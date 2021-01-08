package gr.hua.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ApplicationResponse> applications = applicationService.retrieveApplications(connected_user);
		List<ApplicationResponse> history = applicationService.retrieveHistory(connected_user);
		model.addAttribute("allApplications", applications);
		model.addAttribute("history", history);
		return "viewApplications";
	}
	
	@GetMapping("/applications/accept/{id}")
	public String acceptApplication(@PathVariable("id") int id) {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		applicationService.acceptApplication(id,connected_user);
		return "redirect:/applications";
	}
	
	@GetMapping("/applications/reject/{id}")
	public String rejectApplication(@PathVariable("id") int id, Model model) {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		applicationService.rejectApplication(id,connected_user);
		return "redirect:/applications";
	}
}
