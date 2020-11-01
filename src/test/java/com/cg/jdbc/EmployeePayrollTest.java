package com.cg.jdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class EmployeePayrollTest {
	@Test
	public void givenDatabase_ShouldReturn_SizeOfEmplList() {
		DBDemo db = new DBDemo();
		List<EmployeePayrollData> empList = db.readData();
		Assert.assertEquals(3, empList.size());
	}
}
