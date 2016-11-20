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
@Table(name="PHONE")
public class Phone implements Serializable{
	private static final long serialVersionUID = -9022929256211682745L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PHONEID")
	private int phoneId;
	@Column(name = "NUMBER")
	private String number;
	@Column(name = "DESCRIPTION")
	private String description;
	@Embedded
	private Address address;
	/*@ManyToMany(mappedBy = "phones")*/
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinTable(name="USERS_PHONES", joinColumns={@JoinColumn(name="phoneId", referencedColumnName = "PHONEID")},
	inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName= "USERID")})

	
	
	private List<User> users;
	
	public Phone() {
		super();
	}

	public Phone(int phoneId, String number, String description, Address address, List<User> users) {
		super();
		this.phoneId = phoneId;
		this.number = number;
		this.description = description;
		this.address = address;
		this.users = users;
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
		public List<User> getUsers() {
		return users;
	}

	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/*@Override
	public String toString() {
		return "Phone [phoneId=" + phoneId + ", number=" + number + ", description=" + description + ", address="
				+ address + ", users=" + users + "]";
	}*/

		
}
