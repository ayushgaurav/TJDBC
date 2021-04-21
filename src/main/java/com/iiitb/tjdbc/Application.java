package com.iiitb.tjdbc;

import com.iiitb.tjdbc.core.TJdbc;

import java.sql.*;

import static com.iiitb.tjdbc.util.CommonUtils.getUrl;
import static com.iiitb.tjdbc.util.ConnectionDetails.*;

public class Application {

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(getUrl(), USER, PASSWORD);
            Statement statement = TJdbc.createStatement(connection);

            //uncomment only the part you are working on
            //executeTemporalize(statement);
            //executeInsert(statement);
            //executeFirst(statement);
            //executeLast(statement);
            //executeTUpdate(statement);
            //executeTSelectOnDate(statement);
            //executePrevious(statement);
            //executeNext(statement);
            //executeTjoin(statement);
            //executeCoalesce(statement);
            //executeEvolutionFrom(statement);
            executeEvolutionFromandTo(statement);

//            executeNext(statement);
//            executeEvolution_history(statement);
            executeTdelete(statement);
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeTUpdate(Statement statement) throws SQLException {
        String query = "tupdate student set gpa = 10.2 where id = 1 ";
        statement.executeUpdate(query);
    }

    private static void executeInsert(Statement statement) throws SQLException {
        String query = "tinsert into student values (6,'Henry','active','3.75','CSE')";
        statement.executeUpdate(query);
    }

    private static void executeLast(Statement statement) throws SQLException {
        String query = "Select last id, gpa from student";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2));
        }
    }

    private static void executeFirst(Statement statement) throws SQLException {
        String query = "Select d.id, first gpa from student where d.id <> 1";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3));
        }
    }

    private static void executeTemporalize(Statement statement) throws SQLException {
        String query = "Temporalize student";
        statement.executeUpdate(query);
    }

    private static void executeTSelectOnDate(Statement statement) throws SQLException {
        String query = "tselect gpa from student where id = 1 and date = '2019-01-21' ";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            // can parse the results accordinly with user needs
            System.out.println("Current results are : " + resultset.getString(3) + " for student id " + resultset.getInt(7));
        }
    }

    private static void executePrevious(Statement statement) throws SQLException {
        String query = "Select previous veeru name from student where id=1;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3) + " " + resultset.getString(4));
        }
    }

    private static void executeNext(Statement statement) throws SQLException {
        String query = "Select next veeru name from student where id=1;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3) + " " + resultset.getString(4));
        }
    }

    private static void executeEvolution_history(Statement statement) throws SQLException {
        String query = "select evolution_history gpa from student where s.id=3 ;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3) );
        }
    }
    private static void executeTdelete(Statement statement) throws SQLException{
        String query="tdelete from student s where s.id=3 ;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3)+ " " + resultset.getString(4));
        }
    }

    private static void executeTjoin(Statement statement) throws SQLException {
//        String query = "tselect employee e tjoin department d on e.d_id = d.d_id where d.d_id = 1 ;";
        String query = "tselect employee e tjoin department d on e.d_id = d.d_id ;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3));
        }
    }

    private static void executeCoalesce(Statement statement) throws SQLException {
        String query = "Coalesce president"; // Syntax : Coalesce tableName
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "    " + resultSet.getString(2) + "    " + resultSet.getString(3) + "    " + resultSet.getString(4));
        }
    }

    private static void executeEvolutionFrom(Statement statement) throws SQLException {
        //        "EvolutionFrom student gpa 5.2 ;"
        //        "EvolutionFrom student gpa 5.2 where id = 1 ;"
        String query = "EvolutionFrom student gpa 5.2 ;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3) + " " + resultset.getString(4));
        }
    }

    private static void executeEvolutionFromandTo(Statement statement) throws SQLException {
        //        "EvolutionFromAndTo student gpa 5.2 2.9 ;"
        //        "EvolutionFromAndTo student gpa 5.2 2.9 where id = 1 ;"
        String query = "EvolutionFromAndTo student gpa 5.2 2.9 ;";
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            System.out.println(resultset.getString(1) + " " + resultset.getString(2) + " " + resultset.getString(3) + " " + resultset.getString(4));
        }
    }
}