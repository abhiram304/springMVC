package edu.sjsu.cmpe275.lab2.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import edu.sjsu.cmpe275.lab2.model.Phone;

@Repository
public class PhoneDAOImpl extends AbstractDao<String, Phone> implements PhoneDAO  {
	
	/*@Autowired
    SessionFactory sessionFactory;*/

	public Phone findPhoneById(String id) {
		return findById(id);
	}

	public void savePhone(Phone phone) {
		save(phone);		
	}

	public void updatePhone(Phone phone) {
		update(phone);		
	}

	public void deletePhoneById(String id) {
		deleteById(id);
	}

	public List<Phone> findAllPhones() {
		List<Phone> list = findAll();
		return list;
	}
	
	public boolean checkPhoneExistById(String id) {
		return checkById(id);
	}
}
