package com.sandbox.testing.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.testing.dao.TestDaoImp;
import com.sandbox.testing.entity.EmployeeTable;

@Service
public class TestServiceImp implements TestService{

	@Autowired
	TestDaoImp dao;
	
	@Override
	public List<EmployeeTable> getData() {
		// TODO Auto-generated method stub
		return dao.getEmpData();
	}

	@Override
	public List<EmployeeTable> putData(int id, Map<String, Object> emp) {
		// TODO Auto-generated method stub
		EmployeeTable et=new EmployeeTable();
		List<String> employee_table_all_fields=this.getFieldNames(et); //getting all the field names
		et=null;
		List<String> etFields=new ArrayList<String>();
		for(Map.Entry<String, Object> val:emp.entrySet()) {
			if(employee_table_all_fields.contains(val.getKey())) {
				etFields.add("`"+val.getKey()+"`="+"'"+val.getValue().toString()+"'");
			}
		}
		String etString = etFields.stream().collect(Collectors.joining(","));
		return dao.putEmpData(etString,id);
	}

	@Override
	public int postData(EmployeeTable emp) {
		// TODO Auto-generated method stub
		return dao.postEmpData(emp);
	}

	@Override
	public int deleteData(int id) {
		// TODO Auto-generated method stub
		return dao.deleteEmpData(id);
	}

	@Override
	public List<EmployeeTable> getDataById(int id) {
		// TODO Auto-generated method stub
		return dao.getEmpDataById(id);
	}
	
	private List<String> getFieldNames(Object c){
		List<String> result = null;
	   	try {
			Field[] fields = c.getClass().getDeclaredFields();
			result = new ArrayList<String>();
			    for (Field field : fields) {
			    	  result.add(field.getName());
			    }
		   }
		   catch (Exception e) {
		          e.printStackTrace();
		   }
			return result;
		}

}
