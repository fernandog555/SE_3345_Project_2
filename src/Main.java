import java.util.*;
import java.io.*;

/**
 * This is the driver class for the project.
 * This program is able to read commands from an input file and prints out the results to a user specified output file.
 * Commands are insert, deleted, contains, findMin, findMax, printTree, height and size.
 * Invalid commands that are read from the input file will raise an error and will be gracefully handled.
 * Commands are case sensitive!
 */
public class Main
{
    /**
     * This is where the program starts. Main method takes in 2 arguments from the command line, such as "input.txt output.txt".
     * It reads the commands from the input file and passes them to processCommandsFile(). Commands are case sensitive!
     *
     * @param args command-line arguments specifying input and output file names
     */
    public static void main(String[] args)
    {
        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        // Check if command line arguments were provided. Otherwise do nothing and give error to user.
        if (args.length != 2)
        {
            System.out.println("Warning: It looks like there is no arguments provided. Please check your arguments.");
            System.out.println("Usage: <input_file> <output_file>");
            return;
        }

        // Assuming we got the files opened and read.
        System.out.println("Arguments were successfully provided. Check output file for the results.");

        String inputFile = args[0];
        String outputFile = args[1];

        // Try catch block for reading and writing the files
        try (Scanner scanFile = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile)))
        {
            // Scan each line of the input file and process those commands until we reach end of file.
            while (scanFile.hasNextLine())
            {
                String textLine = scanFile.nextLine().trim();
                processCommandsFile(textLine, tree, writer);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Command file not found: " + inputFile);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to output file: " + outputFile);
        }
    }

    /**
     * Read the command of the current line and execute its corresponding operation
     *
     * @param textLine a line from the input file representing a command
     * @param tree the LazyBinarySearchTree on which to perform operations
     * @param writer the PrintWriter for writing output
     */
    private static void processCommandsFile(String textLine, LazyBinarySearchTree tree, PrintWriter writer)
    {
        try
        {

            // I believe all of these if statements are self explanatory.
            // Though for Insert, Delete, and Contains we need to parse the number we want to do the operation on.
            // They print out either true or false.
            if (textLine.startsWith("Insert:"))
            {
                int number = extractNumber(textLine);
                writer.println(tree.insert(number));
            }
            else if (textLine.startsWith("Delete:"))
            {
                int number = extractNumber(textLine);
                writer.println(tree.delete(number));
            }
            else if (textLine.startsWith("Contains:"))
            {
                int number = extractNumber(textLine);
                writer.println(tree.contains(number));
            }
            else if (textLine.equals("PrintTree"))
            {
                writer.println(tree.toString());
            }
            else if (textLine.equals("FindMin"))
            {
                writer.println(tree.findMin());
            }
            else if (textLine.equals("FindMax"))
            {
                writer.println(tree.findMax());
            }
            else if (textLine.equals("Height"))
            {
                writer.println(tree.height());
            }
            else if (textLine.equals("Size"))
            {
                writer.println(tree.size());
            }
            else
            {
                writer.println("Error in Line: " + textLine);
            }
        }
        // Catch IllegalArgExceptions that came from the binary tree class and display them here.
        // The project rubric did not specify to show what kind of error it was specifically.
        catch (IllegalArgumentException e)
        {
            if (textLine.startsWith("Insert:"))
            {
                writer.println("Error in insert: IllegalArgumentException raised");
            }
            else if (textLine.startsWith("Delete:"))
            {
                writer.println("Error in delete: IllegalArgumentException raised");
            }
            else if (textLine.startsWith("Contains:"))
            {
                writer.println("Error in contains: IllegalArgumentException raised");
            }
            else
            {
                writer.println("Error in Line: " + textLine);
            }
        }
        // Catch exception if we couldn't recognize the command.
        // It was probably spelled wrong, or it's a command that does not exist.
        catch (Exception e)
        {
            writer.println("Error in Line: " + textLine);
        }
    }

    /**
     * Helper method that extracts an integer value from a command line string.
     *
     * @param line the command line containing a number
     * @return the extracted number
     * @throws IllegalArgumentException if the number format is invalid
     */
    private static int extractNumber(String line)
    {
        try
        {
            return Integer.parseInt(line.split(":")[1].trim());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException();
        }
    }
}
