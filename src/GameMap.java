import java.util.ArrayList;

public class GameMap {

    public class MyNode extends BFSNode {
        public MyNode up, right, down, left;

        public MyNode() {
            super();
        }

        public MyNode(int i) {
            super(i);
        }

        @Override
        public ArrayList<IBFSNode> getAdjacentNodes() {
            ArrayList<IBFSNode> ret = new ArrayList<>();
            if (up != null)
                ret.add(up);
            if (right != null)
                ret.add(right);
            if (down != null)
                ret.add(down);
            if (left != null)
                ret.add(left);
            return ret;
        }
    }

    public void addConnectionsToMap(MyNode[] nodes, int[][] arrayMap) {
        for (int[] anArrayMap : arrayMap) {
            int src = anArrayMap[0];
            int pos = anArrayMap[1];
            int side = anArrayMap[2];
            if (side == 1) {
                nodes[src].down = nodes[pos];
                nodes[pos].up = nodes[src];
            } else if (side == 2) {
                nodes[src].right = nodes[pos];
                nodes[pos].left = nodes[src];
            }
        }
    }

    public void addConnectionsToMap2(MyNode[] nodes, int[][] arr, int j) {
        int value;
        for (int i = 0; i < arr.length; i++) {
            value = arr[i][0];
            for (int k = (i + 1); k < arr.length; k++){
                if ((arr[k][0] - 2) == value){
                    nodes[i].right = nodes[k];
                    nodes[k].left = nodes[i];
                }
                if ((arr[k][0] - (2 * j) == value)){
                    nodes[i].down = nodes[k];
                    nodes[k].up = nodes[i];
                }
            }
        }
    }
}