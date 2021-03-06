package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */

public class Region {

	private String name;
	private Integer[] lowAltitudeRanges = new Integer[0];
	private Integer[] highAltitudeRanges = new Integer[0];
	private Collection<Municipality> municipalityList = new TreeSet<Municipality>();
	private Collection<MountainHut> mountainHutList = new TreeSet<MountainHut>();
	
	public Region(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAltitudeRanges(String... ranges) {
		
		String[] parts; 
		int i=0;
		lowAltitudeRanges = new Integer[ranges.length];
		highAltitudeRanges = new Integer[ranges.length];
		
		for(String s: ranges) {
			parts = s.split("-");
			lowAltitudeRanges[i] = Integer.parseInt(parts[0]);
			highAltitudeRanges[i] = Integer.parseInt(parts[1]);
			i++;
		}
	}

	public String getAltitudeRange(Integer altitude) {
		
		for(int i=0; i<lowAltitudeRanges.length; i++) {
			if(lowAltitudeRanges[i]<altitude && highAltitudeRanges[i]>=altitude) {
				return lowAltitudeRanges[i].toString() + "-" + highAltitudeRanges[i].toString();
			}
		}
		return "0-INF";
	}

	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		for(Municipality m: municipalityList) {
			if(m.getName() == name)
				return m;
		}
		Municipality m = new Municipality(name, province, altitude);
		municipalityList.add(m);
		return m;
	}

	public Collection<Municipality> getMunicipalities() {
		return municipalityList;
	}

	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		
		for(MountainHut mh: mountainHutList) {
			if(mh.getName() == name)
				return mh;
		}
		MountainHut mh = new MountainHut(name, category, bedsNumber, municipality);
		mountainHutList.add(mh);
		return mh;
	}

	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		
		for(MountainHut mh: mountainHutList) {
			if(mh.getName() == name)
				return mh;
		}
		MountainHut mh = new MountainHut(name, altitude, category, bedsNumber, municipality);
		mountainHutList.add(mh);
		return mh;
	}

	public Collection<MountainHut> getMountainHuts() {
		return mountainHutList;
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"}, fileRowSplit[0]
	 * <li>{@code "Municipality"}, fileRowSplit[1]
	 * <li>{@code "MunicipalityAltitude"}, fileRowSplit[2]
	 * <li>{@code "Name"}, fileRowSplit[3]
	 * <li>{@code "Altitude"}, fileRowSplit[4]
	 * <li>{@code "Category"}, fileRowSplit[5]
	 * <li>{@code "BedsNumber"} fileRowSplit[6]
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		
		Region r = new Region(name);
		
		List<String> ls = readData(file);
		ls.remove(0); // elimino l' intestazione
		
		for (String fileRow: ls) {
			String[] fileRowSplit = fileRow.split(";");
			r.createOrGetMunicipality(fileRowSplit[1], fileRowSplit[0], Integer.valueOf(fileRowSplit[2]));
			if(fileRowSplit[4]=="") {
				r.createOrGetMountainHut(fileRowSplit[3], fileRowSplit[5], 
				Integer.valueOf(fileRowSplit[6]), 
				new Municipality(fileRowSplit[1], fileRowSplit[0], Integer.valueOf(fileRowSplit[2])));
			}else {
				r.createOrGetMountainHut(fileRowSplit[3], Integer.valueOf(fileRowSplit[4]), 
				fileRowSplit[5], Integer.valueOf(fileRowSplit[6]), 
				new Municipality(fileRowSplit[1], fileRowSplit[0], Integer.valueOf(fileRowSplit[2])));
			}	
		}
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Map<String, Long> countMunicipalitiesPerProvince() {
		
		Map<String,Long> map = new TreeMap<String,Long>();
		
		List<String> provinceList = municipalityList.stream()
			.map(Municipality::getProvince)
			.distinct()
			.sorted( (s1,s2) -> s1.compareTo(s2))
			.collect(Collectors.toList());
		
		for(String pr: provinceList) {
			
			Long count = municipalityList.stream()
				.filter(p -> p.getProvince().equals(pr))
				.count();
			
			map.put(pr, count);
		}
		return map;
	}

	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		Map<String,Map<String, Long>> map = new TreeMap<String,Map<String, Long>>();
		
		List<String> provinceList = municipalityList.stream()
			.map(p -> p.getProvince())
			.distinct()
			.sorted( (s1,s2) -> s1.compareTo(s2))
			.collect(Collectors.toList());
		
		for(String pr: provinceList) {
			
			Map<String, Long> map2 = new TreeMap<String, Long>();
			
			List<String> mList = municipalityList.stream()
					.filter(p -> p.getProvince().equals(pr))
					.map(p -> p.getName()) // ottengo nome del comune
					.distinct()
					.collect(Collectors.toList());
			
			for(String m: mList) {
				
				Long count = mountainHutList.stream()
						.filter(p -> p.getMunicipality().equals(m))
						.count();
				
				map2.put(m, count);
				map.put(pr, map2);
			}
		}
		return map;
	}

	public Integer[] getLowAltitudeRanges() {
		return lowAltitudeRanges;
	}

	public void setLowAltitudeRanges(Integer[] lowAltitudeRanges) {
		this.lowAltitudeRanges = lowAltitudeRanges;
	}

	public Integer[] getHighAltitudeRanges() {
		return highAltitudeRanges;
	}

	public void setHighAltitudeRanges(Integer[] highAltitudeRanges) {
		this.highAltitudeRanges = highAltitudeRanges;
	}

	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		
		Map<String, Long> map = new TreeMap<String, Long>();
		
		for(int i=0; i<this.highAltitudeRanges.length; i++) {
			 String s = this.lowAltitudeRanges[i].toString() + "-" + this.highAltitudeRanges[i];
			 
			 Long count = mountainHutList.stream()
			 	.filter(p -> this.getAltitudeRange(p.getAltitude().orElseGet( () -> {return p.getMunicipality().getAltitude();})).equals(s))
			 	.count();
			 	
			 map.put(s, count);
		}
		return map;
	}

	public Map<String, Integer> totalBedsNumberPerProvince() {
		
		Map<String,Integer> map = new TreeMap<String,Integer>();
		Integer count=0;
		Integer count2=0;
		
		List<String> provinceList = municipalityList.stream()
			.map(Municipality::getProvince)
			.distinct()
			.sorted( (s1,s2) -> s1.compareTo(s2))
			.collect(Collectors.toList());
		
		for(String pr: provinceList) {
			
			List<String> mList = municipalityList.stream()
					.filter(p -> p.getProvince().equals(pr))
					.map(p -> p.getName()) // ottengo nome del comune
					.distinct()
					.collect(Collectors.toList());
			
			for(String m: mList) {
				
				List<Integer> bedsN = mountainHutList.stream()
						.filter(p -> p.getMunicipality().getName().equals(m))
						.map(p -> p.getBedsNumber())
						//.map(MountainHut::getBedsNumber)
						.collect(Collectors.toList());
				
				for(Integer i: bedsN) {
					count += i;
				}
				count2 += count;
				count=0;
			}
			map.put(pr, count2);
			count2=0;
		}
		return map;
	}

	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		
		Map<String, Optional<Integer>> map = new TreeMap<String, Optional<Integer>>();
		
		for(int i=0; i<this.highAltitudeRanges.length; i++) {
			 String s = this.lowAltitudeRanges[i].toString() + "-" + this.highAltitudeRanges[i];
			 
			 Optional<Integer> bedsNMax = mountainHutList.stream()
			 	.filter(p -> this.getAltitudeRange(p.getAltitude().orElseGet( () -> {return p.getMunicipality().getAltitude();})).equals(s))
			 	.map(MountainHut::getBedsNumber)
			 	.sorted( (n1,n2) -> - n1.compareTo(n2))
			 	.findFirst();

			 map.put(s, bedsNMax);
		}
		return map;
	}

	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		
		Map<Long, List<String>> map = new TreeMap<Long, List<String>>();
		Integer max=0;
		
		List<String> mList = municipalityList.stream()
				.map(p -> p.getName()) // ottengo nome del comune
				.distinct()
				.collect(Collectors.toList());
		
		for(String m: mList) {
			
			Long hutsN = mountainHutList.stream()
					.filter(p -> p.getMunicipality().getName().equals(m))
					.count();
			
			if(hutsN > max)
				max=hutsN.intValue();
		}
		
		for(Integer i=1; i<=max; i++) {
			
			List<String> mList2 = new LinkedList<String>();
			
			for(String mm: mList) {
				
				Long mCount = mountainHutList.stream()
					.filter(p -> p.getMunicipality().getName().equals(mm))
					.count();
				
				if(mCount==i.longValue())
					mList2.add(mm);
			}

			map.put(i.longValue(), mList2);
		}

		return map;
	}

}
