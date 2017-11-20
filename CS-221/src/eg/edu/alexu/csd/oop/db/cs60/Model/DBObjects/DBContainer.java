package eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects;

import java.util.ArrayList;
import java.util.List;

public class DBContainer {
    private String name;
    private ArrayList<Table> tables;

    public DBContainer(String name){
        this.tables = new ArrayList<>();
        this.name = name;
    }

	public String getName() {
		return name;
	}
	
	public void remove(String tableName) {
		int i = 0;
		int index = -1;
		for(Table table :tables) {
			if(table.getName().equalsIgnoreCase(tableName)) {
				index = i;
				break ;
			}
			i ++ ;
		}
		if(index != -1)
			tables.remove(index);
	}
	
	public void add(Table table) {
		tables.add(table);
	}
	
	public int getTableIndex(String tableName){
    	int i=0;
		for(Table table : tables) {
			if(table.getName().equalsIgnoreCase(tableName))
				return i ;
			i++;
		}
		return -1;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}
	
	public boolean tableExists(String tableName) {
		for(Table table : tables) {
			if(table.getName().equalsIgnoreCase(tableName))
				return true ;
		}
		return false;
	}

	public int insert(String string, List<String> columns , List<String> values) {
		for(Table table : tables) {
			if(table.getName().equalsIgnoreCase(string)) {
				if(columns.size() != 0) {
					table.insert(columns,values);
					return 1;}
				else {
					table.insert(values);
					return 1;
				}
			}
		}
		return 0;
	}

	public int update(String string, ArrayList<String> columns, ArrayList<String> values, ArrayList<String> toUpdate) {
		int size = 0;
		for(Table table : tables) {
			if(table.getName().equalsIgnoreCase(string)) {
				if(toUpdate.isEmpty())
					size = table.update(columns,values);
				else
					size = table.update(columns, values , toUpdate);
				break ;
			}
		}
		return size;
	}

	public int delete(String string, ArrayList<String> toUpdate) {
		int size = 0;
		for(Table table : tables) {
			if(table.getName().equalsIgnoreCase(string)) {
				if(toUpdate.isEmpty())
					size = table.delete();
				else
					size = table.delete(toUpdate);
				break ;
			}
		}
		return size;
	}
	
}
