package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.crud.Model.CrudModel;
import com.example.crud.dto.CrudDto;

public interface CrudService {

	ResponseEntity<String> cruds(CrudDto crudDto);

	List<CrudModel> getData();

	ResponseEntity<String> deleteUser(Long userid);

	ResponseEntity<String> updateUser(Long userId, CrudDto signUpDto);

	void sendSms(String to, String from, String otp);

	Optional<CrudModel> findById(Long userid);

}
