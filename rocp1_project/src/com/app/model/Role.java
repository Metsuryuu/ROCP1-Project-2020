package com.app.model;

public class Role {
	
	private int roleId;	//primary key.
	private String role;	//not null, unique.
	
	public Role() {}
	
	public Role(String role) {
		super();
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
