public class LazyBinarySearchTree
{
    private class TreeNode
    {
        int key;
        TreeNode leftChild;
        TreeNode rightChild;
        boolean deleted;

        TreeNode(int key)
        {
            this.key = key;
            this.leftChild = null;
            this.rightChild = null;
            this.deleted = false; // default deleted as false. this only changes when we delete
        }
    }

    // Start by creating the root node of the binary tree
    TreeNode root;

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
        validateKey(key);

        // Afterwards, we check if root is null. If so, our insertion will be root.
        if (root == null)
        {
            root = new TreeNode(key);
            return true;
        }

        // Check if there exists a duplicate.
        if (contains(key) == true)
        {
            // Assuming we found an existing duplicate, we need to check if its deleted.
            TreeNode checkNode = search(key);
            if (checkNode.deleted == true)
            {
                // Mark the node as undeleted, and exit out.
                checkNode.deleted = false;
                return false;
            }
            else
            {
                // If we did find a node that was a duplicate, but was not marked as deleted, do nothing.
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
                    current = current.leftChild;
                }
                else
                {
                    current = current.rightChild;
                }
            }

            // At the last step, we check the parent node. Then, we decide what side to insert the node in.
            if (key < parent.key)
            {
                parent.leftChild = newNode;
                return true;
            }
            else
            {
                parent.rightChild = newNode;
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
        // Always check if key is within valid range.
        validateKey(key);

        // First we check if the node we want to delete is null
        if (root == null)
        {
            return false;
        }

        // Search for the node to delete, and mark it as deleted.
        TreeNode nodeToDelete = search(key);
        if (nodeToDelete == null || nodeToDelete.deleted == true)  // do nothing if its already deleted or is null
        {
            return false;
        }
        else if (contains(key) == false) // Check if it even exists
        {
            // if not, then say we cant
            return false;
        }
        else  // node does exist.
        {
            nodeToDelete.deleted = true;
            return true;
        }
    }

    public int findMin()
    {
        if (root == null)
        {
            return -1;
        }

        TreeNode current = root;
        TreeNode minNode = null;

        while (current != null)
        {
            if (!current.deleted)
            {
                minNode = current; // update min if it's not deleted
            }
            current = current.leftChild;
        }

        if (minNode == null)
        {
            return -1;
        }

        return minNode.key;
    }

    public int findMax()
    {
        if (root == null)
        {
            return -1;
        }

        TreeNode current = root;
        TreeNode maxNode = null;

        while (current != null)
        {
            if (!current.deleted)
            {
                maxNode = current;
            }
            current = current.rightChild;
        }

        if (maxNode == null)
        {
            return -1;
        }

        return maxNode.key;
    }


    public boolean contains(int key)
    {
        // Always check if key is within valid range.
        validateKey(key);

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
                // Found the node — check if it's not deleted
                return !current.deleted;
            }
        }

        // Key not found in the tree
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderPrint(root, sb);
        return sb.toString().trim(); // trim to remove leading/trailing space
    }

    private void preOrderPrint(TreeNode node, StringBuilder sb)
    {
        if (node == null)
        {
            return;
        }

        if (node.deleted)
        {
            sb.append("*");
        }

        sb.append(node.key).append(" ");

        preOrderPrint(node.leftChild, sb);
        preOrderPrint(node.rightChild, sb);
    }


    public int height()
    {
        return heightHelper(root);
    }

    private int heightHelper(TreeNode node)
    {
        if (node == null)
        {
            return -1; // base case: empty tree has height -1
        }

        int leftHeight = heightHelper(node.leftChild);
        int rightHeight = heightHelper(node.rightChild);

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public int size()
    {
        return sizeHelper(root);
    }

    private int sizeHelper(TreeNode node)
    {
        if (node == null)
        {
            return 0;
        }

        return 1 + sizeHelper(node.leftChild) + sizeHelper(node.rightChild);
    }

    private void validateKey(int key)
    {
        final int MIN_KEY = 1;
        final int MAX_KEY = 99;

        if (key < MIN_KEY || key > MAX_KEY)
        {
            throw new IllegalArgumentException("Invalid key: " + key);
        }
    }

}
