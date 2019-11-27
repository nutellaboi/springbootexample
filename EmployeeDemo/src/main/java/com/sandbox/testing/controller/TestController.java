package com.sandbox.testing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.testing.entity.employee_table;
import com.sandbox.testing.service.TestServiceImp;

@RestController
public class TestController {

	@Autowired
	TestServiceImp serv;
	
	@RequestMapping(method=RequestMethod.GET,value="/get")
	public ResponseEntity<Object> getData(){
		List<Map<String,Object>> list=serv.getData();
		ResponseEntity<Object> response;
		if(list.isEmpty()) {
			String s="No Records Found";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		else {
			response= new ResponseEntity<Object>(list,HttpStatus.OK);

		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get/{id}") //pass employee_id in URL
	public ResponseEntity<Object> getData(@PathVariable int id){
		List<Map<String,Object>> list=serv.getDataById(id);
		ResponseEntity<Object> response;
		if(list.isEmpty()) {
			String s="No Records Found";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		else {
			response= new ResponseEntity<Object>(list,HttpStatus.OK);

		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/post")
	public ResponseEntity<Object> postData(@RequestBody employee_table emp){
		int val=serv.postData(emp);
		ResponseEntity<Object> response;
		if(val==1) {
			String s="Data Inserted";
			response= new ResponseEntity<Object>(s,HttpStatus.CREATED);
		}
		else {
			String s="Error Inserting";
			response= new ResponseEntity<Object>(s,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/put/{id}") //pass employee_id in URL
	public ResponseEntity<Object> putData(@PathVariable int id,@RequestBody Map<String,Object> emp){
		List<Map<String,Object>> list=serv.putData(id,emp);
		ResponseEntity<Object> response;
		if(list.isEmpty()) {
			String s="No Records Found";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		else {
			response= new ResponseEntity<Object>(list,HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}") //pass employee_id in URL
	public ResponseEntity<Object> deleteData(@PathVariable int id){
		List<Map<String,Object>> modifiedList=serv.deleteData(id);
		ResponseEntity<Object> response;
		if(!modifiedList.isEmpty()) {
			response=new ResponseEntity<Object>(modifiedList,HttpStatus.OK);
		}
		else {
			String s="Table is Empty";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
}
