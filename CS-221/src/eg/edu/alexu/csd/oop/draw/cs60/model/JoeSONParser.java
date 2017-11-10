package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.util.ArrayList;
import java.util.Map;

public class JoeSONParser {


    public String parseArrayOfMapsIntoJSON(ArrayList<Map<String,String>> arrayOfMap){
        StringBuilder parsedArray = new StringBuilder();
        int i = 0;
        parsedArray.append("{\n");
        for(Map<String,String> map : arrayOfMap){
            parsedArray.append("\"" + map.get("id") +"\" :\n{\n");
            for(Map.Entry entry : map.entrySet()){
                parsedArray.append("\"" + entry.getKey() +"\" : \"" + entry.getValue() + "\",\n");
            }
            parsedArray.append("},\n");
        }
        parsedArray.append("}\n");
        return parsedArray.toString();
    }


    public ArrayList<Map<String,String>> parseJSONIntoArrayOfMaps(String jsonFormatted){
        ArrayList<Map<String,String>> arrayOfMap = new ArrayList<>();


        return arrayOfMap;
    }



}
