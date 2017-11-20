package eg.edu.alexu.csd.oop.db.cs60.Controller;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs60.Model.DatabaseImp;
import eg.edu.alexu.csd.oop.db.cs60.Model.DirectoryHandler;

import java.sql.SQLException;

/*

    This class may be considered our controller; it takes submitted queries from the view (console in this case)
    and matches those queries with a function from the Database Interface.

 */


public class QueriesParser {
    Database database;
    DirectoryHandler directoryHandler;

    public QueriesParser(Database database){
        this.database = database;
        directoryHandler = new DirectoryHandler();
    }

    public boolean parseQuery(String query) throws SQLException {
        String[] splittedQuery = query.trim().split(" ");
        for(String s : splittedQuery){
            System.out.println("\"" + s + "\"");
        }
        if(splittedQuery[0].equalsIgnoreCase("create") && splittedQuery[0].equalsIgnoreCase("database")){
            //CREATE DATABASE databasename; or CREATE TABLE table_name (....);
            // TODO Check if exists
            database.createDatabase(splittedQuery[2],true); //this method will call createDatabase if necessary.
            return true; // successful query
        }

        if(splittedQuery[0].equalsIgnoreCase("create") && splittedQuery[0].equalsIgnoreCase("table")){
            //CREATE DATABASE databasename; or CREATE TABLE table_name (....);
            // TODO Check if exists
            database.executeStructureQuery(query); //this method will call createDatabase if necessary.
            return true; // successful query
        }

        if(splittedQuery[0].equalsIgnoreCase("select")){
            database.executeQuery(query);
            return true;
        }

        if(splittedQuery[0].equalsIgnoreCase("update") || splittedQuery[0].equalsIgnoreCase("delete")){
            database.executeUpdateQuery(query);
            return true;
        }

        return false; // invalid query
    }

    public DirectoryHandler getDirectoryHandler(){
        return directoryHandler;
    }
}
