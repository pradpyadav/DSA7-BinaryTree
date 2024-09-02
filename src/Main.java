//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Binary tree Class!");


        Node n1 = new Node(10);
        Node n2 = new Node(4);
        Node n3 = new Node(20);
        Node n4 = new Node(3);
        Node n5 = new Node(7);
        Node n6 = new Node(5);

        Node root = n1;
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        System.out.println("Performing inorder traversal");
        inorder(root);
        System.out.println();
        System.out.println("Preforming PREORDER traversal");
        preorder(root);
        System.out.println();
        System.out.println("Performing POSTORDEr traversal");
        postorder(root);
        System.out.println();
        System.out.println("Printing the minimum in the Binary Tree : ");
        System.out.println(minmumInTree(root));

        System.out.println();
        System.out.println("Height of The Binary tree : "+height(root));
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    static int minmumInTree(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        int minFromLeft = minmumInTree(root.left);
        int minFromRight = minmumInTree(root.right);
        return Math.min(Math.min(minFromLeft, minFromRight), root.data);
    }

    static int height(Node root){
        if(root==null) return 0;
        int heightLeft= height(root.left);
        int heightRight = height(root.right);
        return Math.max(heightLeft,heightRight)+1;
    }

    static class Node {
        int data;

        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}