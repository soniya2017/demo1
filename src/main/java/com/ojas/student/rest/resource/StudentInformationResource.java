package com.ojas.student.rest.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.ojas.student.entity.AccessToken;
import com.ojas.student.entity.StudentInformation;
import com.ojas.student.mapper.StudentMapper;
import com.ojas.student.service.AccessTokenService;
import com.ojas.student.service.StudentInformationService;
import com.ojas.student.utilities.EmailService;

@Controller
@Path("/student")
public class StudentInformationResource {

	@Autowired
	private StudentInformationService informationService;

	@Autowired
	private AccessTokenService accessTokenService;

	@GET
	@Path("/hi")
	@Produces(MediaType.APPLICATION_JSON)
	public String check() {
		return "hello";
	}

	@Autowired
	EmailService email;

	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudentInfo(@RequestBody StudentMapper studentMapper) {
		if (null != studentMapper) {
			StudentInformation studentInfo = new StudentInformation();
			studentInfo.setStudentName(studentMapper.getStudentName());
			studentInfo.setAddress(studentMapper.getAddress());
			studentInfo.setEmailId(studentMapper.getEmailId());
			studentInfo.setRole(studentMapper.getRole());
			studentInfo
					.setPassword(informationService.returnBcryptPasswordEncoder().encode(studentMapper.getPassword()));
			StudentInformation savedStudentInfo = informationService.create(studentInfo);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(studentMapper.getEmailId());
			message.setText("Registration successfully with our application");
		
			message.setSubject("registration info");
			email.send(message);
			return Response.status(Status.OK).entity(savedStudentInfo).build();
		} else {
			return Response.status(Status.CONFLICT).entity("Failed").build();
		}
	}

	@GET
	@Path("/findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInformation findStudentById(@PathParam(value = "id") Long id) {
		return informationService.findById(id);
	}

	@Path("/studentDetail")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInformation getStudentInfoByName(@FormParam("userName") String userName) {
		return informationService.getStudentBody(userName);
	}

	@Path("/accessToken/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccessTokenById(@PathParam(value = "id") Long id) {

		AccessToken accessToken = accessTokenService.getAccessTokenDetail(id);

		return Response.status(Status.OK).entity(accessToken).build();
	}

	@Path("/userDetails")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails getStudentDetails(@FormParam("userName") String userName) {
		return informationService.loadUserByUsername(userName);
	}

	@Path("/principal")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Authentication getPrincipalObject(@FormParam("userName") String userName) {
		/*
		 * Authentication authentication =
		 * informationService.getAuthenticatedObject(userName); // return
		 * Response.status(Status.OK).entity(authentication).build();
		 * if(authentication.isAuthenticated()) { return
		 * Response.status(Status.OK).entity(authentication.isAuthenticated()).
		 * build(); } else return
		 * Response.status(Status.NOT_ACCEPTABLE).entity("Failed").build(); }
		 */
		return informationService.getAuthenticatedObject(userName);

	}

	@Path("/validateStudent")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateStudentInformation(@FormParam("userName") String userName,
			@FormParam("password") String password) {
		Authentication authentication = informationService.getAuthenticatedObject(userName);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean resultant = informationService.returnBcryptPasswordEncoder().matches(password,
				userDetails.getPassword());
		if (userName.equals(userDetails.getUsername()) && resultant) {
			AccessToken accessToken = new AccessToken();
			accessToken.setAccessToken(UUID.randomUUID().toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String creationDate = dateFormat.format(new Date((System.currentTimeMillis())));
			accessToken.setCreationDate(creationDate);
			accessToken.setStudentInformation(informationService.getStudentBody(userName));
			return Response.status(Status.OK).entity(accessTokenService.saveAccessToken(accessToken)).build();
		} else {
			return Response.status(Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity("Bad Credentials")
					.build();

		}
	}

	@Path("/logout/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response logOut(@PathParam("id") Long id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String expireDate = dateFormat.format(new Date((System.currentTimeMillis())));
		int result = accessTokenService.getUpdateAccessToken(expireDate, id);
		return Response.status(Status.OK).entity(result).build();
	}

}