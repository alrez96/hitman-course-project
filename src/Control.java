import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Control extends GameMap {

    File file;
    File file2;
    Scanner inputFile;
    Scanner inputFile2;

    int savePos;
    int[][] mapArray;
    int[][]mapArray2;
    int hideHouse;
    int goalHouse;
    boolean hide = false;

    ArrayList<StaticEnemy> staticList = new ArrayList<>();
    ArrayList<SpinicEnemy> spinicList = new ArrayList<>();
    ArrayList<DynamicEnemy> dynamicList = new ArrayList<>();
    ArrayList<Stone> stoneList = new ArrayList<>();
    ArrayList<Gun> gunList = new ArrayList<>();
    ArrayList<Pot> potList = new ArrayList<>();
    ArrayList<Integer> arrayList = new ArrayList<>();

    GameMap.MyNode[] nodes;
    GameMap.MyNode[] nodes2;

    Hitman hitman;
    Target target;
    Screen screen;
    GameSound gameSound;

    public Control() throws FileNotFoundException {
        file = new File("data\\SaveGamePlay.txt");
        file2 = new File(("data\\SaveGameEdit.txt"));
        inputFile = new Scanner(file);
        inputFile2 = new Scanner(file2);

        int i_len, j_len;
        inputFile2.nextLine();
        inputFile2.nextInt();
        i_len = inputFile2.nextInt();
        int saveJ = inputFile2.nextInt();
        j_len = saveJ;

        int read = inputFile2.nextInt();
        int cont = 1;
        while (read != -1)
        {
            if (!arrayList.contains(read) && (cont % 3) != 0)
                arrayList.add(read);
            read = inputFile2.nextInt();
            cont++;
        }

        Collections.sort(arrayList);

        mapArray2 = new int[arrayList.size()][1];
        for (int i = 0; i < arrayList.size(); i++)
            mapArray2[i][0] = arrayList.get(i);

        inputFile.nextLine();
        int nodeCont = inputFile.nextInt();
        hideHouse = nodeCont;
        int in = inputFile.nextInt();
        mapArray = new int[in][3];
        int j = inputFile.nextInt();
        for (int i = 0; j != -1; i++)
        {
            mapArray[i][0] = j;
            mapArray[i][1] = inputFile.nextInt();
            mapArray[i][2] = inputFile.nextInt();
            j = inputFile.nextInt();
        }

        nodes = new GameMap.MyNode[nodeCont + 1];
        nodes2 = new GameMap.MyNode[nodeCont];

        for (int i = 0; i < nodeCont + 1; i++)
            nodes[i] = new GameMap.MyNode();
        for (int i = 0; i < nodeCont; i++)
            nodes2[i] = new GameMap.MyNode(0);

        addConnectionsToMap(nodes, mapArray);
        addConnectionsToMap2(nodes2, mapArray2, saveJ);

        inputFile.nextLine();
        inputFile.nextLine();
        inputFile.nextLine();
        j = inputFile.nextInt();
        while (j != -1) {
            if (j == 0) {
                hitman = new Hitman(nodes, inputFile.nextInt());
                inputFile.nextInt();
            }
            else
            if (j == 1) {
                StaticEnemy enemy = new StaticEnemy(nodes, inputFile.nextInt(), inputFile.nextInt());
                staticList.add(enemy);
            }
            else
            if (j == 2) {
                SpinicEnemy enemy = new SpinicEnemy(nodes, inputFile.nextInt(), inputFile.nextInt());
                spinicList.add(enemy);
            }
            else
            if (j == 3){
                DynamicEnemy enemy = new DynamicEnemy(nodes, inputFile.nextInt(), inputFile.nextInt());
                dynamicList.add(enemy);
            }
            j = inputFile.nextInt();
        }
        inputFile.nextLine();
        inputFile.nextLine();
        inputFile.nextLine();
        j = inputFile.nextInt();
        while (j != -1)
        {
            if (j == 6){
                Stone obj = new Stone(nodes, inputFile.nextInt());
                stoneList.add(obj);
            }
            else
            if (j == 7){
                Pot obj = new Pot(nodes, inputFile.nextInt());
                potList.add(obj);
            }
            else
            if (j == 9){
                Gun obj = new Gun(nodes, inputFile.nextInt());
                gunList.add(obj);
            }
            else
            if (j == 10){
                 target = new Target(nodes, inputFile.nextInt());
            }
            j = inputFile.nextInt();
        }

        screen = new Screen();
        screen.setTitle("Hitman Go");
        screen.setSize((j_len * 100) + 48, (i_len * 100) + 65);
        screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setVisible(true);

        gameSound = new GameSound();
    }

    public void RunHitman() {
        if (hide) {
            hitman.setPosition(savePos);
            hitman.Move(nodes, hitman.getPositionInt(nodes), screen);
            hide = false;
        }
        else
           hitman.Move(nodes, hitman.getPositionInt(nodes), screen);
    }

    public void HitmanKillEnemy() {
        for (int i = 0; i < staticList.size(); i++)
        {
            staticList.get(i).dead = hitman.KillEnemy(nodes, hitman.getPositionInt(nodes), staticList.get(i).getPositionInt(nodes), "Static");
            if (staticList.get(i).dead == 1){
                gameSound.KillEnemy();
                staticList.remove(i);
                i--;}
        }

        for (int i = 0; i < spinicList.size(); i++)
        {
            spinicList.get(i).dead = hitman.KillEnemy(nodes, hitman.getPositionInt(nodes), spinicList.get(i).getPositionInt(nodes), "Spinic");
            if (spinicList.get(i).dead == 1){
                gameSound.KillEnemy();
                spinicList.remove(i);
                i--;}
        }

        for (int i = 0; i < dynamicList.size(); i++)
        {
            dynamicList.get(i).dead = hitman.KillEnemy(nodes, hitman.getPositionInt(nodes), dynamicList.get(i).getPositionInt(nodes), "Dynamic");
            if (dynamicList.get(i).dead == 1){
                gameSound.KillEnemy();
                dynamicList.remove(i);
                i--;}
        }
    }

    public boolean MoveOnStone() {
        for (int i = 0; i < stoneList.size(); i++)
        {
            if (hitman.getPositionInt(nodes) == stoneList.get(i).getPositionInt(nodes))
            {
                gameSound.PickSound();
                int stoneNewPos = stoneList.get(i).ThrowingStone(nodes2, stoneList.get(i).getPositionInt(nodes), screen);
                goalHouse = stoneNewPos;
                stoneList.get(i).setPosition(stoneNewPos);
                for (StaticEnemy aStaticList : staticList) {
                    aStaticList.attention = stoneList.get(i).StoneSides(nodes2, stoneNewPos, aStaticList.getPositionInt(nodes));
                    if (aStaticList.attention == 1) {
                        System.out.println("Static Enemy Drew Attention!");
                        aStaticList.MoveGoalEnemyNext(nodes, aStaticList.getPositionInt(nodes), stoneNewPos);
                    }
                }
                for (SpinicEnemy aSpinicList : spinicList) {
                    aSpinicList.attention = stoneList.get(i).StoneSides(nodes2, stoneNewPos, aSpinicList.getPositionInt(nodes));
                    if (aSpinicList.attention == 1) {
                        System.out.println("Spinic Enemy Drew Attention!");
                        aSpinicList.MoveGoalEnemyNext(nodes, aSpinicList.getPositionInt(nodes), stoneNewPos);
                    }
                }
                for (DynamicEnemy aDynamicList : dynamicList) {
                    aDynamicList.attention = stoneList.get(i).StoneSides(nodes2, stoneNewPos, aDynamicList.getPositionInt(nodes));
                    if (aDynamicList.attention == 1) {
                        System.out.println("Dynamic Enemy Drew Attention!");
                        aDynamicList.MoveGoalEnemyNext(nodes, aDynamicList.getPositionInt(nodes), stoneNewPos);
                    }
                }
                stoneList.remove(i);
                gameSound.StoneDrap();
                return true;
            }
        }
        return false;
    }

    public void MoveOnGun() {
        for (int i = 0; i < gunList.size(); i++) {
            if (hitman.getPositionInt(nodes) == gunList.get(i).getPositionInt(nodes)) {
                gameSound.PickSound();
                int goal = gunList.get(i).GunVolley(nodes, gunList.get(i).getPositionInt(nodes), screen);
                for (int j = 0; j < staticList.size(); j++)
                    if (nodes[goal].hashCode() == staticList.get(j).getPositionInt(nodes)) {
                        System.out.println("Static Enemy: Killed By Hitman!");
                        gameSound.KillEnemy();
                        staticList.remove(j);
                        j--;
                    }
                for (int j = 0; j < spinicList.size(); j++)
                    if (nodes[goal].hashCode() == spinicList.get(j).getPositionInt(nodes)) {
                        System.out.println("Spinic Enemy: Killed By Hitman!");
                        gameSound.KillEnemy();
                        spinicList.remove(j);
                        j--;
                    }
                for (int j = 0; j < dynamicList.size(); j++)
                    if (nodes[goal].hashCode() == dynamicList.get(j).getPositionInt(nodes)) {
                        System.out.println("Dynamic Enemy: Killed By Hitman!");
                        gameSound.KillEnemy();
                        dynamicList.remove(j);
                        j--;
                    }
                gunList.remove(i);
                gameSound.GunShot();
            }
        }
    }

    public void MoveOnPot() {
        potList.stream().filter(aPotList -> hitman.getPositionInt(nodes) == aPotList.getPositionInt(nodes)).forEach(aPotList -> {
            gameSound.HidePot();
            savePos = hitman.getPositionInt(nodes);
            hitman.setPosition(hideHouse);
            hide = true;
        });
    }

    public boolean KillHitman(){
        for (StaticEnemy aStaticList : staticList) {
            hitman.dead = aStaticList.KillHitman(nodes, aStaticList.getPositionInt(nodes), aStaticList.getSideInt(), hitman.getPositionInt(nodes), "Static");
            if (hitman.dead == 1) {
                gameSound.HitmanDeath();
                return true;
            }
        }
        for (SpinicEnemy aSpinicList : spinicList) {
            hitman.dead = aSpinicList.KillHitman(nodes, aSpinicList.getPositionInt(nodes), aSpinicList.getSideInt(), hitman.getPositionInt(nodes), "Spinic");
            if (hitman.dead == 1) {
                gameSound.HitmanDeath();
                return true;
            }
        }
        for (DynamicEnemy aDynamicList : dynamicList) {
            hitman.dead = aDynamicList.KillHitman(nodes, aDynamicList.getPositionInt(nodes), aDynamicList.getSideInt(), hitman.getPositionInt(nodes), "Dynamic");
            if (hitman.dead == 1) {
                gameSound.HitmanDeath();
                return true;
            }
        }
        return false;
    }

    public void EnemyMove() {
        staticList.stream().filter(aStaticList -> aStaticList.attention == 1).forEach(aStaticList -> {
            aStaticList.MoveGoalEnemy(nodes, aStaticList.getPositionInt(nodes), goalHouse, "Static");
            if (aStaticList.getPositionInt(nodes) == goalHouse)
                aStaticList.attention = 0;
            else
                aStaticList.MoveGoalEnemyNext(nodes, aStaticList.getPositionInt(nodes), goalHouse);
        });
        for (SpinicEnemy aSpinicList : spinicList) {
            if (aSpinicList.attention == 1) {
                aSpinicList.MoveGoalEnemy(nodes, aSpinicList.getPositionInt(nodes), goalHouse, "Spinic");
                if (aSpinicList.getPositionInt(nodes) == goalHouse)
                    aSpinicList.attention = 0;
                else
                    aSpinicList.MoveGoalEnemyNext(nodes, aSpinicList.getPositionInt(nodes), goalHouse);
            } else
                aSpinicList.Move(aSpinicList.getSideInt());
        }
        for (DynamicEnemy aDynamicList : dynamicList) {
            if (aDynamicList.attention == 1) {
                aDynamicList.MoveGoalEnemy(nodes, aDynamicList.getPositionInt(nodes), goalHouse, "Dynamic");
                if (aDynamicList.getPositionInt(nodes) == goalHouse)
                    aDynamicList.attention = 0;
                else
                    aDynamicList.MoveGoalEnemyNext(nodes, aDynamicList.getPositionInt(nodes), goalHouse);
            } else
                aDynamicList.Move(nodes, aDynamicList.getPositionInt(nodes), aDynamicList.getSideInt());
            if (nodes[aDynamicList.getPositionInt(nodes)].up == null && aDynamicList.getSideInt() == 1)
                aDynamicList.setSide((-aDynamicList.getSideInt()));
            if (nodes[aDynamicList.getPositionInt(nodes)].down == null && aDynamicList.getSideInt() == -1)
                aDynamicList.setSide((-aDynamicList.getSideInt()));
            if (nodes[aDynamicList.getPositionInt(nodes)].right == null && aDynamicList.getSideInt() == 2)
                aDynamicList.setSide((-aDynamicList.getSideInt()));
            if (nodes[aDynamicList.getPositionInt(nodes)].left == null && aDynamicList.getSideInt() == -2)
                aDynamicList.setSide((-aDynamicList.getSideInt()));
        }
        gameSound.Walking();
    }

    public boolean FinishGame() {
        if (hitman.getPositionInt(nodes) == target.getPositionInt(nodes)) {
            gameSound.EndGame();
            return true;
        }
        else
            return false;

    }

    public void ScreenUpdate() {
        ArrayList<Object> arrPerson = new ArrayList<>();
        ArrayList<Integer> arrPersonPos = new ArrayList<>();
        arrPerson.add(target);
        arrPerson.addAll(stoneList);
        arrPerson.addAll(gunList);
        arrPerson.add(hitman);
        arrPerson.addAll(potList);
        arrPerson.addAll(staticList);
        arrPerson.addAll(spinicList);
        arrPerson.addAll(dynamicList);
        arrPersonPos.addAll(staticList.stream().map(aStaticList -> aStaticList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.addAll(spinicList.stream().map(aSpinicList -> aSpinicList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.addAll(dynamicList.stream().map(aDynamicList -> aDynamicList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.addAll(stoneList.stream().map(aStoneList -> aStoneList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.addAll(gunList.stream().map(aGunList -> aGunList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.addAll(potList.stream().map(aPotList -> aPotList.getPositionInt(nodes)).collect(Collectors.toList()));
        arrPersonPos.add(hitman.getPositionInt(nodes));
        arrPersonPos.add(target.getPositionInt(nodes));
        screen.UpdateScreen(arrPerson, arrPersonPos, nodes, hide, savePos, hitman.getPositionInt(nodes));
    }

    public void ShowMessage(int i){
        if (i == 1)
            JOptionPane.showMessageDialog(null, "You Win !!!");
        else
        if (i == -1)
            JOptionPane.showMessageDialog(null, "Hitman Died !!!");
    }
}

