package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.util.ArrayList;
import java.util.Map;

public class JoeSONParser {


    public String parseArrayOfMapsIntoJSON(ArrayList<Map<String,String>> arrayOfMap){
        StringBuilder parsedArray = new StringBuilder();
        parsedArray.append("{\n");
        for(Map<String,String> map : arrayOfMap){
            parsedArray.append("\"" + map.getOrDefault("id","Map") +"\" :\n{\n");
            for(Map.Entry entry : map.entrySet()){
                parsedArray.append("\"" + entry.getKey() +"\" : \"" + entry.getValue() + "\",");
            }
        }
        parsedArray.append("}\n");
        return parsedArray.toString();
    }



}
