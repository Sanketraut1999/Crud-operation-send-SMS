package com.example.crud.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud.Dao.CrudDao;
import com.example.crud.Model.CrudModel;
import com.example.crud.dto.CrudDto;
import com.example.crud.service.CrudService;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class CrudServiceImpl implements CrudService {
	@Autowired
	private CrudDao crudDao;

	@Override
	public ResponseEntity<String> cruds(CrudDto crudDto) {
		CrudModel crudModel = new CrudModel();

		crudModel.setFirstName(crudDto.getFirstName());
		crudModel.setLasttName(crudDto.getLasttName());
		crudModel.setEmail(crudDto.getEmail());
		crudModel.setPhone(crudDto.getPhone());

		crudDao.save(crudModel);
		return new ResponseEntity<>("Registered Sucessfully", HttpStatus.OK);
	}

	@Override
	public List<CrudModel> getData() {

		return crudDao.findAll();
	}
	  @Override
		public Optional<CrudModel> findById(Long userId) {
			Optional<CrudModel> optional=crudDao.findById(userId);
			return optional;
		}
	@Override
	public ResponseEntity<String> deleteUser(Long userid) {
		crudDao.deleteById(userid);
		return new ResponseEntity<>("User Data Delete Successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateUser(Long userId, CrudDto crudDto) {
		ResponseEntity<String> upd = new ResponseEntity<>("", HttpStatus.OK);

		CrudModel crudModel = crudDao.getByUserId(userId);

		crudModel.setFirstName(crudDto.getFirstName());
		crudModel.setLasttName(crudDto.getLasttName());
		crudModel.setEmail(crudDto.getEmail());
		crudModel.setPhone(crudDto.getPhone());
		crudDao.save(crudModel);
		upd = new ResponseEntity<>("User Data update successfully", HttpStatus.OK);

		return upd;

	}

	@Value("${app.twillio.accountSID}")
	private String ACCOUNT_SID;

	@Value("${app.twillio.authToken}")
	private String AUTH_TOKEN;

	@Override
	public void sendSms(String to, String from, String otp) {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(from), otp).create();

	}

	
}
