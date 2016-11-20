package edu.sjsu.cmpe275.lab2.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Phone;
import edu.sjsu.cmpe275.lab2.model.User;
import edu.sjsu.cmpe275.lab2.service.PhoneService;
import edu.sjsu.cmpe275.lab2.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PhoneService phoneService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	/*TEST*/
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
	
	/*GET CREATE NEW USER PAGE*/
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createNewUser(ModelMap model) {
		ModelAndView userFound= new ModelAndView("createNewUser");
		return userFound;
	}
	
	
	/*GET UPDATE USER PAGE*/
	@RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserById(@PathVariable("userId") String id, Model model) {
		System.out.println("I am in updateUser "+Integer.parseInt(id));
		ModelAndView userFound= new ModelAndView("updateUser");
		ModelAndView userNotFound= new ModelAndView("userNotFound");
		boolean flag = userService.checkUserPresentById(id);
		System.out.println("flag "+flag);
		if(!flag){
	        System.out.println("Unable to update as user with id "+id+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return userNotFound;
	    }
		User user = userService.findUserById(id);
		System.out.println("User: "+user);
		model.addAttribute("user", user);
		model.addAttribute("httpStatus", HttpStatus.OK);
		return userFound;	
	}
	
	/*@RequestMapping(value="/user/{userId}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("userId") String id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("httpStatus", HttpStatus.OK);
		return "viewUser";
	}*/
	
	/*CREATE NEW USER ON CLICKING CREATE IN CREATENEW USER PAGE*/
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public String createNewUser(@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="title", required=false) String title, 
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zip", required=false) String zip,
			UriComponentsBuilder ucBuilder, Model model) {
		Address address = new Address();
		User user = new User();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		user.setAddress(address);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setTitle(title);
		userService.saveUser(user);//It should return user to get id.
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		model.addAttribute("headers", headers);
		model.addAttribute("httpStatus", HttpStatus.CREATED);
		return "userCreationSuccess";
	}
	
	
	/*UPDATE USER ON CLICKING UPDATE IN UPDATEUSER PAGE*/
	@RequestMapping(value="/user/{userId}", method = RequestMethod.POST)
	public String updateUser (@RequestParam(value="userId", required=false) String userid,
	@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="title", required=false) String title, 
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zip", required=false) String zip,
			@PathVariable("userId") String id,
			Model model) {
		System.out.println("IN UPDATE METHOD");
		if(!userService.checkUserPresentById(userid)){
	        System.out.println("Unable to update as user with id "+userid+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return "userNotFound";
	    }
		User user = userService.findUserById(userid);
		Address address = new Address();
		if(city.isEmpty())
			address.setCity(city);			
		if(state.isEmpty())
			address.setState(state);
		if(street.isEmpty())
			address.setStreet(street);
		if(zip.isEmpty())
			address.setZip(zip);
		if(address != null)
			user.setAddress(address);
		if(firstName.isEmpty())
			user.setFirstName(firstName);
		if(lastName.isEmpty())
			user.setLastName(lastName);
		if(title.isEmpty())
			user.setTitle(title);
		if(user != null){
		userService.updateUser(user);
		model.addAttribute("httpStatus", HttpStatus.OK);
		System.out.println("No user found to update");
		return "userUpdatedSuccessfully";
		}
		return "userUpdatedSuccessfully";
	}
	
	/*DELETE AN EXISTING USER*/
	@RequestMapping(value="/user/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("userId") String id, Model model) {
		if(!userService.checkUserPresentById(id)){
	        System.out.println("A user with id "+id+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return "userNotFound";
	    }
		userService.deleteUserById(id);
		model.addAttribute("httpStatus", HttpStatus.OK);
		return "userDeleteSuccess";
	}
	
	
	/*GET USER DETAILS IN JSON FORMAT*/
	@RequestMapping(value="/getJSON/user/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User fetchUserByIdAsJSON(@PathVariable("userId") String id, 
			@RequestParam(value="json", required=true) boolean json, Model model) {
		if(json){
			if(!userService.checkUserPresentById(id)){
				System.out.println("Unable to fetch as user with id "+id+" doesnot exist");
		        return null;
		    }
			User user = userService.findUserById(id);
			System.out.println("User: "+user);
			return user;
		}else{
			System.out.println("Unable to fetch as json parameter has not been set properly");
			return null;
		}
	}
	
	/*VIEW ALL USERS*/
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> userList = userService.findAllUsers();
		model.addAttribute("httpStatus", HttpStatus.OK);
		model.addAttribute("userList", userList);
		return "viewAllUsers";
	}
	
	
	
	
	
	/*GET CREATE PHONE PAGE*/
	@RequestMapping(value="/createPhone", method = RequestMethod.GET)
	public String createNewPhone(Model model) {
		List<Phone> phoneList = phoneService.findAllPhones();
		List<User> userList = userService.findAllUsers();
		model.addAttribute("httpStatus", HttpStatus.OK);
		model.addAttribute("userList", userList);
		return "createNewPhone";
	}
	
	
	/*GET PHONE DETAILS IN JSON FORMAT*/
	@RequestMapping(value="/getJSON/phone/{phoneId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Phone fetchPhoneByIdAsJSON(@PathVariable("phoneId") String id, 
			@RequestParam(value="json", required=true) boolean json, Model model) {
		if(json){
			if(!phoneService.checkPhoneExistById(id)){
				System.out.println("Unable to fetch as user with id "+id+" doesnot exist");
		        return null;
		    }
			Phone phone = phoneService.findPhoneById(id);
			System.out.println("Phone: "+phone);
			return phone;
		}else{
			System.out.println("Unable to fetch as json parameter has not been set properly");
			return null;
		}
	}
	
	@ModelAttribute
	public void attachTitle(Model model){
		model.addAttribute("titleMessage", "Group 21: Pratik Pandey & Abhi Ram Salammagari");
	}
	
	
	/*CREATE PHONE FROM CREATE PHONE PAGE*/
	@RequestMapping(value="/phone", method = RequestMethod.POST)
	public String createNewPhone(@RequestParam(value="number", required=false) String number,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zip", required=false) String zip,
			@RequestParam(value="userId")String[] userIds,
			UriComponentsBuilder ucBuilder, Model model) {
		System.out.println("Users : "+userIds[0]);
		Address address = new Address();
		Phone phone = new Phone();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		phone.setAddress(address);
		phone.setNumber(number);
		phone.setDescription(description);
		List<User> usersList= new ArrayList<User>();
		for(int i=0;i<userIds.length;i++){
			User user = userService.findUserById(userIds[i]);
			//int and=user.getUserId();
			usersList.add(user);
			System.out.println("User being added: "+usersList);
		}
		phone.setUsers(usersList);
		phoneService.savePhone(phone);
		System.out.println("Phone "+phone.toString());
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/phone/{id}").buildAndExpand(phone.getPhoneId()).toUri());
		model.addAttribute("headers", headers);
		model.addAttribute("httpStatus", HttpStatus.CREATED);
		return "phoneCreationSuccess";
	}
	
	
	
	
	/*GET PHONE WITH ID AND UPDATE----------------*/
	@RequestMapping(value="/phone/{phoneId}", method = RequestMethod.GET)
	public ModelAndView getPhoneById(@PathVariable("phoneId") String id, Model model) {
		ModelAndView phoneFound= new ModelAndView("updatePhone");
		ModelAndView phoneNotFound= new ModelAndView("phoneNotFound");
		boolean flag = phoneService.checkPhoneExistById(id);
		if(!flag){
	        System.out.println("Unable to update as the phone with id "+id+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return phoneNotFound;
	    }
		
			/*
			System.out.println("Users in the getPhoneById : "+userLst.toString());
			
			*/
			List<User> userLst = userService.findAllUsers();
			model.addAttribute("userList", userLst);
			
			Phone phone = phoneService.findPhoneById(id);
			model.addAttribute("phone", phone);	
			model.addAttribute("httpStatus", HttpStatus.OK);
			return phoneFound;
			}
	
	/*UPDATE PHONE ON CLICKING UPDATE BUTTON IN UPDATE PHONE PAGE*/
	@RequestMapping(value="/phone", method = RequestMethod.PUT)
	public String updatePhone(@RequestParam(value="number", required=false) String number,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="street", required=false) String street,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="zip", required=false) String zip,
			@PathVariable("phoneId") String id,
			@RequestParam(value = "userId", required = true) long[] userIds,
			Model model) {
		Phone phone = phoneService.findPhoneById(id);
		System.out.println("USER IDS: "+userIds);
		if(phone == null){
	        System.out.println("Unable to update as phone with id "+id+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return "viewPhone";
	    }
		Address address = new Address();
		if(city.isEmpty())
			address.setCity(city);			
		if(state.isEmpty())
			address.setState(state);
		if(street.isEmpty())
			address.setStreet(street);
		if(zip.isEmpty())
			address.setZip(zip);
		if(address != null)
			phone.setAddress(address);
		if(number.isEmpty())
			phone.setNumber(number);
		if(description.isEmpty())
			phone.setDescription(description);
		if(phone != null){
			phoneService.updatePhone(phone);
			model.addAttribute("httpStatus", HttpStatus.OK);
		}
		return "viewPhone";
	}
	
	
	@RequestMapping(value="/phone/{phoneId}", method = RequestMethod.DELETE)
	public String deletePhoneById(@PathVariable("phoneId") String id, Model model) {
		if(phoneService.findPhoneById(id) == null){
	        System.out.println("An phone with id "+id+" doesnot exist");
	        model.addAttribute("httpStatus", HttpStatus.NOT_FOUND);
			return "viewPhone";
	    }
		phoneService.deletePhoneById(id);
		model.addAttribute("httpStatus", HttpStatus.OK);
		return "viewPhone";
	}
	
	@RequestMapping(value="/phone", method = RequestMethod.GET)
	public String getAllPhones(Model model) {
		List<Phone> phoneList = phoneService.findAllPhones();
		model.addAttribute("httpStatus", HttpStatus.OK);
		model.addAttribute("phoneList", phoneList);
		return "viewPhone";
	}
}
