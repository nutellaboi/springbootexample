package com.sandbox.testing.service;

import java.util.List;
import java.util.Map;

import com.sandbox.testing.entity.EmployeeTable;

public interface TestService {
	
	public List<EmployeeTable> getData();

	public int postData(EmployeeTable emp);

	public int deleteData(int id);

	public List<EmployeeTable> getDataById(int id);

	public List<EmployeeTable> putData(int id, Map<String, Object> emp);

}
