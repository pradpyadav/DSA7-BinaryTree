import java.util.ArrayList;

public class CousinBinaryTree {
    static Node xPar, yPar;
    static int xLevel, yLevel;


    static void areCousinsHelper(Node node, Node par, int x, int y, int level) {
        if (node == null) return;
        if (node.data == x) {
            xPar = par;
            xLevel = level;

        }
        if (node.data == y) {
            yPar = par;
            yLevel = level;
        }

        areCousinsHelper(node.left, node, x, y, level + 1);
        areCousinsHelper(node.right, node, x, y, level + 1);

    }

    static boolean areCousings(Node root, int x, int y) {
        areCousinsHelper(root, null, x, y, 0);
        boolean onSameLevel = xLevel == yLevel;
        boolean haveDiffParents = xPar != yPar;
        return onSameLevel && haveDiffParents;
    }

    static class Node {
        int data;

        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
    static void nodesAtLevelK(Node root, int level, ArrayList<Integer> ans){
        if(root==null || level<0) return;
        if(level==0){
            ans.add(root.data);
        }
        nodesAtLevelK(root.left,level-1,ans);
        nodesAtLevelK(root.right, level-1,ans);

    }

    public static void main(String[] args) {
        System.out.println("Welcome to binary Cousin tree Program");

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node root = n1;
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.right = n5;


        System.out.println("Are cousins" + n4.data + " " + n5.data);
        System.out.println(areCousings(root, n4.data, n5.data));

        ArrayList<Integer> ans = new ArrayList<>();
        nodesAtLevelK(root,2,ans);
        System.out.println("The nodes at Level "+2+" : "+ans);

    }
}
