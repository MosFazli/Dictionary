public class OBST {
    public int KEY;
    public String meaning;
    public OBST left;
    public OBST right;

    public OBST search_Recursive(OBST root, int key)  {
        // Base Cases: root is null or key is present at root
        if (root==null || root.KEY==key)
            return root;
        // val is greater than root's key
        if (root.KEY > key)
            return search_Recursive(root.left, key);
        // val is less than root's key
        return search_Recursive(root.right, key);
    }

    public void inorder_Recursive(OBST root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.KEY + " ");
            inorder_Recursive(root.right);
        }
    }

    void DISPLAY(OBST root, int nivel) {
        int i;
        if (root != null) {
            DISPLAY(root.right, nivel + 1);
            for (i = 0; i <= nivel; i++)
                System.out.print(" ");
            System.out.println(root.meaning);
            DISPLAY(root.left, nivel + 1);
        }
    }

}
