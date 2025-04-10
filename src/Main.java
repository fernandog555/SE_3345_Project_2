/**
 * Main driver class for LazyBinarySearchTree.
 * This program reads tree operations from an input file and writes the results to an output file.
 * Commands include insert, delete, contains, findMin, findMax, printTree, height, and size.
 * Invalid commands and out-of-range keys are gracefully handled.
 */
import java.util.*;
import java.io.*;

public class Main
{
    /**
     * Entry point of the program. Takes two command-line arguments: input file and output file.
     *
     * @param args command-line arguments specifying input and output file names
     */
    public static void main(String[] args)
    {
        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        if (args.length != 2)
        {
            System.out.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (Scanner scanFile = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile)))
        {
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
     * Processes a single command line from the input file and performs the corresponding tree operation.
     *
     * @param textLine a line from the input file representing a command
     * @param tree the LazyBinarySearchTree on which to perform operations
     * @param writer the PrintWriter for writing output
     */
    private static void processCommandsFile(String textLine, LazyBinarySearchTree tree, PrintWriter writer)
    {
        try
        {
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
        catch (Exception e)
        {
            writer.println("Error in Line: " + textLine);
        }
    }

    /**
     * Extracts an integer value from a command line string.
     * Expected format: Command:<number>
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
