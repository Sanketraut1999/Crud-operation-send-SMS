package com.example.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.Model.CrudModel;
import com.example.crud.dto.CrudDto;
import com.example.crud.service.CrudService;


@RestController
public class CrudController {
	@Autowired
	private CrudService crudService;

	@PostMapping("/crud")
	public ResponseEntity<String> cruds(@RequestBody CrudDto crudDto) {
		return crudService.cruds(crudDto);
	}

	@GetMapping("/getAll")
	public List<CrudModel> getData() {
		return crudService.getData();
	}
	@GetMapping("/findById/{userid}")
	public Optional<CrudModel> getById(@PathVariable("userid") Long userid) {
		return crudService.findById(userid);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId, @RequestBody CrudDto crudDto) {
		return crudService.updateUser(userId, crudDto);
	}

	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") Long userid) {
		return crudService.deleteUser(userid);
	}

	@Value("${app.twillio.fromPhoneNo}")
	private String from;

	@Value("${app.twillio.toPhoneNo}")
	private String to;

	@GetMapping("/sendSms")
	public String sendSms() {
		int min = 10000000;
		int max = 99999999;
		long msg = (long) (Math.random() * (max - min + 1) + min);
		String otp = "Your otp :" + msg;
		crudService.sendSms(to, from, otp);
		return "message sent successfully";

	}

}
