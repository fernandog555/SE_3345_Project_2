import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // Create all the objects that we are gonna need
        Scanner scanUser = new Scanner(System.in);
        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        //System.out.print("Enter commands file name: ");
        //String INPUTFILE = scanUser.nextLine();

        String INPUTFILE = "input.txt";

        try (Scanner scanFile = new Scanner(new File(INPUTFILE)))
        {
            while (scanFile.hasNextLine())
            {
                String textLine = scanFile.nextLine().trim().toLowerCase();
                System.out.println("\n" + textLine);
                processCommandsFile(textLine, tree);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Command file not found: " + INPUTFILE);
        }
    }

    private static void processCommandsFile(String textLine, LazyBinarySearchTree tree)
    {
        if (textLine.startsWith("insert:"))
        {
            int number = extractNumber(textLine);
            tree.insert(number);
            //System.out.println("Value " + number + " has been inserted.");
        }
        else if (textLine.startsWith("delete:"))
        {
            int number = extractNumber(textLine);
            tree.delete(number);
        }
        else if (textLine.startsWith("contains:"))
        {
            int number = extractNumber(textLine);
            if (tree.contains(number) == true)
            {
                System.out.println(number + " is in the tree");
            }
            else
            {
                System.out.println(number + " is NOT in the tree");
            }
        }
        else if (textLine.equals("printtree"))
        {
            System.out.println(tree.toString());
        }
        else if (textLine.equals("findmin"))
        {
            System.out.println("The minimum value is: " + tree.findMin());
        }
        else if (textLine.equals("findmax"))
        {
            System.out.println("The maximum value is: " + tree.findMax());
        }
        else if (textLine.equals("height"))
        {
            tree.height();
            System.out.println("Tree height: " + tree.height());
        }
        else if (textLine.equals("size"))
        {
            System.out.println("Tree size: " + tree.size());
        }
        else
        {
            System.out.println("Error in Line: " + textLine);
        }
    }

    private static int extractNumber(String line)
    {
        return Integer.parseInt(line.split(":")[1].trim());
    }
}
