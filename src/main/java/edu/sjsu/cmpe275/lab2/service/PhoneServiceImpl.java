package edu.sjsu.cmpe275.lab2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.sjsu.cmpe275.lab2.dao.PhoneDAO;
import edu.sjsu.cmpe275.lab2.model.Phone;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneDAO phoneDAO;
	
	public void setPhoneDAO(PhoneDAO phoneDAO) {
		this.phoneDAO = phoneDAO;
	}

	public Phone findPhoneById(String id) {
		return phoneDAO.findPhoneById(id);
	}

	public void savePhone(Phone phone) {
		phoneDAO.savePhone(phone);
	}

	public void updatePhone(Phone phone) {
		phoneDAO.updatePhone(phone);		
	}

	public void deletePhoneById(String id) {
		phoneDAO.deletePhoneById(id);		
	}

	public List<Phone> findAllPhones() {
		return phoneDAO.findAllPhones();
	}
	
	
	public boolean checkPhoneExistById(String id) {
		return phoneDAO.checkPhoneExistById(id);
	}
}
