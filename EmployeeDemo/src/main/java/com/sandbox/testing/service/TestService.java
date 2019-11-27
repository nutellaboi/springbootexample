package com.sandbox.testing.service;

import java.util.List;
import java.util.Map;

import com.sandbox.testing.entity.employee_table;

public interface TestService {
	
	public List<Map<String,Object>> getData();

	public int postData(employee_table emp);

	public List<Map<String, Object>> deleteData(int id);

	public List<Map<String, Object>> getDataById(int id);

	public List<Map<String, Object>> putData(int id, Map<String, Object> emp);

}
