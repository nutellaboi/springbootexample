package com.sandbox.testing.dao;

import java.util.List;
import java.util.Map;

import com.sandbox.testing.entity.employee_table;

public interface TestDao {
	
	public List<Map<String, Object>> getEmpData();

	public int postEmpData(employee_table emp);

	public List<Map<String, Object>> deleteEmpData(int id);

	public List<Map<String, Object>> getEmpDataById(int id);

	public List<Map<String, Object>> putEmpData(String etString, int id);

}
