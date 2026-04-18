class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Step 0: Assign unique group IDs to ungrouped items
        int totalGroups = m;
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        Map<Integer, Integer> itemToGroup = new HashMap<>();

        for (int item = 0; item < n; item++) {
            int grp = group[item] == -1 ? totalGroups++ : group[item];

            groupToItems
                    .computeIfAbsent(grp, k -> new ArrayList<>())
                    .add(item);

            itemToGroup.put(item, grp);
        }

        // Step 1: Build group dependency graph
        List<Integer>[] groupGraph = new ArrayList[totalGroups];
        for (int i = 0; i < totalGroups; i++) {
            groupGraph[i] = new ArrayList<>();
        }

        for (int item = 0; item < n; item++) {
            int toGroup = itemToGroup.get(item);

            for (int before : beforeItems.get(item)) {
                int fromGroup = itemToGroup.get(before);

                if (fromGroup != toGroup) {
                    groupGraph[fromGroup].add(toGroup);
                }
            }
        }

        List<Integer> groupOrder = topoSort(groupGraph);
        if (groupOrder.isEmpty()) return new int[0];

        // Step 2: Toposort items inside each group
        List<Integer> result = new ArrayList<>();

        for (int grp : groupOrder) {
            List<Integer> items = groupToItems.get(grp);
            if (items == null || items.isEmpty()) continue;

            int size = items.size();
            int[] indexMap = new int[n];
            for (int i = 0; i < size; i++) {
                indexMap[items.get(i)] = i;
            }

            List<Integer>[] itemGraph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                itemGraph[i] = new ArrayList<>();
            }

            for (int v : items) {
                for (int u : beforeItems.get(v)) {
                    if (itemToGroup.get(u) == grp) {
                        itemGraph[indexMap[u]].add(indexMap[v]);
                    }
                }
            }

            List<Integer> itemOrder = topoSort(itemGraph);
            if (itemOrder.isEmpty()) return new int[0];

            for (int idx : itemOrder) {
                result.add(items.get(idx));
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> topoSort(List<Integer>[] graph) {
        int n = graph.length;
        int[] indegree = new int[n];

        for (List<Integer> edges : graph) {
            for (int v : edges) {
                indegree[v]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);

            for (int nei : graph[node]) {
                if (--indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }

        return order.size() == n ? order : Collections.emptyList();
    }
}