import java.util.*;

public class ImpactedNetworkDevices {

    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, Set<Integer>> networkMap = buildNetworkMap(edges);
        Set<Integer> impactedDevices = new HashSet<>();

        dfs(networkMap, targetDevice, impactedDevices);

        // Remove the target device itself
        impactedDevices.remove(targetDevice);

        return new ArrayList<>(impactedDevices);
    }

    private static Map<Integer, Set<Integer>> buildNetworkMap(int[][] edges) {
        Map<Integer, Set<Integer>> networkMap = new HashMap<>();

        for (int[] edge : edges) {
            int device1 = edge[0];
            int device2 = edge[1];

            networkMap.computeIfAbsent(device1, k -> new HashSet<>()).add(device2);
            networkMap.computeIfAbsent(device2, k -> new HashSet<>()).add(device1);
        }

        return networkMap;
    }

    private static void dfs(Map<Integer, Set<Integer>> networkMap, int currentDevice, Set<Integer> impactedDevices) {
        Stack<Integer> stack = new Stack<>();
        stack.push(currentDevice);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            impactedDevices.add(current);

            Set<Integer> neighbors = networkMap.getOrDefault(current, Collections.emptySet());

            for (int neighbor : neighbors) {
                if (!impactedDevices.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;

        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice);
        System.out.println("Impacted Device List: " + impactedDevices);
    }
}
