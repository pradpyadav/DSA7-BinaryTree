import java.util.ArrayDeque;

public class BinarySearchTreeMain {


    public static void main(String[] args) {

        Node n1 = new Node(8);
        Node n2 = new Node(4);
        Node n3 = new Node(10);
        Node n4 = new Node(1);
        Node n5 = new Node(6);
        Node n6 = new Node(14);
        Node n7 = new Node(4);
        Node n8 = new Node(7);


        Node root = n1;
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n5.left = n7;
        n5.right = n8;

        boolean result = search(root, 6);
        System.out.println("The search result returned is : " + result);
        printInorderBST(root);
        System.out.println();
        insert(root, 5);
        System.out.println();
        printInorderBST(root);
        System.out.println();
        insert(root, 50);
        printInorderBST(root);

        System.out.println();
        delete(root, 50);
        printInorderBST(root);
        System.out.println();
        System.out.println("Printing after second element deleted");
        delete(root, 4);
        printInorderBST(root);
        System.out.println();
        System.out.println("Cehcking if the given tree is BSt or not:" + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println();
        System.out.println("Noe performing teh twosumusing bST");
        System.out.println(" Fro calcualting teh sum 18 exist in bst or not : "+twoSumBST(root,18));
    }

    static boolean twoSumBST(Node root, int targetSum) {
        Node cur1 = root, cur2 = root;
        int val1 = 0, val2 = 0;
        boolean done1 = false, done2 = false;
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();
        while (true) {
            //iterate inorder
            while (done1 == false) {
                if (cur1 != null) {
                    stack1.push(cur1);
                    cur1 = cur1.left;
                } else {
                    if (stack1.isEmpty()) {
                        done1 = true;
                    } else {
                        Node pop = stack1.pop();
                        val1 = pop.data;
                        cur1 = pop.right;
                        done1 = true;
                    }
                }
            }

            //iterate reverseinorder
            while (done2 == false) {
                if (cur2 != null) {
                    stack2.push(cur2);
                    cur2 = cur2.right;
                } else {
                    if (stack2.isEmpty()) {
                        done2 = true;
                    } else {
                        Node pop = stack2.pop();
                        val2 = pop.data;
                        cur2 = pop.left;
                        done2 = true;
                    }
                }
            }

            //calcualte the sum using teh current val values
            int sum = val1 + val2;
            if (val1 != val2 && sum == targetSum) {
                return true;
            } else if (val1 + val2 < targetSum) {
                done1 = false;
            } else if (val1 + val2 > targetSum) {
                done2 = false;

            }
            if (val1 >= val2) {
                return false;
            }
        }

    }

    static Node getInorderPredecessor(Node root, int key, Node predecessor) {
        if (root == null) return null;
        if (root.data > key) {//moving to left
            return getInorderPredecessor(root.left, key, predecessor);
        } else if (root.data < key) {//moving to right
            predecessor = root;
            return getInorderPredecessor(root.right, key, predecessor);
        } else {
            if (root.left == null) {
                return predecessor;
            } else {
                return getMax(root.left);
            }
        }
    }

    static Node getMax(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    static boolean isBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }

    static void printInorderBST(Node root) {
        if (root == null) return;
        printInorderBST(root.left);
        System.out.print(root.data + " ");
        printInorderBST(root.right);
    }

    static Node delete(Node root, int key) {
        if (root == null) return null;
        if (root.data > key) {
            root.left = delete(root.left, key);
        } else if (root.data < key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            //if there is left child and right child of the given Node
            //find the inorDERSuccessor for this given nodes right subtree

            Node inOrderSuccessorNode = findSmallest(root.right);
            root.data = inOrderSuccessorNode.data;
            root.right = delete(root.right, inOrderSuccessorNode.data);
        }
        return root;

    }

    static Node findSmallest(Node root) {

        Node temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    static Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);

        if (root.data > key) {
            root.left = insert(root.left, key);
        }
        if (root.data < key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    static boolean search(Node root, int key) {
        if (root == null) {
            System.out.println("Given key doesnot exist as the root itself is null!!!");
            return false;
        }

        if (root.data > key) {
            //search in teh left sub tree as teh key is less than the given root data value.
            return search(root.left, key);
        }
        if (root.data < key) {
            //search in the right sub tree as the key is greater than the given root data value.
            return search(root.right, key);
        }

        return true;
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
