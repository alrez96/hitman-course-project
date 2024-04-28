import java.util.ArrayList;

public class LabelValue
{
    int[][] btn;
    int[][] val;
    int i1, j1;

    public LabelValue(int i, int j)
    {
        btn = new int[i][j];
        i1 = i;
        j1 = j;
        int num = 0;
        for (int k = 0; k < i; k++)
            for (int p = 0; p < j; p++)
                btn[k][p] = num++;
    }

    public void VarArrayIni(ArrayList<Integer> arr){
        val = new int[arr.size()][1];
        for (int i = 0; i < val.length; i++)
            val[i][0] = arr.get(i);
    }

    public int getVarArrayValue(int value)
    {
        for (int i = 0; i < val.length; i++)
            if (val[i][0] == value)
                return i;
        return 0;
    }

    public int getVarValue(int value)
    {
        return val[value][0];
    }

    /*public int getValue(int i, int j)
    {
        return btn[i][j];
    }*/

    public int getPosi(int value)
    {
        for (int i = 0; i < i1; i++)
            for (int j = 0; j < j1; j++)
            {
                if (btn[i][j] == value)
                    return i;
            }
        return -1;
    }

    public int getPosj(int value)
    {
        for (int i = 0; i < i1; i++)
            for (int j = 0; j < j1; j++)
            {
                if (btn[i][j] == value)
                    return j;
            }
        return -1;
    }
}
