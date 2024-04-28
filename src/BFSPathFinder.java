import java.util.*;

public class BFSPathFinder {

    public static ArrayList<IBFSNode> shortestPath(IBFSNode startNode, IBFSNode endNode) {
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, IBFSNode> hashToNode = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> parent = new HashMap<>();

        hashToNode.put(startNode.hashCode(), startNode);
        visited.add(startNode.hashCode());
        parent.put(startNode.hashCode(), startNode.hashCode());
        q.add(startNode.hashCode());

        while (! q.isEmpty()) {
            Integer hash = q.poll();
            IBFSNode node = hashToNode.get(hash);
            ArrayList<IBFSNode> children = node.getAdjacentNodes();

            for (IBFSNode child: children) {
                if (!visited.contains(child.hashCode())) {
                    visited.add(child.hashCode());
                    parent.put(child.hashCode(), node.hashCode());
                    hashToNode.put(child.hashCode(), child);
                    q.add(child.hashCode());
                }
                if (child.hashCode() == endNode.hashCode()) {
                    q.clear();
                    break;
                }
            }
        }

        ArrayList<IBFSNode> path = new ArrayList<>();
        if (visited.contains(endNode.hashCode())) {
            IBFSNode finder = endNode;
            IBFSNode par = hashToNode.get(parent.get(finder.hashCode()));
            do {
                path.add(0, finder);
                finder = par;
                par = hashToNode.get(parent.get(finder.hashCode()));
            } while (par.hashCode() != finder.hashCode());

        } else {
            path = null;
        }

        return path;
    }
}
