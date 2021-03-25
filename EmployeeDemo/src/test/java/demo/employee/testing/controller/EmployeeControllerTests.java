/*package demo.employee.testing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.employee.controller.EmployeeController;
import demo.employee.entity.EmployeeTable;
import demo.employee.service.EmployeeServiceImp;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EmployeeServiceImp service;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(this.mockMvc).isNotNull();
		assertThat(this.service).isNotNull();
	}
	
	@Test
	public void getDataTest() throws Exception{
		
		//This is mock response object
		EmployeeTable emp1=new EmployeeTable(1,"TestNameA","TestAddressA","123456789");
		EmployeeTable emp2=new EmployeeTable(2,"TestNameB","TestAddressB","987654321");
		List<EmployeeTable> mockResponse=new ArrayList<EmployeeTable>();
		mockResponse.add(emp1);
		mockResponse.add(emp2);
		
		when(service.getData()).thenReturn(mockResponse);
		
		MvcResult mockResult=this.mockMvc.perform(get("/emp/get"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String mockResultJSON=mockResult.getResponse().getContentAsString();
		List<EmployeeTable> ResultList=objectMapper.readValue(mockResultJSON, new TypeReference<List<EmployeeTable>>() {});
		
		assertNotNull(ResultList);//checking if the result is null
		assertEquals(mockResponse.get(0).getEmployee_name(), ResultList.get(0).getEmployee_name());//checking if the expected emp name is same as actual emp name
	
	}
	
	@Test
	public void getDataByIdTest() throws Exception{
		
		//This is mock response object
		int id=1;
		EmployeeTable emp1=new EmployeeTable(1,"TestNameA","TestAddressA","123456789");
		List<EmployeeTable> mockResponse=new ArrayList<EmployeeTable>();
		mockResponse.add(emp1);
		
		when(service.getDataById(id)).thenReturn(mockResponse);
		
		MvcResult mockResult=this.mockMvc.perform(get("/emp/get/"+id))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String mockResultJSON=mockResult.getResponse().getContentAsString();
		EmployeeTable Result=objectMapper.readValue(mockResultJSON, new TypeReference<EmployeeTable>() {});
		
		assertNotNull(Result);//checking if the result is null
		assertEquals(mockResponse.get(0).getEmployee_name(), Result.getEmployee_name());//checking if the expected emp name is same as actual emp name
		
	}

	@Test
	public void postDataTest() throws Exception{

		//This is mock request object
		EmployeeTable emp1=new EmployeeTable();
		emp1.setEmployee_name("TestNameA");
		emp1.setAddress("TestAddressA");
		emp1.setPhone("123456789");
		
		//This is mock response object
		int res=1;
		String response="Data Inserted";
		
		when(service.postData(emp1)).thenReturn(res);
		
		MvcResult mockResult=this.mockMvc.perform(post("/emp/post")
				.content(objectMapper.writeValueAsString(emp1))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String Result=mockResult.getResponse().getContentAsString();
		
		assertNotNull(Result);//checking if the result is null
		assertEquals(Result, response);//checking if the expected emp name is same as actual emp name
		
	}
	
	@Test
	public void putDataTest() throws Exception{

		//This is mock request object
		int id=1;
		Map<String,Object> emp1=new HashMap<String,Object>();
		emp1.put("Employee_name","TestNameA");
		emp1.put("Address","TestAddressA");
		emp1.put("Phone","123456789");
		
		//This is mock response object
		EmployeeTable emp2=new EmployeeTable(1,"TestNameAA","TestAddressAA","1234567899");
		List<EmployeeTable> mockResponse=new ArrayList<EmployeeTable>();
		mockResponse.add(emp2);
		
		when(service.putData(id,emp1)).thenReturn(mockResponse);
		
		MvcResult mockResult=this.mockMvc.perform(put("/emp/put/"+id)
				.content(objectMapper.writeValueAsString(emp1))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String mockResultJSON=mockResult.getResponse().getContentAsString();
		EmployeeTable Result=objectMapper.readValue(mockResultJSON, new TypeReference<EmployeeTable>() {});

		assertNotNull(Result);//checking if the result is null
		assertEquals(Result.getEmployee_name(), mockResponse.get(0).getEmployee_name());//checking if the expected emp name is same as actual emp name
				
	}
	
	@Test
	public void deleteDataTest() throws Exception{

		int id=1;
		//This is mock request object
		EmployeeTable emp1=new EmployeeTable();
		emp1.setEmployee_name("TestNameA");
		emp1.setAddress("TestAddressA");
		emp1.setPhone("123456789");
		
		//This is mock response object
		int res=1;
		String response="Data Deleted";
		
		when(service.deleteData(id)).thenReturn(res);
		
		MvcResult mockResult=this.mockMvc.perform(delete("/emp/delete"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String Result=mockResult.getResponse().getContentAsString();
				
		assertNotNull(Result);//checking if the result is null
		assertEquals(Result, response);//checking if the expected emp name is same as actual emp name
		
	}
	
}
*/
