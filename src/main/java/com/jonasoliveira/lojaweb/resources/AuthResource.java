package com.jonasoliveira.lojaweb.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jonasoliveira.lojaweb.dto.EmailDTO;
import com.jonasoliveira.lojaweb.security.JWTUtil;
import com.jonasoliveira.lojaweb.security.UserSS;
import com.jonasoliveira.lojaweb.services.AuthService;
import com.jonasoliveira.lojaweb.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
/*Json Configuracao de cors S3 AWS exemplo https://docs.aws.amazon.com/AmazonS3/latest/dev/cors.html
	[
	   {
	        "AllowedHeaders": [
	            "Authorization"
	        ],
	        "AllowedMethods": [
	            "GET"
	        ],
	        "AllowedOrigins": [
	            "http//*",
	            "https//*"
	        ],
	        "ExposeHeaders": [],
	        "MaxAgeSeconds": 3000
	    }
	]
*/
