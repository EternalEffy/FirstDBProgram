import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBProgram program = new DBProgram();
        program.makeSomeFiles();
        program.connect();
        program.createDB();
        program.makeCatalog();
        program.showDB();
        program.closeDB();
    }
}
