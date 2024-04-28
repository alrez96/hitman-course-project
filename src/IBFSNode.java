import java.util.ArrayList;

public interface IBFSNode {

    ArrayList<IBFSNode> getAdjacentNodes();

    int hashCode();
}
