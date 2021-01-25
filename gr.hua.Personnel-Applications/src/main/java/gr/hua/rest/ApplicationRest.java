package gr.hua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.service.ApplicationService;

@RestController
public class ApplicationRest {

	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/api/applications/new")
	public void makeApplication(@RequestBody Application application) {
		//Application application = ObjectMapper().readValue(applicationJson.toString(), Application.class);
		applicationService.addApplication(application);
	}
	
	@GetMapping("/api/applications/view")
	public List<ApplicationResponse> viewApplications() {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		return applicationService.findPersonalApplications(connected_user);
	}
}
