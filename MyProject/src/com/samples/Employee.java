package com.samples;

public class Employee {
    private String name;
    private String designation;
    private String department;
    private String [] address;

    public String[] getAddress() {
		return address;
	}
	public void setAddress(String[] address) {
		this.address = address;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return "Name : "+this.name+
        "\nDesignation : "+this.designation+
        "\nDepartment : "+this.department;
    }
}
