package ru.gb.java_core_3.l2_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static final String DB_CONNECTION_STRING = "jdbc:sqlite:db/example.db";

    private static String insertStatement = "insert into students (name, score) values (?, ?);";

    private static final String EXAMPLE_CALL = "{call do_something_prc(?,?,?)}";
    private static final String CREATE_REQUEST = "create table if not exists students " +
            "(id integer primary key autoincrement, name text, score integer);";

    private static final String DROP_REQUEST = "drop table if exists students;";
    private static final String SIMPLE_INSERT_REQUEST =
            "insert into students (name, score) values ('Vasya Pupkin', 80), ('Kolya Morzhov', 90), ('Vitaly Petrov', 100);";

    public static void main(String[] args) {

        try {
            connect();
            statement = connection.createStatement();
            createTable();
//            simpleInsertExample();
//            simpleDropTable();
//            simpleUpdate();
//            simpleRead();
//            simpleDelete();
//            notReallyCorrectInsert("Pavel", 200);
//            notReallyCorrectInsert("Pavel', 200); drop table students;", 200); //sql injection
//            var name = "Pavel";
//            var score = 200;
            preparedStatement = connection.prepareStatement(insertStatement);
//            preparedInsert(name, score);
//            var evilName ="Pavel', 200); drop table students;";
//            preparedInsert(evilName, score);
//            massInsert();
//            massInsertTransactions();

//            CallableStatement cs = connection.prepareCall(EXAMPLE_CALL);
//            cs.setObject(1, "sfdbdfsgb");
//            cs.executeUpdate();

//            massInsertWithBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void massInsertWithBatch() throws SQLException {
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "Student #" + i);
            preparedStatement.setInt(2, i);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
//        connection.commit();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void massInsertTransactions() throws SQLException {
        var start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        for (int i = 0; i < 5000; i++) {
            preparedInsert("Student #" + i, i);
        }
//        connection.commit();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void massInsert() throws SQLException {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedInsert("Student #" + i, i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void preparedInsert(String name, int score) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, score);
        preparedStatement.executeUpdate();
    }

    private static void notReallyCorrectInsert(String name, int score) throws SQLException {
        var count = statement.executeUpdate("insert into students (name, score) values ('" + name + "', " + score + ");");
        System.out.printf("Updated %d rows\n", count);
    }

    private static int simpleDelete() throws SQLException {
        return statement.executeUpdate("delete from students where score < 100;");
    }

    private static void simpleRead() throws SQLException {
        try (ResultSet rs = statement.executeQuery("select name, id, score from students order by score desc;")) {
            while (rs.next()) {
                System.out.printf("Student:\n  ID: %d\n  name: %s\n  score: %d\n\n",
                        rs.getInt(2),
                        rs.getString("name"),
                        rs.getInt("score"));
            }
        }
    }

    private static void simpleUpdate() throws SQLException {
        var count = statement.executeUpdate("update students set name = 'looser' where score < 100;");
        System.out.printf("Updated %d rows\n", count);
    }

    private static void simpleDropTable() throws SQLException {
        statement.execute(DROP_REQUEST);
    }

    private static void simpleInsertExample() throws SQLException {
        var count = statement.executeUpdate(SIMPLE_INSERT_REQUEST);
        System.out.printf("Inserted %d rows\n", count);
    }

    private static void createTable() throws SQLException {
        statement.execute(CREATE_REQUEST);
    }

    private static void connect() throws SQLException {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        connection = DriverManager.getConnection(DB_CONNECTION_STRING);
    }

    private static void disconnect() {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
