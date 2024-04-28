import java.util.ArrayList;

public class Objects {
    private int position;

    public Objects(GameMap.MyNode[] nodes, int position) {
        this.position = nodes[position].hashCode();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPosition(GameMap.MyNode[] nodes) {
        return nodes[position].toString().substring(15);
    }

    public int getPositionInt(GameMap.MyNode[] nodes) {
        return nodes[position].hashCode();
    }

    public String getPositionWhitValue(GameMap.MyNode[] nodes, int j) {
        return nodes[j].toString().substring(15);
    }

    /*public int getNodesValue(GameMap.MyNode[] nodes, String str) {
        for (GameMap.MyNode node : nodes)
            if ((node.toString().substring(15)).equals(str))
                return node.hashCode();
        return 0;
    }*/
}

class Stone extends Objects {
    int upPos = -1, downPos = -1, leftPos = -1, rightPos = -1;
    public Stone(GameMap.MyNode[] nodes, int position) {
        super(nodes, position);
    }

    public int ThrowingStone(GameMap.MyNode[] nodes, int stonePosition, Screen screen) {
        ArrayList<Integer> array = new ArrayList<>();
        String i;
        System.out.println();
        System.out.println("Your choice(s) for throwing stone:");
        if (nodes[stonePosition].up != null){
            System.out.println("Up");array.add(1);}
        if (nodes[stonePosition].down != null){
            System.out.println("Down");array.add(-1);}
        if (nodes[stonePosition].right != null){
            System.out.println("Right");array.add(2);}
        if (nodes[stonePosition].left != null){
            System.out.println("Left");array.add(-2);}

        i = screen.ButtonClick(array, stonePosition);
        screen.ButtonColor();
        System.out.println("Choice: " + i);
        System.out.println();

        if (("up").equals(i)) {
            System.out.print("Throwing Stone: " + getPosition(nodes));
            stonePosition = nodes[stonePosition].up.hashCode();
            System.out.println(" ==> " + getPositionWhitValue(nodes, stonePosition));
        } else if (("down").equals(i)) {
            System.out.print("Throwing Stone: " + getPosition(nodes));
            stonePosition = nodes[stonePosition].down.hashCode();
            System.out.println(" ==> " + getPositionWhitValue(nodes, stonePosition));
        } else if (("right").equals(i)) {
            System.out.print("Throwing Stone: " + getPosition(nodes));
            stonePosition = nodes[stonePosition].right.hashCode();
            System.out.println(" ==> " + getPositionWhitValue(nodes, stonePosition));
        } else if (("left").equals(i)) {
            System.out.print("Throwing Stone: " + getPosition(nodes));
            stonePosition = nodes[stonePosition].left.hashCode();
            System.out.println(" ==> " + getPositionWhitValue(nodes, stonePosition));
        }
        return stonePosition;
    }

    public int StoneSides(GameMap.MyNode[] nodes, int position, int enemyPos) {
        if (nodes[position].up != null)
            upPos = nodes[position].up.hashCode();
        if (nodes[position].down != null)
            downPos = nodes[position].down.hashCode();
        if (nodes[position].right != null)
            rightPos = nodes[position].right.hashCode();
        if (nodes[position].left != null)
            leftPos = nodes[position].left.hashCode();
        if (nodes[position].up != null && nodes[position].up.hashCode() == enemyPos)
            return 1;
        if (nodes[position].down != null && nodes[position].down.hashCode() == enemyPos)
            return 1;
        if (nodes[position].left != null && nodes[position].left.hashCode() == enemyPos)
            return 1;
        if (nodes[position].right != null && nodes[position].right.hashCode() == enemyPos)
            return 1;
        if (upPos != -1 && nodes[upPos].right != null && nodes[upPos].right.hashCode() == enemyPos)
            return 1;
        if (upPos != -1 && nodes[upPos].left != null && nodes[upPos].left.hashCode() == enemyPos)
            return 1;
        if (downPos != -1 && nodes[downPos].right != null && nodes[downPos].right.hashCode() == enemyPos)
            return 1;
        if (downPos != -1 && nodes[downPos].left != null && nodes[downPos].left.hashCode() == enemyPos)
            return 1;
        if (leftPos != -1 && nodes[leftPos].up != null && nodes[leftPos].up.hashCode() == enemyPos)
            return 1;
        if (leftPos != -1 && nodes[leftPos].down != null && nodes[leftPos].down.hashCode() == enemyPos)
            return 1;
        if (rightPos != -1 && nodes[rightPos].up != null && nodes[rightPos].up.hashCode() == enemyPos)
            return 1;
        if (rightPos != -1 && nodes[rightPos].down != null && nodes[rightPos].down.hashCode() == enemyPos)
            return 1;

        return 0;
    }
}

class Gun extends Objects {
    public Gun(GameMap.MyNode[] nodes, int position) {
        super(nodes, position);
    }

    public int GunVolley(GameMap.MyNode[] nodes, int gunPosition, Screen screen) {
        ArrayList<Integer> array = new ArrayList<>();
        int goal;
        int up = gunPosition;
        int down = gunPosition;
        int right = gunPosition;
        int left = gunPosition;

        System.out.println();
        System.out.println("Your choice(s) for shooting:");
        if (nodes[gunPosition].up != null) {
            System.out.print("Up:");
            while (nodes[up].up != null) {
                System.out.print(" " + getPositionWhitValue(nodes, nodes[up].up.hashCode()) + " ");
                array.add(nodes[up].up.hashCode());
                up = nodes[up].up.hashCode();
            }
            System.out.println();
        }
        if (nodes[gunPosition].down != null) {
            System.out.print("Down:");
            while (nodes[down].down != null) {
                System.out.print(" " + getPositionWhitValue(nodes, nodes[down].down.hashCode()) + " ");
                array.add(nodes[down].down.hashCode());
                down = nodes[down].down.hashCode();
            }
            System.out.println();
        }
        if (nodes[gunPosition].right != null) {
            System.out.print("Right:");
            while (nodes[right].right != null) {
                System.out.print(" " + getPositionWhitValue(nodes, nodes[right].right.hashCode()) + " ");
                array.add(nodes[right].right.hashCode());
                right = nodes[right].right.hashCode();
            }
            System.out.println();
        }
        if (nodes[gunPosition].left != null) {
            System.out.print("Left:");
            while (nodes[left].left != null) {
                System.out.print(" " + getPositionWhitValue(nodes, nodes[left].left.hashCode()) + " ");
                array.add(nodes[left].left.hashCode());
                left = nodes[left].left.hashCode();
            }
            System.out.println();
        }
        goal = screen.GunButton(array);
        screen.ButtonColor();
        System.out.println();
        return goal;
    }
}

class Pot extends Objects {
    public Pot(GameMap.MyNode[] nodes, int position) {
        super(nodes, position);
    }
}

class Target extends Objects {
    public Target(GameMap.MyNode[] nodes, int position) {
        super(nodes, position);
    }
}

