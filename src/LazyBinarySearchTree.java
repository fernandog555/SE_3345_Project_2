/**
 * This class goes alongside Main. We receive commands from Main and process the logic here.
 * Keys must be in the range [1, 99]. Otherwise, IllegalArgumentException will get thrown.
 * Deleted nodes are marked as "deleted" but are not physically removed.
 */
public class LazyBinarySearchTree
{
    /**
     * Inner class representing a node in the binary search tree.
     */
    private class TreeNode
    {
        int key;
        TreeNode leftChild;
        TreeNode rightChild;
        boolean deleted;

        /**
         * Constructs a new TreeNode with the given key.
         * @param key the key value of the node
         */
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

    /**
     * Physically inserts a new element into the tree if it is not a duplicate.
     * It undeletes an existing logically deleted node.
     *
     * @param key the value to insert (must be in range 1–99)
     * @return true if a new node was physically inserted, false if a deleted node was undeleted or key already existed
     * @throws IllegalArgumentException if key is outside valid range
     */
    public boolean insert(int key)
    {
        // First and foremost, we check if the value is actually within accepted range [1-99].
        validateKey(key);

        // If the tree is empty, just make the root node
        if (root == null)
        {
            root = new TreeNode(key);
            return true;  // physically inserted
        }

        // Search for any node with this key, whether deleted or not
        TreeNode found = search(key);
        if (found != null)
        {
            // The node already exists
            if (found.deleted)
            {
                // "Undelete" it
                found.deleted = false;
            }
            // Return false because no new node is physically inserted
            return false;
        }

        // There was no existing duplicate, so we physically insert a node into the tree.
        // Create current pointer and set it as root to we can start looking through nodes, beginning at root.
        TreeNode current = root;
        TreeNode parent = null;
        TreeNode newNode = new TreeNode(key);

        // For each call of the method, we start at the root node, and work our way down.
        // For each iteration of the loop, we compare each node and their leafs to see
        // which one to hop to. Once that is done, we exit out the loop and move on.
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
        }
        else
        {
            parent.rightChild = newNode;
        }
        return true;
    }

    /**
     * Searches for a node with the given key.
     *
     * @param key the key to search for
     * @return the TreeNode with the given key, or null if not found
     */
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

    /**
     * Marks a node with the given key as logically deleted.
     *
     * @param key the value to delete (must be in range 1–99)
     * @return true if the node was successfully marked as deleted, false otherwise
     * @throws IllegalArgumentException if key is outside valid range
     */
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

    /**
     * Finds the minimum key that is not marked as deleted.
     *
     * @return the minimum undeleted key, or -1 if none exists
     */
    public int findMin()
    {
        if (root == null)
        {
            return -1;
        }

        TreeNode current = root;
        TreeNode minNode = null;

        // Search through the tree to find the minimum. Going left is always the smallest node.
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

    /**
     * Finds the maximum key that is not marked as deleted.
     *
     * @return the maximum undeleted key, or -1 if none exists
     */
    public int findMax()
    {
        // Always check if root is null.
        if (root == null)
        {
            return -1;
        }

        TreeNode current = root;
        TreeNode maxNode = null;

        // Search through the tree to find the maximum. Going right is always the biggest node.
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

    /**
     * Checks whether the tree contains the given key and it is not marked as deleted.
     *
     * @param key the key to search for (must be in range 1–99)
     * @return true if the key exists and is not deleted, false otherwise
     * @throws IllegalArgumentException if key is outside valid range
     */
    public boolean contains(int key)
    {
        // Always check if key is within valid range.
        validateKey(key);
        if (root == null)
        {
            return false;
        }

        TreeNode current = root;

        // Search through the tree and check if the we found is deleted.
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
                // Found the node, check if it's not deleted
                return !current.deleted;
            }
        }

        // Key not found in the tree
        return false;
    }

    /**
     * Returns a pre-order traversal of the tree including deleted nodes (marked with *).
     *
     * @return a string representing the pre-order traversal of the tree
     */
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

    /**
     * Returns the height of the tree (including deleted nodes).
     *
     * @return the height of the tree, or -1 if the tree is empty
     */
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

    /**
     * Returns the number of nodes in the tree (including deleted nodes).
     *
     * @return the size of the tree
     */
    public int size()
    {
        return sizeHelper(root);
    }

    // Recursive helper method to find the size  of the whole tree.
    private int sizeHelper(TreeNode node)
    {
        if (node == null)
        {
            return 0;
        }

        return 1 + sizeHelper(node.leftChild) + sizeHelper(node.rightChild);
    }

    /**
     * Validates that a key is within the allowed range [1, 99].
     *
     * @param key the key to validate
     * @throws IllegalArgumentException if key is outside the valid range
     */
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
