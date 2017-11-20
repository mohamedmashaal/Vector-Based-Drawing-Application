package eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects;

import java.util.ArrayList;

public class Column <T> {
	private String name ;
	private String type;
	private ArrayList<Record<T>> records;

    public Column(String name, String type){
    	this.type = type ;
    	this.name = name ;
        this.records = new ArrayList<>();
    }
    
    public String getName() {
		return name;
	}
    
    public String getType() {
    	return type;
    }

    public void addRecord(Record<T> record) {
    	records.add(record);
    }
    
    public Record getRecord(int index) {
    	return records.get(index);
    }
    
    public ArrayList<Record<T>> getRecords() {
        return records;
    }

    public T [] getData() {
        ArrayList<Object> colData = new ArrayList<>();
        for(Record record : records){
            colData.add(record.getValue());
        }
        return (T[]) colData.toArray();
    }

    public void empty() {
    	this.records = new ArrayList<>();
    }
    
    public void remove(int index) {
    	records.remove(index);
    }
}
