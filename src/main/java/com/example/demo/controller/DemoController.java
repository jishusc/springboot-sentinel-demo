package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * restful风格接口接入sentinel需要做url清洗
	 * 
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/basicInfo/searchEmployeeById/{employeeId}")
	public String searchEmployeeById(@PathVariable("employeeId") String employeeId) {
		return "employeeId:" + employeeId;
	}

	@GetMapping("/basicInfo/searchCompanyById")
	public String searchEmployeeById2(String companyId) {
		return "companyId:" + companyId;
	}
}
