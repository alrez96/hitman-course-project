import java.util.ArrayList;

public class BFSNode implements IBFSNode {
    private static int hasher1 = 0;
    private static int hasher2 = 0;

    private int _hashCode = -1;

    public BFSNode() {
        _hashCode = hasher1++;
    }

    public BFSNode(int i) {
        _hashCode = hasher2++;
    }

    @Override
    public ArrayList<IBFSNode> getAdjacentNodes() {
        return null;
    }

    @Override
    public int hashCode() {
        if (_hashCode == -1)
            throw new IllegalArgumentException("Forgot to call super constructor :|");
        return _hashCode;
    }
}
