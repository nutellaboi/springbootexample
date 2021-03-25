package demo.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import demo.employee.entity.EmployeeTable;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

	@Autowired
	JdbcTemplate jdbc;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<EmployeeTable> getEmpData() {
		// TODO Auto-generated method stub
		String sql="SELECT employee_id,employee_name,phone,address FROM employee_table";
		//List<Map<String,Object>> EmpList=jdbc.queryForList(sql);
		List<EmployeeTable> EmpList=jdbc.query(sql, new BeanPropertyRowMapper(EmployeeTable.class));
		return EmpList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<EmployeeTable> getEmpDataById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT employee_id,employee_name,phone,address FROM employee_table WHERE employee_id=?";
		List<EmployeeTable> EmpList=jdbc.query(sql,new BeanPropertyRowMapper(EmployeeTable.class), new Object[] {id});
		return EmpList;
	}

	@Override
	public int postEmpData(EmployeeTable emp) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO employee_table(employee_name,phone,address) VALUES "
				+ "('"+emp.getEmployee_name()+"','"+emp.getPhone()+"','"+emp.getAddress()+"')";
		int val=jdbc.update(sql);
		return val;
	}

	@Override
	public List<EmployeeTable> putEmpData(String etString, int id) {
		// TODO Auto-generated method stub
		String sql="UPDATE employee_table SET "+etString+" WHERE employee_id="+id;
		jdbc.update(sql);
		return getEmpDataById(id);
	}

	@Override
	public int deleteEmpData(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM employee_table WHERE employee_id=? ";
		int val=jdbc.update(sql, new Object[] {id});
		return val;
	}


}
