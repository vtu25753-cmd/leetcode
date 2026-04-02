 class ThroneInheritance {

    private Map<String, Node> nodes;
    private String kingName;

    public ThroneInheritance(String kingName) {
        this.nodes = new HashMap<>();
        this.kingName = kingName;
        Node king = new Node(kingName, null);
        nodes.put(kingName, king);
    }
    
    public void birth(String parentName, String childName) {
        Node parent = nodes.get(parentName);
        Node child = new Node(childName, parent);
        parent.children.add(child);
        nodes.put(childName, child);
    }
    
    public void death(String name) {
        Node node = nodes.get(name);
        node.alive = false;
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        buildOrder(kingName, order);
        return order;
    }

    private void buildOrder(String current, List<String> order) {
        
        Node node = nodes.get(current);

        if (node.alive) {
            order.add(current);
        }
        for (Node child : node.children) {
            buildOrder(child.name, order);
        }
    }

    class Node {
        String name;
        Node parent;
        List<Node> children;
        boolean alive;

        public Node(String name, Node parent) {
            this.name = name;
            this.parent = parent;
            this.children = new ArrayList<>();
            this.alive = true;
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */