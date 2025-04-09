import java.util.*;
import java.io.*; 

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // Create all the objects that we are gonna need
        Scanner scanUser = new Scanner(System.in);
        LazyBinarySearchTree tree = new LazyBinarySearchTree();
        
        //System.out.print("Enter commands file name: ");
        //String INPUTFILE = scanUser.nextLine();

        String INPUTFILE = "test.txt";
        
        try (Scanner scanFile = new Scanner(new File(INPUTFILE)))
        { 
            while (scanFile.hasNextLine())
            {
                String textLine = scanFile.nextLine().trim().toLowerCase();
                System.out.println("\n" + textLine);
        
                if (textLine.startsWith("insert:") || textLine.startsWith("delete:") || textLine.startsWith("contains:"))
                {
                    int number = extractNumber(textLine);
        
                    if (textLine.startsWith("insert:"))
                    {
                        tree.insert(number);
                        //System.out.println("Value " + number + " has been inserted.");
                    }
                    else if (textLine.startsWith("delete:"))
                    {
                        // handle delete
                    }
                    else if (textLine.startsWith("contains:"))
                    {
                        if (tree.contains(number) == true)
                        {
                            System.out.println(number + " is in the tree");
                        }
                        else
                        {
                            System.out.println(number + " is NOT in the tree");
                        }
                    }
                }
                else if (textLine.equals("printtree"))
                {
                    // handle printTree
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
                    // handle height
                }
                else if (textLine.equals("size"))
                {
                    // handle size
                }
                else
                {
                    System.out.println("Unknown command");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Command file not found: " + INPUTFILE);
        }
    }
    
    private static int extractNumber(String line)
    {
        return Integer.parseInt(line.split(":")[1].trim());
    }

}
