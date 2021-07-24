package demo.employee.controller;

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

import demo.employee.entity.EmployeeTable;
import demo.employee.entity.LoginRequestBody;
import demo.employee.entity.LoginResponseBody;
import demo.employee.entity.UsersTable;
import demo.employee.service.CustomAuthenticationService;
import demo.employee.service.EmployeeServiceImp;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeServiceImp serv;
	
	@Autowired
	private CustomAuthenticationService authService;
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequestBody login)throws Exception{
		String username=login.getUsername();
		String password=login.getPassword();
		
		LoginResponseBody response=authService.getLoginDetails(username,password);

		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/sign-up")
	public ResponseEntity<Object> signUp(@RequestBody UsersTable user) throws Exception{
		String response=serv.signUp(user);
		ResponseEntity<Object> responseEntity;
		if(response.equals("ERROR!")) {
			responseEntity= new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
		}
		else {
			responseEntity= new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/get")
	public ResponseEntity<Object> getData(){
		List<EmployeeTable> list=serv.getData();
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
	public ResponseEntity<Object> getDataById(@PathVariable int id){
		List<EmployeeTable> list=serv.getDataById(id);
		ResponseEntity<Object> response;
		if(list.isEmpty()) {
			String s="No Records Found";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		else {
			response= new ResponseEntity<Object>(list.get(0),HttpStatus.OK);

		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/post")
	public ResponseEntity<Object> postData(@RequestBody EmployeeTable emp){
		int val=serv.postData(emp);
		ResponseEntity<Object> response;
		if(val>=1) {
			String s=val+" Data Inserted";
			response= new ResponseEntity<Object>(s,HttpStatus.OK);
		}
		else {
			String s="Error Inserting";
			response= new ResponseEntity<Object>(s,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/put/{id}") //pass employee_id in URL
	public ResponseEntity<Object> putData(@PathVariable int id,@RequestBody Map<String,Object> emp){
		List<EmployeeTable> list=serv.putData(id,emp);
		ResponseEntity<Object> response;
		if(list.isEmpty()) {
			String s="No Records Found";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_FOUND);
		}
		else {
			response= new ResponseEntity<Object>(list.get(0),HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}") //pass employee_id in URL
	public ResponseEntity<Object> deleteData(@PathVariable int id){
		int val=serv.deleteData(id);
		ResponseEntity<Object> response;
		if(val>0) {
			String s="Data Deleted";
			response=new ResponseEntity<Object>(s,HttpStatus.OK);
		}
		else {
			String s="Error in Deletion: Check Id.";
			response= new ResponseEntity<Object>(s,HttpStatus.NOT_IMPLEMENTED);
		}
		return response;
	}
	
}
