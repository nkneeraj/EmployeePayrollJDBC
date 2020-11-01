package com.cg.jdbc;

import java.time.LocalDate;

public class EmployeePayrollData {
	public int id;
	public String name;
	public double salary;
	public LocalDate start;

	public EmployeePayrollData(int id, String name, double basic_pay, LocalDate start) {
		this.id = id;
		this.name = name;
		this.salary = basic_pay;
		this.start = start;
	}

	@Override
	public String toString() {
		return " id: " + id + " name: " + name + " basic_pay: " + salary + " startdate: " + start;
	}

} 
