package edu.sjsu.cmpe275.lab2.service;

import java.util.List;

import edu.sjsu.cmpe275.lab2.model.Phone;

public interface PhoneService {
	
	public abstract Phone findPhoneById(String id);

	public abstract void savePhone(Phone phone);
	
	public abstract void updatePhone(Phone phone);
	
	public abstract void deletePhoneById(String id);
	
	public abstract List<Phone> findAllPhones();
	
	public boolean checkPhoneExistById(String id);

}
