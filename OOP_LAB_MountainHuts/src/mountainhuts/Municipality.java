package mountainhuts;

/**
 * Represents a municipality
 *
 */
public class Municipality implements Comparable<Municipality>{

	private String name;
	private String province;
	private Integer altitude;
	
	public Municipality(String name, String province, Integer altitude) {
		this.name = name;
		this.province = province;
		this.altitude = altitude;
	}
	
	public String getName() {
		return this.name;
	}

	public String getProvince() {
		return this.province;
	}

	public Integer getAltitude() {
		return this.altitude;
	}

	@Override
	public int compareTo(Municipality m) {

		int cmp = (this.name.compareTo(m.getName()));
		
		if(cmp < 0)
			return -1;
		else if(cmp > 0)
			return 1;
		return 0;
	}

}
