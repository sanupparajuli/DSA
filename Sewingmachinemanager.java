public class Sewingmachinemanager {
    public static int minMovesToEqualizeDresses(int[] dresses, int m) {
        int n = dresses.length;
        int totalDresses = 0;
        for (int dress : dresses) {
            totalDresses += dress;
        }
        int avgDressesPerMachine = totalDresses / n;
        if (totalDresses % n != 0) {
            return -1; // Cannot distribute dresses equally
        }
        
        int moves = 0;
        int[] targetDresses = new int[n];
        for (int i = 0; i < n; i++) {
            targetDresses[i] = avgDressesPerMachine;
        }
        
        for (int i = 0; i < n - m + 1; i++) {
            for (int j = i; j < i + m - 1; j++) {
                int diff = dresses[j] - targetDresses[j];
                dresses[j] -= diff;
                dresses[j + 1] += diff;
                moves += Math.abs(diff);
            }
        }
        
        return moves;
    }

    public static void main(String[] args) {
        int[] dresses = {1, 0, 5};
        int m = 2; // Number of consecutive sewing machines per move
        int minMoves = minMovesToEqualizeDresses(dresses, m);
        System.out.println("Minimum moves required: " + minMoves);
    }
}
