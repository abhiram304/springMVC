package edu.sjsu.cmpe275.lab2.dao;

import java.util.List;

import edu.sjsu.cmpe275.lab2.model.Phone;

public interface PhoneDAO {

		public abstract Phone findPhoneById(String id);

		public abstract void savePhone(Phone phone);
		
		public abstract void updatePhone(Phone phone);
		
		public abstract void deletePhoneById(String id);
		
		public abstract List<Phone> findAllPhones();
		
		public boolean checkPhoneExistById(String id);

}
