package eg.edu.alexu.csd.oop.db.cs60.View;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs60.Model.DatabaseImp;

public class Testing {

    public static void main(String[] args){
        //SELECT * From table_name12 SELECT * From table_name12
    	//SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5 SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5
    	//SELECT * FROM table_name1 WHERE coluMN_NAME2 < 6 SELECT * FROM table_name1 WHERE coluMN_NAME2 < 6
    	// UPDATE table_name8 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3' UPDATE table_name8 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'
    	//DELETE From table_name2
    	//DELETE From table_name2 WHERE coLUmn_NAME1='VAluE1'
    	//UPDATE table_namE1 SET column_name1 11111111 COLUMN_NAME2 22222222 column_name3 333333333 WHERE coLUmn_NAME3 VALUE3
    	String x = "SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5";
        //String x = "	sons (PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255) )";
        //Create TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)
        // INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4) INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)
        //UPDATE table_name9 SET column_name1='value1', column_name2=15, column_name3='value2' UPDATE table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'
        x = x.replaceAll("\\)", " ").replaceAll("\\(", " ").replaceAll("'", "").replaceAll("\\s+\\,", ",");
        System.out.println(x);
        String [] z = x.split("\\s+|\\,\\s*|\\(|\\)|\\=");
        for(String v : z)
        	System.out.println(v);

    	/*DatabaseImp db = new DatabaseImp();
    	db.createDatabase("7amada", true);
    	try {
			db.executeStructureQuery("CREATE TABLE table_namE1 (column_name1 varchar, column_name2 int, column_name3 varchar)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			db.executeUpdateQuery("INSERT INTO table_namE1(column_NAME1, COLUMN_name2, column_name3) VALUES ('value1', 4, 'value2')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			db.executeUpdateQuery("UPDATE table_namE1 SET column_name1 11111111 COLUMN_NAME2 22222222 column_name3 333333333 WHERE coLUmn_NAME3 VALUE3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//
//        String q = "SELECT col1,col2,col3 FROM table";
//        String[] qs = q.split(" ");
//        for(String s : qs){
//            System.out.println(s);
//        }

		/*Object[] arr = new Object[2];
		Object[][] retData = new Object[][]{arr};*/

    }

}
