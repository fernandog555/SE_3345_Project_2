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
     *
     * The boolean that gets returned must indicate whether the method has physically
     * inserted a new node (true), or if we "undeleted" (false) an element.
     */
    public boolean insert(int key)
    {
        TreeNode newNode = new TreeNode(key);

        // First and foremost, we check if the value is actually within accepted range [1-99].
        if (key > 99 || key < 1)
        {
            System.out.println("Key " + key + " is out of range! Must be between 1 and 99.");
            System.out.println("Element will be skipped.");
            return false;
            // TODO: We may need to add some mode checks and testing for this edge case
        }

        // Afterwards, we check if root is null. If so, our insertion will be root.
        if (root == null)
        {
            root = new TreeNode(key);
            System.out.println("Insertion of node was root.");
            return true;
        }

        // Check if there exists a duplicate.
        if (contains(key) == true)
        {
            System.out.println("Found existing duplicate key. Checking if its marked as deleted.");

            // Assuming we found an existing duplicate, we need to check if its deleted.
            TreeNode checkNode = search(key);
            if (checkNode.deleted == true)
            {
                System.out.println("Node was marked as deleted. It is now undeleted.");
                // Mark the node as undeleted, and exit out.
                checkNode.deleted = false;
                return false;
            }
            else
            {
                // If we did find a node that was a duplicate, but was not marked as deleted, do nothing.
                System.out.println("Node was NOT marked as deleted. Element has been skipped.");
                return false;
            }
        }
        else // There was no existing duplicate, so we physically insert a node into the tree.
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
                return true;
            }
            else
            {
                parent.rightChild = newNode;
                System.out.println("Insertion is right child of " + key + ".");
                return true;
            }
        }
    }

    // Helper method that searches for a node and returns it. Unlike contains(), it will not give back a boolean.
    public TreeNode search(int key)
    {
        TreeNode current = root;
        while (current != null)
        {
            if (key < current.key)
            {
                current = current.leftChild;
            }
            else if (key > current.key)
            {
                current = current.rightChild;
            }
            else
            {
                return current; // Found
            }
        }
        return null; // Not found
    }

    // false = node was not deleted
    // true = node was deleted
    // TODO: It would be nice to reorganize some of the logic so its more readable. Also to implement an ability to say if we are trying to delete a null pointer, or the node was already marked as deleted.
    public boolean delete(int key)
    {
        // First we check if the node we want to delete is null
        if (root == null)
        {
            System.out.println("Warning: Root node is null! There is nothing to delete!");
            return false;
        }

        // Search for the node to delete, and mark it as deleted.
        TreeNode nodeToDelete = search(key);
        if (nodeToDelete == null || nodeToDelete.deleted == true)  // do nothing if its already deleted or is null
        {
            System.out.println("Element " + key + " was not deleted either because it was already deleted, or is null.");
            return false;
        }
        else if (contains(key) == false) // Check if it even exists
        {
            // if not, then say we cant
            System.out.println("Warning: Element " + key + " was not deleted. Element does not exist.");
            return false;
        }
        else  // node does exist.
        {
            System.out.println("Element " + key + " was deleted.");
            nodeToDelete.deleted = true;
            return true;
        }
    }

    public int findMin()
    {
        // First, check if there is a root
        if (root == null)
        {
            System.out.println("Warning: Tree is empty!");
            return -1;
        }

        TreeNode current = root;
        TreeNode minNode = null;

        while (current != null)
        {
            minNode = current;
            current = current.leftChild;
        }
        // Found minimum
        return minNode.key;
    }

    public int findMax()
    {
        // First, check if there is a root
        if (root == null)
        {
            System.out.println("Warning: Tree is empty!");
            return -1;
        }

        TreeNode current = root;
        TreeNode minNode = null;

        while (current != null)
        {
            minNode = current;
            current = current.rightChild;
        }
        // Found minimum
        return minNode.key;
    }

    public boolean contains(int key)
    {
        TreeNode current = root;
        while (current != null)
        {
            if (key < current.key)
            {
                current = current.leftChild;
            }
            else if (key > current.key)
            {
                current = current.rightChild;
            }
            else
            {
                // Found node
                return true;
            }
        }
        // Did not find node
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
