package demo.employee.dao;

import java.util.List;

import demo.employee.entity.EmployeeTable;
import demo.employee.entity.UsersTable;

public interface EmployeeDao {
	
	public List<EmployeeTable> getEmpData();

	public int postEmpData(EmployeeTable emp);

	public int deleteEmpData(int id);

	public List<EmployeeTable> getEmpDataById(int id);

	public List<EmployeeTable> putEmpData(String etString, int id);
	
	public String createUser(UsersTable user);

}
