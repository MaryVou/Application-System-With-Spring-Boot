package gr.hua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public List<ApplicationResponse> retrieveAllApplications() {
		return applicationRepository.findAllApplications();
	}

	public List<ApplicationResponse> retrieveApplicationsForSupervisor() {
		return applicationRepository.findApplicationsForSupervisor();
	}

	public List<ApplicationResponse> retrieveApplicationsForPDEmployee() {
		return applicationRepository.findApplicationsForPDEmployee();
	}

	public List<ApplicationResponse> retrieveApplicationsForManager() {
		return applicationRepository.findApplicationsForManager();
	}
}
