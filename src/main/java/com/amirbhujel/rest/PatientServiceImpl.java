package com.amirbhujel.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

public class PatientServiceImpl implements PatientService {
	private long currentId = 123;
	Map<Long, Patient> patients = new HashMap<Long, Patient>();
	Map<Long, Prescription> prescriptions = new HashMap<Long, Prescription>();

	public PatientServiceImpl() {
		init();
	}

	final void init() {
		Patient patient = new Patient();
		patient.setName("John");
		patient.setId(currentId);
		patients.put(patient.getId(), patient);

		Prescription prescription = new Prescription();
		prescription.setDescription("prescription 223");
		prescription.setId(223);
		prescriptions.put(prescription.getId(), prescription);
	}

	public Response addPatient(Patient patient) {

		System.out.println("... Invoking addPatient, patient name is :" + patient.getName());

		patient.setId(++currentId);
		patients.put(patient.getId(), patient);

		return Response.ok(patient).build();
	}

	public Patient getPatient(String id) {
		
		System.out.println("... Invoking getPatient, patient name is :" + id);

		Long patientid = Long.parseLong(id);
		Patient patient = patients.get(patientid);

		return patient;
	}

	public Response updatePatient(Patient updatedPatient) {
		
		System.out.println("... Invoking updatePatient, patient name is :" + updatedPatient.getName());

		Patient currentPatient = patients.get(updatedPatient.getId());

		Response response = null;
		if(currentPatient != null){
			patients.put(updatedPatient.getId(), updatedPatient);
			response = Response.ok().build();
		}
		else{
			response = Response.notModified().build();
		}

		return response;
	}

	public Response deletePatients(String id) {
		
		System.out.println("... Invoking deletePatient, patient name is :" + id);

		Long patientId = Long.parseLong(id);
		Patient patient = patients.get(patientId);

		Response response = null;
		if (patient != null) {
			patients.remove(patientId);
			response = Response.ok().build();
		}
		else{
			response = Response.notModified().build();
		}

		return response;
	}

	public Prescription getPrescription(String prescriptionId) {

		return null;
	}
}
