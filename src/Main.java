import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBProgram program = new DBProgram();
        Generator generator = new Generator();
        program.connect();
        program.createDB();
        for(int i=0;i<10;i++) {
            program.addUser(generator.generateName(), generator.generateAge(), generator.generateScore(), generator.generateLevel());
        }
        program.showDB();
        program.closeDB();
    }
}
