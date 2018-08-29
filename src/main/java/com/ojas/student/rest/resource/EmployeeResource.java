package com.ojas.student.rest.resource;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ojas.student.entity.EmployeeInformation;
import com.ojas.student.service.EmployeeService;

@Controller
@Path("employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@GET
	@Path("/hiEmp")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkMethod() {

		return "Hello";
	}

	@Path("/saveEmp")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveEmployee(@FormDataParam("photo") InputStream input,
			@FormDataParam("photo") FormDataContentDisposition disp, @FormDataParam("empName") String eName,
			@FormDataParam("qualification") String qualification, @FormDataParam("emailId") String emailId,
			@FormDataParam("address") String address, @FormDataParam("mobileNo") String moNo) throws IOException {
		
		byte[] desert = IOUtils.toByteArray(input);
		EmployeeInformation info = new EmployeeInformation();
		info.setPhoto(desert);
		info.setEmpName(eName);
		info.setQualification(qualification);
		info.setEmailId(emailId);
		info.setAddress(address);
		info.setMobileNo(moNo);
		
		return Response.status(Status.OK).entity(employeeService.saveEmp(info)).build();
	}
}
