import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class ClosestValuesInBST {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Double.compare(Math.abs(b - target), Math.abs(a - target)));
        inorder(root, target, k, pq);
        
        List<Integer> result = new ArrayList<>(pq);
        Collections.sort(result);
        return result;
    }
    
    private void inorder(TreeNode root, double target, int k, PriorityQueue<Integer> pq) {
        if (root == null) return;
        
        inorder(root.left, target, k, pq);
        
        if (pq.size() < k) {
            pq.offer(root.val);
        } else {
            if (Math.abs(root.val - target) < Math.abs(pq.peek() - target)) {
                pq.poll();
                pq.offer(root.val);
            } else {
                // Since the BST is inorder traversed, if the current node's value is farther from target
                // than the peek of pq, it means subsequent values will be even farther.
                // Therefore, no need to traverse further.
                return;
            }
        }
        
        inorder(root.right, target, k, pq);
    }
    
    public static void main(String[] args) {
        ClosestValuesInBST solution = new ClosestValuesInBST();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double target = 3.8;
        int x = 2;

        List<Integer> result = solution.closestKValues(root, target, x);
        System.out.println("Closest values to " + target + ": " + result);
    }
}