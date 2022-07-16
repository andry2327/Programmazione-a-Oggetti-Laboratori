package clinic;

public class Doctor implements Comparable<Doctor>{

	private String nome;
	private String cognome;
	private String SSN; // codice fiscale
	private Integer docID;
	private String specializzazione;
	
	
	public Doctor(String nome, String cognome, String SSN, Integer docID, String specializzazione) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.SSN = SSN;
		this.docID = docID;
		this.specializzazione = specializzazione;
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

	public Integer getDocID() {
		return docID;
	}

	public void setnBadge(Integer docID) {
		this.docID = docID;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}
	
	@Override
	public int compareTo(Doctor d) {
		
		int cmp = (this.cognome.compareTo(d.getCognome()));
		
		if(cmp < 0)
			return -1;
		else if(cmp > 0)
			return 1;
		else {
			int cmp2 = this.nome.compareTo(d.getNome());
			
			if(cmp2 < 0)
				return -1;
			else if(cmp2 > 0)
				return 1;
		}
		return 0;
	}
}
