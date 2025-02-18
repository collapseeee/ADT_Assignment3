import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declare usage variables:
        String fileName;
        String infixExpression = "";

        // Argument handling and input file parsing:
        if (args.length == 0) {
            System.out.println("Please provide a file name as a command-line argument.");
            return;
        }
        if (args.length > 1) {
            System.out.println("Invalid number command-line argument.");
            return;
        }
        fileName = args[0];
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                infixExpression = input.nextLine().replace(" ","");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File name `" + fileName + "` not found");
        }
        System.out.println(infixExpression);
    }
}