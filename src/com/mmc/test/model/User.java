package com.mmc.test.model;

public class User {
	private String username;
	private String password;
	private int age;
	private String email;
	private String message;

	public User() {
		super();
	}

	public User(String username, String password, int age, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.email = email;
		this.message = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return message;
	}

	public void setAddress(String address) {
		this.message = address;
	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null || obj.getClass() != this.getClass()) {
			System.out.println("false");
			return false;
		} else if (this == obj) {
			return true;
		} else if (username.equals(((User)obj).getUsername())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
