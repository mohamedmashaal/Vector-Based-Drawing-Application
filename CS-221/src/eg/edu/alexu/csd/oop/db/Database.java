package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

public interface Database {

        /**
         * Create database with the given name, or use it if exists. This method performs
         * a call to executeStructureQuery() internally to create or drop the database.
         *
         * @param databaseName Database name
         * @param dropIfExists is true, then delete the database and recreate it again.
         * @return the absolute path of the database directory wherein data is stored
         */
        public String createDatabase(String databaseName, boolean dropIfExists);

        /**
         * Creates/drops table or database.
         *
         * @param query create or drop, table or database query
         * @throws SQLException syntax error
         * @returns true if success, or false otherwise
         */
        public boolean executeStructureQuery(String query) throws java.sql.SQLException;

        /**
         * Select from table
         *
         * @param query select query
         * @return the selected records or an empty array if no records match. Columns
         * types must be preserved (i.e. int column returns Integer objects)
         * @throws SQLException syntax error
         */
        public Object[][] executeQuery(String query) throws java.sql.SQLException;

        /**
         * Insert or update or delete the data
         *
         * @param query data manipulation query
         * @return the updated rows count
         * @throws SQLException syntax error
         */
        public int executeUpdateQuery(String query) throws java.sql.SQLException;

}
