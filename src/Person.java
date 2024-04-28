import java.util.ArrayList;

public class Person {
    private int position;
    private int side; // Up: 1, Down: -1, Right: 2, Left: -2

    public Person(GameMap.MyNode[] nodes, int position, int side) {
        this.position = nodes[position].hashCode();
        this.side = side;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public String getPosition(GameMap.MyNode[] nodes) {
        return nodes[position].toString().substring(15);
    }

    /*public String getPositionWhitValue(GameMap.MyNode[] nodes, int j) {
        return nodes[j].toString().substring(15);
    }

    public int getNodesValue(GameMap.MyNode[] nodes, String str) {
        for (GameMap.MyNode node : nodes)
            if ((node.toString().substring(15)).equals(str))
                return node.hashCode();
        return 0;
    }*/

    public int getPositionInt(GameMap.MyNode[] nodes) {
        return nodes[position].hashCode();
    }

    /*public String getSide() {
        if (side == 1)
            return "Up";
        else
        if (side == -1)
            return "Down";
        else
        if (side == 2)
            return "Right";
        else
        if (side == -2)
            return "Left";

        return null;
    }*/

    public int getSideInt() {
        return side;
    }

    public int BFS(GameMap.MyNode[] nodes, int start, int end) {
        ArrayList<IBFSNode> path = BFSPathFinder.shortestPath(nodes[start], nodes[end]);
        GameMap.MyNode prevNode = nodes[start];
        int i;
        for (IBFSNode pathNode: path) {
            if (pathNode instanceof GameMap.MyNode) {
                GameMap.MyNode myPathNode = (GameMap.MyNode)pathNode;
                if (prevNode.up == myPathNode)
                    i = 1;
                else if (prevNode.right == myPathNode)
                    i = 2;
                else if (prevNode.down == myPathNode)
                    i = -1;
                else if (prevNode.left == myPathNode)
                    i = -2;
                else {
                    return 0;
                }
                return i;
            } else {
                System.out.println("Something is wrong :/");
            }
        }
        return 0;
    }

    public int KillHitman(GameMap.MyNode[] nodes, int position, int side, int hitPosition, String enemyName){
        if (nodes[position].up != null && nodes[position].up.hashCode() == hitPosition && side == 1) {
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].up.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
            System.out.println("\nGame Over!\n" + enemyName + " Enemy Killed You!");
            return 1;
        }
        else
        if (nodes[position].down != null && nodes[position].down.hashCode() == hitPosition && side == -1) {
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].down.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
            System.out.println("\nGame Over!\n" + enemyName + " Enemy Killed You!");
            return 1;
        }
        else
        if (nodes[position].right != null && nodes[position].right.hashCode() == hitPosition && side == 2) {
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].right.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
            System.out.println("\nGame Over!\n" + enemyName + " Enemy Killed You!");
            return 1;
        }
        else
        if (nodes[position].left != null && nodes[position].left.hashCode() == hitPosition && side == -2) {
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].left.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
            System.out.println("\nGame Over!\n" + enemyName + " Enemy Killed You!");
            return 1;
        }
        return 0;
    }

    public void MoveGoalEnemy(GameMap.MyNode[] nodes, int position, int goal, String enemyName){
        if (BFS(nodes, position, goal) == 1){
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].up.hashCode());
            setSide(1);
            System.out.println(" ==> " + getPosition(nodes));}
        else
        if (BFS(nodes, position, goal) == -1){
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].down.hashCode());
            setSide(-1);
            System.out.println(" ==> " + getPosition(nodes));}
        else
        if (BFS(nodes, position, goal) == 2){
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].right.hashCode());
            setSide(2);
            System.out.println(" ==> " + getPosition(nodes));}
        else
        if (BFS(nodes, position, goal) == -2){
            System.out.print(enemyName + " Enemy: " + getPosition(nodes));
            setPosition(nodes[position].left.hashCode());
            setSide(-2);
            System.out.println(" ==> " + getPosition(nodes));}
    }

    public void MoveGoalEnemyNext(GameMap.MyNode[] nodes, int position, int goal){
        if (BFS(nodes, position, goal) == 1)
            setSide(1);
        else
        if (BFS(nodes, position, goal) == -1)
            setSide(-1);
        else
        if (BFS(nodes, position, goal) == 2)
            setSide(2);
        else
        if (BFS(nodes, position, goal) == -2)
            setSide(-2);
    }
}

class Hitman extends Person {
    int dead = 0;
    public Hitman(GameMap.MyNode[] nodes, int position) {
        super(nodes, position, 0);
    }

    public int KillEnemy(GameMap.MyNode[] nodes, int position, int enemyPosition, String eName) {
        if (nodes[position].hashCode() == enemyPosition) {
            System.out.println(eName + " Enemy: Killed By Hitman!");
            return 1;}
        return 0;
    }

    public void Move(GameMap.MyNode[] nodes, int position, Screen screen) {
        System.out.println();
        ArrayList<Integer> array = new ArrayList<>();
        String i;
        System.out.println("Your choice(s):");
        if (nodes[position].up != null){
            System.out.println("Up");array.add(1);}
        if (nodes[position].down != null){
            System.out.println("Down");array.add(-1);}
        if (nodes[position].right != null){
            System.out.println("Right");array.add(2);}
        if (nodes[position].left != null){
            System.out.println("Left");array.add(-2);}

        i = screen.ButtonClick(array, position);
        screen.ButtonColor();
        System.out.println("Choice: " + i);
        System.out.println();

        if (("up").equals(i) && nodes[position].up != null) {
            System.out.print("Hitman: " + getPosition(nodes));
            setPosition(nodes[position].up.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
        } else if (("down").equals(i) && nodes[position].down != null) {
            System.out.print("Hitman: " + getPosition(nodes));
            setPosition(nodes[position].down.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
        } else if (("right").equals(i) && nodes[position].right != null) {
            System.out.print("Hitman: " + getPosition(nodes));
            setPosition(nodes[position].right.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
        } else if (("left").equals(i) && nodes[position].left != null) {
            System.out.print("Hitman: " + getPosition(nodes));
            setPosition(nodes[position].left.hashCode());
            System.out.println(" ==> " + getPosition(nodes));
        }
    }
}

class StaticEnemy extends Person {
    int dead = 0;
    int attention = 0;
    public StaticEnemy(GameMap.MyNode[] nodes, int position, int side) {
        super(nodes, position, side);
    }
}

class SpinicEnemy extends Person {
    int dead = 0;
    int attention = 0;
    public SpinicEnemy(GameMap.MyNode[] nodes, int position, int side) {
        super(nodes, position, side);
    }

    public void Move(int side) {
        setSide((-side));
    }
}

class DynamicEnemy extends Person {
    int dead = 0;
    int attention = 0;
    public DynamicEnemy(GameMap.MyNode[] nodes, int position, int side) {
        super(nodes, position, side);
    }

    public void Move(GameMap.MyNode[] nodes, int position, int side) {
        if (nodes[position].up == null && side == 1)
            setSide((-side));
        if (nodes[position].down == null && side == -1)
            setSide((-side));
        if (nodes[position].right == null && side == 2)
            setSide((-side));
        if (nodes[position].left == null && side == -2)
            setSide((-side));

        if (nodes[position].up != null && side == 1) {
            System.out.print("Dynamic Enemy: " + getPosition(nodes));
            setPosition(nodes[position].up.hashCode());
            position = getPositionInt(nodes);
            System.out.println(" ==> " + getPosition(nodes));
        } else if (nodes[position].down != null && side == -1) {
            System.out.print("Dynamic Enemy: " + getPosition(nodes));
            setPosition(nodes[position].down.hashCode());
            position = getPositionInt(nodes);
            System.out.println(" ==> " + getPosition(nodes));}
        else
        if (nodes[position].right != null && side == 2) {
            System.out.print("Dynamic Enemy: " + getPosition(nodes));
            setPosition(nodes[position].right.hashCode());
            position = getPositionInt(nodes);
            System.out.println(" ==> " + getPosition(nodes));}
        else
        if (nodes[position].left != null && side == -2) {
            System.out.print("Dynamic Enemy: " + getPosition(nodes));
            setPosition(nodes[position].left.hashCode());
            position = getPositionInt(nodes);
            System.out.println(" ==> " + getPosition(nodes));}

        if (nodes[position].up == null && side == 1)
            setSide((-side));
        if (nodes[position].down == null && side == -1)
            setSide((-side));
        if (nodes[position].right == null && side == 2)
            setSide((-side));
        if (nodes[position].left == null && side == -2)
            setSide((-side));
    }
}
