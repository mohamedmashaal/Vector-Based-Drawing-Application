package eg.edu.alexu.csd.oop.db.cs60.Model;

/*

    This class handles the main directory of databases:
        Checks if a newly created database already exists
        Creates new databases
        Creates new tables

 */

import java.io.File;
import java.io.IOException;

public class DirectoryHandler {

    File mainDirectory;

    public DirectoryHandler(){
        mainDirectory = new File("data");
        mainDirectory.mkdirs();
    }

    public boolean exists(){
        // TODO Complete It
        for(File dir : mainDirectory.listFiles()){

        }
        return false;
    }

    public void deleteDatabase(String databaseName){
    	File dir = new File(mainDirectory.getAbsolutePath() + System.getProperty("file.separator") + databaseName);
    	deleteDir(dir);
    }

    private void deleteDir(File dir) {
    	File[] files = dir.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteDir(f);
                } else {
                    f.delete();
                }
            }
        }
        dir.delete();
	}

	public String getPathOf(String databaseName) {
    	File dataFile = new File(mainDirectory.getAbsolutePath() + System.getProperty("file.separator") + databaseName);
        return dataFile.getAbsolutePath();
    }
    
	public void createDatabase(String databaseName) {
		File dataFile = new File(mainDirectory.getAbsolutePath() + System.getProperty("file.separator") + databaseName);
		dataFile.mkdirs();
	}

	public void createTable(String tableName , String databaseName) {
		//Just for now
		File table = new File(mainDirectory.getAbsolutePath() + System.getProperty("file.separator") + databaseName +System.getProperty("file.separator")+ tableName +".xml");
		try {
			table.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTable(String tableName, String databaseName) {
		File table = new File(mainDirectory.getAbsolutePath() + System.getProperty("file.separator") + databaseName +System.getProperty("file.separator")+ tableName +".xml");
		table.delete();
	}
}
