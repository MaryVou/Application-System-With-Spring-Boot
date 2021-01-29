package gr.hua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.service.ApplicationService;

@RestController
public class ApplicationRest {

	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/api/applications/new")
	public ResponseEntity makeApplication(@RequestBody String applicationJson) {
		
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		
		ObjectMapper mapper = new ObjectMapper();
		Application application = null;
		try {
			application = mapper.readValue(applicationJson, Application.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(application!=null) {
			
			//TODO check if days are enough and then add
			//else return error message
			
			applicationService.addApplication(application, connected_user);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST); 
	}
	
	@GetMapping("/api/applications/view")
	public List<ApplicationResponse> viewApplications() {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		return applicationService.findPersonalApplications(connected_user);
	}
}
