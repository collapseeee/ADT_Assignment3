import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class InfixToPostfixMain {
    public static void main(String[] args) {
        // Declare usage variables:
        String fileName;
        ArrayList<String> inputList = new ArrayList<>();
        Convertor convertor = new Convertor();

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
                String infixExpression = input.nextLine();
                inputList.add(infixExpression);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File name `" + fileName + "` not found");
        }

        convertor.getToStringArrayListResult(inputList);
    }
    public static void printAllInList(ArrayList<String> list) {
        for (String o : list) {
            System.out.println(o);
        }
    }
}