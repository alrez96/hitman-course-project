import java.io.FileNotFoundException;

public class TestGame {
    public static void main(String[] args) throws FileNotFoundException {
        Control test = new Control();
        boolean endGame;
        boolean stoneDrag;
        boolean finish;

        while (true) {
            test.ScreenUpdate();
            test.RunHitman();
            finish = test.FinishGame();
            if (finish){
                test.ScreenUpdate();
                System.out.println("You Win !!!");
                test.ShowMessage(1);
                System.exit(0);
            }
            test.HitmanKillEnemy();
            test.MoveOnPot();
            endGame = test.KillHitman();
            if (endGame) {
                test.ScreenUpdate();
                test.ShowMessage(-1);
                System.exit(0);
            }
            test.EnemyMove();
            test.ScreenUpdate();
            stoneDrag = test.MoveOnStone();
            if (stoneDrag)
                continue;
            test.MoveOnGun();
            test.ScreenUpdate();
        }
    }
}
