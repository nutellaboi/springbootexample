package demo.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.employee.dao.EmployeeDaoImp;
import demo.employee.entity.LoginResponseBody;
import demo.employee.entity.UsersTable;
import demo.employee.security.JwtTokenUtil;
import demo.employee.security.JwtUserDetailsService;

@Service
public class CustomAuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	public LoginResponseBody getLoginDetails(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		authenticate(username, password);
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(username);

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		LoginResponseBody response=new LoginResponseBody();
		response.setToken(token);
		response.setUsername(jwtTokenUtil.getUsernameFromToken(token));

		return response;
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
