 package clinic;

public class Patient implements Comparable<Patient>{

	private String nome;
	private String cognome;
	private String SSN; // codice fiscale
	
	public Patient(String nome, String cognome, String SSN) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.SSN = SSN;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	@Override
	public int compareTo(Patient p) {
		
		int cmp = (this.SSN.compareTo(p.getSSN()));
		
		if(cmp < 0)
			return -1;
		else if(cmp > 0)
			return 1;
		return 0;
	}
}
