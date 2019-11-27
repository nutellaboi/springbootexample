package com.sandbox.testing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sandbox.testing.entity.employee_table;

@Repository
public class TestDaoImp implements TestDao {

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public List<Map<String, Object>> getEmpData() {
		// TODO Auto-generated method stub
		String sql="SELECT employee_id,employee_name,phone,address FROM employee_table";
		List<Map<String,Object>> EmpList=jdbc.queryForList(sql);
		return EmpList;
	}

	@Override
	public List<Map<String, Object>> putEmpData(String etString, int id) {
		// TODO Auto-generated method stub
		String sql="UPDATE employee_table SET "+etString+" WHERE employee_id="+id;
		jdbc.update(sql);
		return getEmpDataById(id);
	}

	@Override
	public int postEmpData(employee_table emp) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO employee_table(employee_name,phone,address) VALUES"
				+ " ('"+emp.getEmployee_name()+"','"+emp.getPhone()+"','"+emp.getAddress()+"')";
		int val=jdbc.update(sql);
		return val;
	}

	@Override
	public List<Map<String, Object>> deleteEmpData(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM employee_table WHERE employee_id=? ";
		jdbc.update(sql, new Object[] {id});
		return getEmpData();
	}

	@Override
	public List<Map<String, Object>> getEmpDataById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT employee_id,employee_name,phone,address FROM employee_table WHERE employee_id="+id;
		List<Map<String,Object>> EmpList=jdbc.queryForList(sql);
		return EmpList;
	}

}
