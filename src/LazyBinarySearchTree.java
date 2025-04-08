public class LazyBinarySearchTree
{
    // Start by creating the root node of the binary tree
    TreeNode root;

    public LazyBinarySearchTree()
    {
        System.out.println("Binary Search Tree has been created.");
    }

    /*
     * Insert will add a new element to a leaf node.
     * Valid set of keys will be in the range 1-99.
     * If the new element we want to add already exists, do nothing (do not add the node).
     * However, if the new element isn't a duplicate of a not-deleted element, but is a
     * duplicate of a deleted element, then this method must mark the "deleted" element
     * as undeleted.
     * The boolean that gets returned must indicate whether the method has physically
     * inserted a new node (false), or if we "undeleted" (true) an element.
     */
    public boolean insert(int key)
    {
        TreeNode newNode = new TreeNode(key);

        // First and foremost, we check if the value is actually within accepted range [1-99].
        if (key > 99 || key < 1)
        {
            System.out.println("Key " + key + " is out of range! Must be between 1 and 99.");
            return false;
        }

        // Afterwards, we check if root is null. If so, our insertion will be root.
        if (root == null)
        {
            root = new TreeNode(key);
            System.out.println("Insertion of node was root."); // Debug to see if root was placed
            return false;
        }

        // Need to make existingElement method to check if we already have an existing element similar to the one we wanna add.
        // Traversal through the tree to find.
        if (existingElement(key) == true)
        {
            System.out.println("Key " + key + " is already exists! Element will be skipped.");
        }
        else
        {
            // Create current pointer and set it as root to we can start looking through nodes, beginning at root.
            TreeNode current = root;
            TreeNode parent = null;

            // For each call of the method, we start at the root node, and work our way down.
            // For each iteration of the loop, we compare each node and their leafs to see
            // which one to hop to. Once thats done, we exit out the loop and move on.
            while (current != null)
            {
                // Parent will be set to current. On the first iteration, we compare root.
                parent = current;

                // Look at current node and compare values to the key we want to insert.
                // Once we compare, we keep repeating until its time to actually insert.
                if (key < current.key)
                {
                    System.out.println("Going left from " + current.key);
                    current = current.leftChild;
                }
                else
                {
                    System.out.println("Going right from " + current.key);
                    current = current.rightChild;
                }
            }

            // At the last step, we check the parent node. Then, we decide what side to insert the node in.
            if (key < parent.key)
            {
                parent.leftChild = newNode;
                System.out.println("Insertion is left child of " + key + ".");
            }
            else
            {
                parent.rightChild = newNode;
                System.out.println("Insertion is right child of " + key + ".");
            }
        }

        return false;
    }

    public boolean existingElement(int key)
    {
        // TODO: Create traversal algorithm to search through the tree to find the key.
        return false;
    }

    public boolean delete(int key)
    {
        return false;
    }

    public int findMin()
    {
        return 0;
    }

    public int findMax()
    {
        return 0;
    }

    public boolean contains(int key)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "";
    }

    public int height()
    {
        return 0;
    }

    public int size()
    {
        return 0;
    }
}
