package gr.hua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String findAuthorityByUsername(String username) {
		return userRepository.findAuthorityByUsername(username);
	}
}
