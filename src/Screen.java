import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Screen extends JFrame{

    int i, j;
    JButton[][] jLabels;
    LabelValue labelVal;
    JFXPanel p1 = new JFXPanel();
    JPanel p2 = new JPanel(new BorderLayout());
    File file;
    Scanner inputFile;
    ImageIcon house = new ImageIcon("data\\Map\\house.png");
    ImageIcon len = new ImageIcon("data\\Map\\len.png");
    ImageIcon wen = new ImageIcon("data\\Map\\wen.png");
    ImageIcon hitman = new ImageIcon("data\\Person\\Hitman.png");
    ImageIcon hitmanStone = new ImageIcon("data\\Person\\HitStone.png");
    ImageIcon hitmanGun = new ImageIcon("data\\Person\\HitGun.png");
    ImageIcon target = new ImageIcon("data\\Object\\Target.png");
    ImageIcon stone = new ImageIcon("data\\Object\\Stone.png");
    ImageIcon gun = new ImageIcon("data\\Object\\Gun.png");
    ImageIcon pot = new ImageIcon("data\\Object\\Pot.png");
    ImageIcon static_u = new ImageIcon("data\\Person\\Static\\Static_u.png");
    ImageIcon static_d = new ImageIcon("data\\Person\\Static\\Static_d.png");
    ImageIcon static_r = new ImageIcon("data\\Person\\Static\\Static_r.png");
    ImageIcon static_l = new ImageIcon("data\\Person\\Static\\Static_l.png");
    ImageIcon spinic_u = new ImageIcon("data\\Person\\Spinic\\Spinic_u.png");
    ImageIcon spinic_d = new ImageIcon("data\\Person\\Spinic\\Spinic_d.png");
    ImageIcon spinic_r = new ImageIcon("data\\Person\\Spinic\\Spinic_r.png");
    ImageIcon spinic_l = new ImageIcon("data\\Person\\Spinic\\Spinic_l.png");
    ImageIcon dynamic_u = new ImageIcon("data\\Person\\Dynamic\\Dynamic_u.png");
    ImageIcon dynamic_d = new ImageIcon("data\\Person\\Dynamic\\Dynamic_d.png");
    ImageIcon dynamic_r = new ImageIcon("data\\Person\\Dynamic\\Dynamic_r.png");
    ImageIcon dynamic_l = new ImageIcon("data\\Person\\Dynamic\\Dynamic_l.png");
    ImageIcon staticA_u = new ImageIcon("data\\Person\\Static\\StaticA_u.png");
    ImageIcon staticA_d = new ImageIcon("data\\Person\\Static\\StaticA_d.png");
    ImageIcon staticA_r = new ImageIcon("data\\Person\\Static\\StaticA_r.png");
    ImageIcon staticA_l = new ImageIcon("data\\Person\\Static\\StaticA_l.png");
    ImageIcon spinicA_u = new ImageIcon("data\\Person\\Spinic\\SpinicA_u.png");
    ImageIcon spinicA_d = new ImageIcon("data\\Person\\Spinic\\SpinicA_d.png");
    ImageIcon spinicA_r = new ImageIcon("data\\Person\\Spinic\\SpinicA_r.png");
    ImageIcon spinicA_l = new ImageIcon("data\\Person\\Spinic\\SpinicA_l.png");
    ImageIcon dynamicA_u = new ImageIcon("data\\Person\\Dynamic\\DynamicA_u.png");
    ImageIcon dynamicA_d = new ImageIcon("data\\Person\\Dynamic\\DynamicA_d.png");
    ImageIcon dynamicA_r = new ImageIcon("data\\Person\\Dynamic\\DynamicA_r.png");
    ImageIcon dynamicA_l = new ImageIcon("data\\Person\\Dynamic\\DynamicA_l.png");

    ArrayList<Integer> array = new ArrayList<>();
    ArrayList<Integer> array2 = new ArrayList<>();

    public Screen() throws FileNotFoundException {

        file = new File("data\\SaveGameEdit.txt");
        inputFile = new Scanner(file);
        inputFile.nextLine();
        inputFile.nextInt();
        i = inputFile.nextInt();
        j = inputFile.nextInt();
        jLabels = new JButton[i][j];
        labelVal = new LabelValue(i, j);
        p1.setLayout(new GridLayout(i, j));
        for (int k = 0; k < i; k++)
            for (int p = 0; p < j; p++){
                jLabels[k][p] = new JButton();
                jLabels[k][p].setBackground(new Color(250, 250, 250));
                p1.add(jLabels[k][p]);
            }
        int input1 = inputFile.nextInt();
        int input2 = inputFile.nextInt();
        int side = inputFile.nextInt();
        while (input1 != -1)
        {
            if (!array.contains(input1))
                array.add(input1);
            if (!array.contains(input2))
                array.add(input2);
            input1 = inputFile.nextInt();
            if (side == 2){
                jLabels[labelVal.getPosi(input2 - 1)][labelVal.getPosj(input2 - 1)].setIcon(len);
                jLabels[labelVal.getPosi(input2 - 1)][labelVal.getPosj(input2 - 1)].setEnabled(false);
                array2.add(input2 - 1);}
            else
            if (side == 1){
                jLabels[labelVal.getPosi(input2 - j)][labelVal.getPosj(input2 - j)].setIcon(wen);
                jLabels[labelVal.getPosi(input2 - j)][labelVal.getPosj(input2 - j)].setEnabled(false);
                array2.add(input2 - j);}
            if (input1 == -1)
                break;
            input2 = inputFile.nextInt();
            side = inputFile.nextInt();
        }

        array2.addAll(array);
        Collections.sort(array);
        labelVal.VarArrayIni(array);

        for (int p = 0; p < (i * j); p++)
            if (!array2.contains(p))
                jLabels[labelVal.getPosi(p)][labelVal.getPosj(p)].setVisible(false);

        for (Integer anArray : array) jLabels[labelVal.getPosi(anArray)][labelVal.getPosj(anArray)].setIcon(house);

        p2.add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.CENTER);
    }

    public void UpdateScreen(ArrayList<Object> arr, ArrayList<Integer> pos, GameMap.MyNode node[], boolean hide, int savePos, int hitPos){
        ArrayList<Integer> stoneArr = new ArrayList<>();
        ArrayList<Integer> gunArr = new ArrayList<>();
        int j, side;
        for (Object anArr : arr) {
            if (anArr instanceof Hitman) {
                if (hide) {
                    j = savePos;
                    jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(hitman);
                }
                else {
                    j = ((Hitman) anArr).getPositionInt(node);
                    jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(hitman);
                }
            }
            else
            if (anArr instanceof Target) {
                j = ((Target) anArr).getPositionInt(node);
                jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(target);
            }
            else
            if (anArr instanceof Stone) {
                j = ((Stone) anArr).getPositionInt(node);
                stoneArr.add(j);
                jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(stone);
            }
            else
            if (anArr instanceof Gun) {
                j = ((Gun) anArr).getPositionInt(node);
                gunArr.add(j);
                jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(gun);
            }
            else
            if (anArr instanceof Pot) {
                j = ((Pot) anArr).getPositionInt(node);
                jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(pot);
            }
            else
            if (anArr instanceof StaticEnemy) {
                j = ((StaticEnemy) anArr).getPositionInt(node);
                side = ((StaticEnemy) anArr).getSideInt();
                switch (side) {
                    case 1:
                        if (((StaticEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(staticA_u);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(static_u);
                        break;
                    case -1:
                        if (((StaticEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(staticA_d);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(static_d);
                        break;
                    case 2:{
                        if (((StaticEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(staticA_r);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(static_r);
                        break;}
                    case -2:
                        if (((StaticEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(staticA_l);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(static_l);
                        break;
                }
            }
            else
            if (anArr instanceof SpinicEnemy) {
                j = ((SpinicEnemy) anArr).getPositionInt(node);
                side = ((SpinicEnemy) anArr).getSideInt();
                switch (side) {
                    case 1:
                        if (((SpinicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinicA_u);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinic_u);
                        break;
                    case -1:
                        if (((SpinicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinicA_d);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinic_d);
                        break;
                    case 2:
                        if (((SpinicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinicA_r);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinic_r);
                        break;
                    case -2:
                        if (((SpinicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinicA_l);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(spinic_l);
                        break;
                }
            }
            else
            if (anArr instanceof DynamicEnemy) {
                j = ((DynamicEnemy) anArr).getPositionInt(node);
                side = ((DynamicEnemy) anArr).getSideInt();
                switch (side) {
                    case 1:
                        if (((DynamicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamicA_u);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamic_u);
                        break;
                    case -1:
                        if (((DynamicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamicA_d);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamic_d);
                        break;
                    case 2:
                        if (((DynamicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamicA_r);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamic_r);
                        break;
                    case -2:
                        if (((DynamicEnemy) anArr).attention == 1)
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamicA_l);
                        else
                            jLabels[labelVal.getPosi(labelVal.getVarValue(j))][labelVal.getPosj(labelVal.getVarValue(j))].setIcon(dynamic_l);
                        break;
                }
            }
        }

        array.stream().filter(anArray -> !pos.contains(labelVal.getVarArrayValue(anArray))).forEach(anArray -> jLabels[labelVal.getPosi(anArray)][labelVal.getPosj(anArray)].setIcon(house));
        stoneArr.stream().filter(aStoneArr -> aStoneArr == hitPos).forEach(aStoneArr -> jLabels[labelVal.getPosi(labelVal.getVarValue(aStoneArr))][labelVal.getPosj(labelVal.getVarValue(aStoneArr))].setIcon(hitmanStone));
        gunArr.stream().filter(aGunArr -> aGunArr == hitPos).forEach(aGunArr -> jLabels[labelVal.getPosi(labelVal.getVarValue(aGunArr))][labelVal.getPosj(labelVal.getVarValue(aGunArr))].setIcon(hitmanGun));
    }

    public String ButtonClick(ArrayList<Integer> array, int pos)
    {
        while (true){
            if (array.contains(1)){
                jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) - (2 * j))].setBackground(new Color(206, 255, 157));
                if (jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) - (2 * j))].getModel().isPressed()){
                    jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) - (2 * j))].getModel().setPressed(false);
                    return "up";}}
            if (array.contains(-1)){
                jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) + (2 * j))].setBackground(new Color(206, 255, 157));
                if (jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) + (2 * j))].getModel().isPressed()){
                    jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + (2 * j))][labelVal.getPosj(labelVal.getVarValue(pos) + (2 * j))].getModel().setPressed(false);
                    return "down";}}
            if (array.contains(2)){
                jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + 2)][labelVal.getPosj(labelVal.getVarValue(pos) + 2)].setBackground(new Color(206, 255, 157));
                if (jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + 2)][labelVal.getPosj(labelVal.getVarValue(pos) + 2)].getModel().isPressed()){
                    jLabels[labelVal.getPosi(labelVal.getVarValue(pos) + 2)][labelVal.getPosj(labelVal.getVarValue(pos) + 2)].getModel().setPressed(false);
                    return "right";}}
            if (array.contains(-2)){
                jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - 2)][labelVal.getPosj(labelVal.getVarValue(pos) - 2)].setBackground(new Color(206, 255, 157));
                if (jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - 2)][labelVal.getPosj(labelVal.getVarValue(pos) - 2)].getModel().isPressed()) {
                    jLabels[labelVal.getPosi(labelVal.getVarValue(pos) - 2)][labelVal.getPosj(labelVal.getVarValue(pos) - 2)].getModel().setPressed(false);
                    return "left";}
                }
        }
    }

    public int GunButton(ArrayList<Integer> array)
    {
        while (true){
            for (Integer anArray : array) {
                jLabels[labelVal.getPosi(labelVal.getVarValue(anArray))][labelVal.getPosj(labelVal.getVarValue(anArray))].setBackground(new Color(206, 255, 157));
                if (jLabels[labelVal.getPosi(labelVal.getVarValue(anArray))][labelVal.getPosj(labelVal.getVarValue(anArray))].getModel().isPressed()) {
                    jLabels[labelVal.getPosi(labelVal.getVarValue(anArray))][labelVal.getPosj(labelVal.getVarValue(anArray))].getModel().setPressed(false);
                    return anArray;
                }
            }
        }
    }

    public void ButtonColor()
    {
        for (int k = 0; k < i; k++)
            for (int p = 0; p < j; p++) {
                jLabels[k][p].setBackground(new Color(250, 250, 250));
            }
    }
}
