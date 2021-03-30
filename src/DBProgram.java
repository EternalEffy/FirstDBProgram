import org.json.JSONObject;

import java.io.*;
import java.sql.*;

public class DBProgram {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private File catJson,catDB;
    private JSONObject jsonObject;

    public void makeSomeFiles(){
        catJson = new File("Catalog.json");
        catDB = new File("Catalog.db");
        connection=null;
        try {
            jsonObject = new JSONObject(new BufferedReader( new FileReader(catJson)).readLine());
        }catch (IOException e) {
            e.printStackTrace();
        }
        catDB = new File("Catalog.db");
    }

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Catalog.db");
            System.out.println("Connection");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB(){
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists 'fileInfo' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'fileName' VARCHAR, 'path' VARCHAR,'extension' VARCHAR,'comment' VARCHAR);");
            System.out.println("Table was created or already exist");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void makeCatalog(){
        try {
            for(int i=0;i<jsonObject.getJSONArray("fileInfo").length();i++) {
                statement.execute(new FileInfo(get(i, "fileInfo").getString("fileName"), get(i, "fileInfo").getString("path"), get(i, "fileInfo").getString("extension"), get(i, "fileInfo").getString("comment")).requestAdd());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public JSONObject get(int index, String listName){
        return index>jsonObject.getJSONArray(listName).length()-1? null : jsonObject.getJSONArray(listName).getJSONObject(index);
    }

    public void showDB(){
        try {
            resultSet = statement.executeQuery("SELECT * FROM fileInfo");
            while(resultSet.next())
            {
                System.out.println( "ID = " + resultSet.getInt("id"));
                System.out.println( "fileName = " + resultSet.getString("fileName"));
                System.out.println( "path = " + resultSet.getString("path"));
                System.out.println( "extension = " + resultSet.getString("extension"));
                System.out.println( "comment = " + resultSet.getString("comment"));
                System.out.println();
            }
            System.out.println("Table was showed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connection close");
    }
}

