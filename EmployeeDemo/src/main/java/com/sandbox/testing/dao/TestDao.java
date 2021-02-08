package com.sandbox.testing.dao;

import java.util.List;

import com.sandbox.testing.entity.EmployeeTable;

public interface TestDao {
	
	public List<EmployeeTable> getEmpData();

	public int postEmpData(EmployeeTable emp);

	public int deleteEmpData(int id);

	public List<EmployeeTable> getEmpDataById(int id);

	public List<EmployeeTable> putEmpData(String etString, int id);

}
