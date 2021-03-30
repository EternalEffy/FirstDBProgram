import java.sql.*;

public class DBProgram {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connect(){
        try{
            connection=null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Connection");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB(){
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'age' INT,'score' INT,'level' INT);");
            System.out.println("Table was created or already exist");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(String name,String age,String score,String level){
        try {
            statement.execute(new User(name,age,score,level).requestAdd());
            System.out.println("Request addUser with UserName: "+name+" |Age:  "+age+" |Score:  "+score+" |Level: "+level+"| was added successful");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showDB(){
        try {
            resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next())
            {
                System.out.println( "ID = " + resultSet.getInt("id"));
                System.out.println( "name = " + resultSet.getString("name"));
                System.out.println( "age = " + resultSet.getString("age"));
                System.out.println( "score = " + resultSet.getString("score"));
                System.out.println( "level = " + resultSet.getString("level"));
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

