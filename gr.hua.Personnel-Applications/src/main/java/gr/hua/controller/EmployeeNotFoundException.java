package gr.hua.controller;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String exception) {
		super(exception);
	}

}
