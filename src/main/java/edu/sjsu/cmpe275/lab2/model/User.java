package edu.sjsu.cmpe275.lab2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")

public class User implements Serializable{
	private static final long serialVersionUID = -9022929256211682745L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private int userId;
	@Column(name = "FIRSTNAME")
	private String firstName;
	@Column(name = "LASTNAME")
	private String lastName;
	@Column(name = "TITLE")
	private String title;
	@Embedded
	private Address address;
	@ManyToMany(mappedBy = "users", fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	/*@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(name="USERS_PHONES", joinColumns={@JoinColumn(name="userId", referencedColumnName = "USERID")},
	inverseJoinColumns={@JoinColumn(name="phoneId", referencedColumnName= "PHONEID")})*/
	private List<Phone> phones;
	
	public User() {
		super();
	}

	public User(int userId, String firstName, String lastName, String title, Address address, List<Phone> phones) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.address = address;
		this.phones = phones;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/*@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", address=" + address + ", phones=" + phones + "]";
	}*/

}
