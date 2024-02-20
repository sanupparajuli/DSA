public class VenueDecoration {
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;

        int n = costs.length;
        int k = costs[0].length;

        // dp[i][j] represents the minimum cost to decorate venues up to index i with theme j
        int[][] dp = new int[n][k];

        // Initialize the first row of dp with costs of the first venue
        System.arraycopy(costs[0], 0, dp[0], 0, k);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                // Initialize dp[i][j] with Integer.MAX_VALUE
                dp[i][j] = Integer.MAX_VALUE;

                for (int l = 0; l < k; l++) {
                    // Skip if l == j (adjacent venues cannot have the same theme)
                    if (l == j) continue;

                    // Update dp[i][j] by taking the minimum of the previous venue's costs
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + costs[i][j]);
                }
            }
        }

        // Find the minimum cost from the last row of dp
        int minCost = Integer.MAX_VALUE;
        for (int c : dp[n - 1]) {
            minCost = Math.min(minCost, c);
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        System.out.println(minCost(costs));
    }
}
