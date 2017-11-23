package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JoeSONParser {

	public String parseArrayOfMapsIntoJSON(ArrayList<Map<String, String>> arrayOfMap) {
		StringBuilder parsedArray = new StringBuilder();
		parsedArray.append("{\n");
		for (Map<String, String> map : arrayOfMap) {
			parsedArray.append("\t\"" + map.get("id") + "\" :\n\t{\n");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				parsedArray.append("\t\t\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\",\n");
			}
			parsedArray.append("\t},\n");
		}
		parsedArray.append("}\n");

		return parsedArray.toString();
	}

	public ArrayList<Map<String, String>> parseJSONIntoArrayOfMaps(String jsonFormatted) {
		ArrayList<Map<String, String>> arrayOfMap = new ArrayList<>();
		Scanner in = new Scanner(jsonFormatted);
		in.nextLine().trim(); // as first line has only an open curly bracket
								// "{"

		while (in.hasNextLine()) {
			Map<String, String> map = new HashMap<>();
			// reading id line
			String current = in.nextLine().trim();
			if (current.equals("}")) // end of file
				break;
			// getting just the id
			// current.trim();
			String id = "";
			boolean firstQuote = true;
			for (int i = 0; i < current.length(); i++) {
				if (current.charAt(i) == '"') {
					if (firstQuote) {
						firstQuote = false;
						continue;
					} else
						break;
				}
				id += current.charAt(i);
			}

			map.put("id", id);
			while (true) {
				current = in.nextLine().trim();
				if (current.equals("},"))
					break;
				if (current.equals("{"))
					continue;

				String[] entry = { "", "" };
				int index = 0;
				boolean oddQuote = true;
				for (int i = 0; i < current.length(); i++) {
					if (current.charAt(i) == '"') {
						if (oddQuote) {
							oddQuote = false;
							continue;
						} else {
							if (index == 1)
								break;
							index++;
							oddQuote = true;
							continue;
						}
					}
					entry[index] += current.charAt(i);
				}
				entry[1] = entry[1].substring(3); // deleting unwanted part " :
													// "
				map.put(entry[0], entry[1]); // adding entry to the map
			}

			arrayOfMap.add(map);
		}
		in.close();
		return arrayOfMap;
	}

}
