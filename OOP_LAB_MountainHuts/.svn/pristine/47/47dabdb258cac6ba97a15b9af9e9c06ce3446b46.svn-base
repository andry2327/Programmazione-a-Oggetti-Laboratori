package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut.
 * 
 * It is linked to a {@link Municipality}
 *
 */
public class MountainHut implements Comparable<MountainHut>{

	private String name;
	private Integer altitude;
	private String category;
	private Integer bedsNumber;
	private Municipality municipality;
	
	public MountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
		
		this.name = name;
		this.altitude = altitude;
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
	}
	
	public MountainHut(String name, String category, Integer bedsNumber, Municipality municipality) {
		
		this.name = name;
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
	}
	
	public String getName() {
		return this.name;
	}

	public Optional<Integer> getAltitude() {
		return Optional.ofNullable(altitude);
	}

	public String getCategory() {
		return this.category;
	}

	public Integer getBedsNumber() {
		return this.bedsNumber;
	}

	public Municipality getMunicipality() {
		return this.municipality;
	}

	@Override
	public int compareTo(MountainHut mh) {
		
		int cmp = (this.name.compareTo(mh.getName()));
		
		if(cmp < 0)
			return -1;
		else if(cmp > 0)
			return 1;
		return 0;
	}

}
