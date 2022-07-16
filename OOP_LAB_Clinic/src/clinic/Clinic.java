package clinic;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic implements ErrorListener {

	
	Collection<Patient> patientList = new TreeSet<Patient> ();
	Collection<Doctor> doctorList = new TreeSet<Doctor> ();
	Map<String, Integer> patientToDoctor = new TreeMap<String, Integer> ();
	Map<Integer, ArrayList<String>> DoctorToPatient = new TreeMap<Integer, ArrayList<String>> ();

	
	public void addPatient(String first, String last, String ssn) {
		
		patientList.add(new Patient(first, last, ssn));
	}

	public String getPatient(String ssn) throws NoSuchPatient {
		
		for(Patient p: patientList) {
			if(p.getSSN().compareTo(ssn)==0) {	
				return p.getCognome() + " " + p.getNome() + " (" + p.getSSN() + ")";
			}
		}
		
		NoSuchPatient err = new NoSuchPatient("Paziente non trovato");
		throw err;
	}

	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {

		doctorList.add(new Doctor(first, last, ssn, Integer.valueOf(docID), specialization));
	}

	public String getDoctor(int docID) throws NoSuchDoctor {
		
		for(Doctor d: doctorList) {
			if(d.getDocID().compareTo(docID)==0) {	
				return d.getCognome() + " " + d.getNome() + " (" + d.getSSN() + ") [" + d.getDocID() + "]: " + d.getSpecializzazione();
			}
		}
		
		NoSuchDoctor err = new NoSuchDoctor("Dottore non trovato");
		throw err;
	}
	
	public String getDoctor(Integer docId) {
		for(Doctor d: doctorList) {
			if(d.getDocID().compareTo(docId.intValue())==0) {	
				return d.getSSN() + " " + d.getCognome() + " " + d.getNome();
			}
		}
		return null;
	}
	
	public Doctor getThisDoctor(Integer docId) {
		for(Doctor d: doctorList) {
			if(d.getDocID().compareTo(docId.intValue())==0) {	
				return d;
			}
		}
		return null;
	}

	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		
		boolean errP=true, errD=true;
		
		for(Patient p: patientList) {
			if (p.getSSN() == ssn) {
				errP=false;
				for(Doctor d: doctorList) {
					if(d.getDocID() == docID) {
						errD=false;
						patientToDoctor.put(ssn, Integer.valueOf(docID));
						
						if(DoctorToPatient.containsKey(Integer.valueOf(docID))) {
							DoctorToPatient.get(Integer.valueOf(docID)).add(ssn); 
						}else {
							ArrayList<String> ls = new ArrayList<String>();
							ls.add(ssn);
							DoctorToPatient.put(Integer.valueOf(docID), ls);
						}
						
					}
				}
				if(errD) {
					NoSuchDoctor err = new NoSuchDoctor("Dottore non trovato");
					throw err;
				}
			}
		}
		if(errP) {
			NoSuchPatient err = new NoSuchPatient("Paziente non trovato");
			throw err;
		}

	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		
		boolean pIsIn = patientList.stream()
			.map(Patient::getSSN)
			.collect(Collectors.toList()).contains(ssn);
		
		if(!pIsIn) {
			NoSuchPatient err = new NoSuchPatient("Paziente non trovato");
			throw err;
		}
		
		if(!patientToDoctor.containsKey(ssn)) {
			NoSuchDoctor err = new NoSuchDoctor("Dottore non trovato");
			throw err;
		}
		return patientToDoctor.get(ssn);
	}

	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {

		boolean dIsIn = doctorList.stream()
				.map(Doctor::getDocID)
				.collect(Collectors.toList()).contains(Integer.valueOf(id));
		
		if(!dIsIn) {
			NoSuchDoctor err = new NoSuchDoctor("Dottore non trovato");
			throw err;
		}
		return DoctorToPatient.get(Integer.valueOf(id));
	}

	public int loadData(Reader reader) throws IOException {
		
		LineNumberReader text = new LineNumberReader(reader);
		String line;
		int n;
		
		
		while((line = text.readLine()) != null) {
			line = line.replace(" ", "");
			try {
				if (line.matches("P;[\\w]+;[\\w]+;[\\w]+")) {
				
					String[] fileRowSplit = line.split(";");
					patientList.add(new Patient(fileRowSplit[1], fileRowSplit[2], fileRowSplit[3]));
				
				}else if(line.matches("M;[\\d]+;[\\w]+;[\\w]+;[\\w]+;[\\w]+")){
				
					String[] fileRowSplit = line.split(";");
					doctorList.add(new Doctor(fileRowSplit[2], fileRowSplit[3], fileRowSplit[4], Integer.parseInt(fileRowSplit[1]), fileRowSplit[5]));
				}
			}catch(Exception e){
				if (e instanceof IOException) {
	                 System.out.println(e.getMessage());
	             }
			}
		}
		
		return text.getLineNumber();		
	}

	public int loadData(Reader reader, ErrorListener listener) throws IOException {

		LineNumberReader text = new LineNumberReader(reader);
		String line;
		int n;
		
		
		while((line = text.readLine()) != null) {
			line = line.replace(" ", "");
			try {
				if (line.matches("P;[\\w]+;[\\w]+;[\\w]+")) {
				
					String[] fileRowSplit = line.split(";");
					patientList.add(new Patient(fileRowSplit[1], fileRowSplit[2], fileRowSplit[3]));
				
				}else if(line.matches("M;[\\d]+;[\\w]+;[\\w]+;[\\w]+;[\\w]+")){
				
					String[] fileRowSplit = line.split(";");
					doctorList.add(new Doctor(fileRowSplit[2], fileRowSplit[3], fileRowSplit[4], Integer.parseInt(fileRowSplit[1]), fileRowSplit[5]));
				}else {
					offending(line);
				}
			}catch(Exception e){
				if (e instanceof IOException) {
					offending(line);
	                System.out.println(e.getMessage());
	             }
			}
		}	
		return text.getLineNumber();		
	}

	public Collection<Integer> idleDoctors(){
		
		return DoctorToPatient.keySet().stream()
				.filter(p -> DoctorToPatient.get(p).isEmpty()==true)
				.collect(Collectors.toList());
	}

	public Collection<Integer> busyDoctors(){
		
		Double avg = DoctorToPatient.keySet().stream()
					.mapToInt(p -> DoctorToPatient.get(p).size())
					.average().getAsDouble();

		Collection<Integer> c = DoctorToPatient.keySet().stream()
				.filter(p -> DoctorToPatient.get(p).size() > avg)
				.collect(Collectors.toList());
		
		return c;
	}

	public Collection<String> doctorsByNumPatients(){
		/*
		Collection<String> n = new ArrayList<String>();
		
		Map<Object, Long> m = patientToDoctor.values().stream()
				.collect(
						Collectors.groupingBy(p -> this.getDoctor(p), Collectors.counting()
						)
				.sorted(Comparator.comparing(Map.Entry<Object,Long>::getValue).reversed()))
			
				;
		
		System.out.println(m);
		
		
		n = new ArrayList<String>();
		*/
		
		
		
		
		/*
		patientToDoctor.values().stream()
		.sorted(Comparator.comparing(d->d.getPatients().size()))	   // or avoid method chaining
		.map( d -> String.format("%3d : %s %s %s", d.getPatients().size(),
								 d.getId(), d.getLast(), d.getFirst()))
		.collect(Collectors.toList())
		


		 CODICE SIMO:
		;*/
		
		
		/*
		return patientToDoctor.values().stream()
			.sorted(Comparator.comparing( (Doctor d) -> d.getPazienti().size()).reversed())
			.map( d -> String.format("%3d : %s %s %s", d.getPatients().size(),
					 d.getId(), d.getLast(), d.getFirst()))
			.collect(Collectors.toList());
			*/
		
		
		
		/*
		Collection<Integer> s = patientToDoctor.values().stream()
				.collect(Collectors.toList());
				
		s.stream()
		.map(d -> this.getThisDoctor(d))
		.collect(Collectors.groupingBy(d -> ((Doctor) d).getSSN()), Collectors.counting());
		*/
		
		/*
		.collect(Collectors.groupingBy(d->d),
				()->new TreeMap<Integer,List<String>>(reverseOrder()), // type pars required to guide compiler
				Collectors.toList()
				);*/
		Collection<String> n = new ArrayList<String>();
		
		Map<Doctor,Long> s = patientToDoctor.values().stream()
				.filter(p->this.getThisDoctor(p)!=null)
				.collect(Collectors.groupingBy(p -> this.getThisDoctor(p), Collectors.counting()));
				//.sorted(Map.Entry.comparingByValue().reversed());
				//.collect(Collectors.toList()));
		
		s.entrySet().stream()
			.sorted( (s1,s2) -> s.get(s2).intValue()-s.get(s1).intValue());
		
		for(Doctor d: s.keySet()) {
			String str = new String();
			str = s.get(d).toString() + " : " + d.getSSN() + " " + d.getCognome() + " " + d.getNome();
			n.add(str);
		}
					
		return n;
		
				/*
		return patientToDoctor.values().stream()
				.filter(p->this.getThisDoctor(p)!=null)
				.collect(Collectors.groupingBy(p -> this.getThisDoctor(p), Collectors.counting()))
				.collect(Collectors.toList());
		
		
				.entrySet().stream()
				
				//.sorted(Comparator.comparing(Map.Entry::getValue).reversed()) // <-- the compiler cannot infer the type
				.sorted(Comparator.comparing(Map.Entry<Doctor,Long>::getValue).reversed()) // must make Entry type parameters explicit
				//.sorted(Comparator.comparing(Map.Entry::getValue, Collections.reverseOrder())) // or avoid method chaining 
				.map( e -> String.format("%3d", e.getValue()) + " : " + e.getKey().getId() +  " " + e.getKey().getLast() + " " + e.getKey().getFirst())
				.collect(Collectors.toList());
				*/
	}

	public Collection<String> countPatientsPerSpecialization(){
		
		Collection<String> n = new ArrayList<String>();
		
		Map<String,Long> s = patientToDoctor.values().stream()
				.filter(p->this.getThisDoctor(p)!=null)
				.collect(Collectors.groupingBy(p -> this.getThisDoctor(p).getSpecializzazione(), Collectors.counting()));
		
		s.entrySet().stream()
		.sorted(Comparator.comparing(Map.Entry<String,Long>::getValue,Collections.reverseOrder()).thenComparing(Map.Entry::getKey));
		
		for(String sp: s.keySet()) {
			String str = new String();
			str = s.get(sp).toString() + " - " + sp;
			n.add(str);
		}
		
		return n;
		
		/*CODICE SIMO

		return pazienti.values().stream()
		.map(Patient::getD) //ottengo i dottori assegnati ai pazienti
		.filter(d -> d!=null) //filtro i pazienti con un dottore assegnato
		.collect(groupingBy(Doctor::getSpecialization, counting())) //raggruppo per 1 aspecializzazione del dottore e li conto
		.entrySet().stream() //ottengo uno stream di mappe con specializzazione e numero <String, Logn>
		.sorted (comparing(Map.Entry<String, Long>::getValue, reverseOrder()) //ordino in base al valore della mappa (l'intero) e faccio reverse order per l'ordiname
		.thenComparing(Map.Entry::getKey)) //in caso di numero uguale, ordino in ordine alfabetico le stringhe
		.map(e -> String.format("%3d - %s", e.getValue(), e.getKey())) //formatto la stringa risultante come chiesto dal requirement
		.collect(tolist()); 
		 */
	}

	@Override
	public void offending(String line) {
		System.out.println("Linea scartata: " + line);
	}
	
	
	
}
