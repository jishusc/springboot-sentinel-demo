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

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/hello1")
	public String hello1() {
		return "hello1";
	}
	
	@GetMapping("/helloDelay/{delay}")
	public String helloDelay(@PathVariable("delay") int delay) {
		try {
			delay = delay / 2 + (int) (Math.random() * delay / 2);
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "delayed " + delay + "ms";
	}
	

}
