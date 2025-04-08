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
                        // handle contains
                    }
                }
                else if (textLine.equals("printtree"))
                {
                    // handle printTree
                }
                else if (textLine.equals("findmin"))
                {
                    // handle findMin
                }
                else if (textLine.equals("findmax"))
                {
                    // handle findMax
                }
                else if (textLine.equals("contains"))
                {
                    // handle contains with no argument?
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
