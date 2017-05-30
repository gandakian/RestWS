package com.amirbhujel.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amirbhujel.rest.Patient;

public class PatientServiceClient {

	private static final String PATIENT_SERVICE_URL = "http://localhost:8080/RestWS/services/patientService";

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		// WebTarget getTarget =
		// client.target("http://localhost:8080/RestWS/services/patientService/patients/123");
		// Response response = getTarget.request().get();
		// System.out.println(response.getStatus());

		// GET Method
		WebTarget getTarget = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}").resolveTemplate("id",
				123);
		Patient patient = getTarget.request().get(Patient.class);
		System.out.println(patient.getName());

		// PUT Method
		WebTarget putTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
		patient.setName("Amir");
		Response updateResponse = putTarget.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();

		Patient newPatient = new Patient();
		newPatient.setName("Bhujel");

		// POST Method
		WebTarget postTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
		Patient postResponse = postTarget.request().post(Entity.entity(newPatient, MediaType.APPLICATION_XML),
				Patient.class);
		System.out.println(postResponse.getId());
		
		// DELETE Method
		/*WebTarget deleteTarget = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}").resolveTemplate("id",
				124);
		Patient delPatient = deleteTarget.request().delete(Patient.class);
		System.out.println(delPatient.getName());*/
		
		client.close();
	}

}
