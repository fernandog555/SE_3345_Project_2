public class TreeNode
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
