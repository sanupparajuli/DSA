public class EngineBuilding {
    public static int minTimeToBuild(int[] engines, int splitCost) {
        int totalTime = 0;
        int numEngineers = 1; // Initially, there is only one engineer available

        for (int i = engines.length - 1; i >= 0; i--) {
            // Splitting engineer if needed
            while (numEngineers < engines.length - i) {
                totalTime += splitCost;
                numEngineers *= 2; // Each engineer splits into two
            }

            // Assigning the maximum time to build the current engine among available engineers
            totalTime += Math.max(engines[i], 1);
            numEngineers--; // One engineer is used to build the current engine
        }

        return totalTime;
    }

    public static void main(String[] args) {
        int[] engines = {1, 2, 3};
        int splitCost = 1;
        System.out.println(minTimeToBuild(engines, splitCost));
    }
}
