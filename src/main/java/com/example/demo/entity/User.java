package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "m_user")
@Data
public class User {
	
	@Id
	@Column(name = "eid")
	private String eid;
	
	@Column(name = "ename")
	private String ename;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "mailaddress")
	private String mailaddress;
	
	@Column(name = "station")
	private String station;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "sendchk")
	private Integer sendchk;
	
	@Column(name = "possibleamt")
	private Integer possibleamt;

}
