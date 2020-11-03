package com.cg.jdbc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.cg.jdbc.EmployeePayrollService.IOService;

public class EmployeePayrollTest {
	@Test
	public void givenDatabase_ShouldReturn_SizeOfEmplList() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> empList = service.readEmployeePayrollData(IOService.DB_IO);
		System.out.println(empList);
		Assert.assertEquals(4, empList.size());
	}

	@Test
	public void givenUpdatedSalaryForEmployee_WhenUpdated_ShouldMatchWithDB() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = service.readEmployeePayrollData(IOService.DB_IO);
		service.updateEmployeeSalary("Terisa", 3000000.00);
		boolean result = service.checkEmployeePayrollInSyncWithDB("Terisa",3000000.00);
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenUpdatedSalaryForEmployee_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() {
		EmployeePayrollService service = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = service.readEmployeePayrollData(IOService.DB_IO);
		service.updateEmployeeSalaryUsingPrepareStatement("Terisa", 2000000.00);
		boolean result = service.checkEmployeePayrollInSyncWithDB("Terisa", 2000000.00);
		Assert.assertTrue(result);
	}

	@Test
	public void givenSpecifiesDateRange_WhenDataRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmployeePayrollData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> employeePayrollData = service
				.readEmployeeDetailsForDateRange(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(4, employeePayrollData.size());
	}
	
	@Test
	public void givenEmployeePayrollData_WhenRetrievedAvg_SalaryByGender_ShouldReturnValuesAsExpected() {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmployeePayrollData(IOService.DB_IO);
		Map<String, Double> averageSalaryByGender = service.readAverageSalaryByGender(IOService.DB_IO);
		Assert.assertTrue(
				averageSalaryByGender.get("M").equals(200.0) && averageSalaryByGender.get("F").equals(2000000.0));
	}
}
