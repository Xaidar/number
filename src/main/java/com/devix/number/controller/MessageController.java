package com.devix.number.controller;

import com.devix.number.generator.RandomGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@RequestMapping(value= "/number/random", method = RequestMethod.GET)
	public String random() {
		return RandomGenerator.generate();
	}
}
