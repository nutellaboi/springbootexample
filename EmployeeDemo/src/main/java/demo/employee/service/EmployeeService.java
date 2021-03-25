package demo.employee.service;

import java.util.List;
import java.util.Map;

import demo.employee.entity.EmployeeTable;

public interface EmployeeService {
	
	public List<EmployeeTable> getData();

	public int postData(EmployeeTable emp);

	public int deleteData(int id);

	public List<EmployeeTable> getDataById(int id);

	public List<EmployeeTable> putData(int id, Map<String, Object> emp);

}
