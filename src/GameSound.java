import javafx.scene.media.*;

import java.nio.file.Paths;

public class GameSound {

    Media music;
    Media pick = new Media(Paths.get("data\\Sound\\pick.mp3").toUri().toString());
    Media gunShot = new Media(Paths.get("data\\Sound\\gun.mp3").toUri().toString());
    Media stoneDrap = new Media(Paths.get("data\\Sound\\stone.mp3").toUri().toString());
    Media walk = new Media(Paths.get("data\\Sound\\walk.mp3").toUri().toString());
    Media pot = new Media(Paths.get("data\\Sound\\pot.mp3").toUri().toString());
    Media killEnemy = new Media(Paths.get("data\\Sound\\killE.mp3").toUri().toString());
    Media hitdeath = new Media(Paths.get("data\\Sound\\deathHitman.mp3").toUri().toString());
    Media end = new Media(Paths.get("data\\Sound\\endGame.mp3").toUri().toString());
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    MediaPlayer mediaPlayer4;
    MediaPlayer mediaPlayer5;
    MediaPlayer mediaPlayer6;
    MediaPlayer mediaPlayer7;
    MediaPlayer mediaPlayer8;
    MediaPlayer mediaPlayer9;

    public GameSound()
    {
        music = new Media(Paths.get("data\\Sound\\music.mp3").toUri().toString());
        mediaPlayer1 = new MediaPlayer(music);
        mediaPlayer1.play();
    }

    public void PickSound()
    {
        mediaPlayer2 = new MediaPlayer(pick);
        mediaPlayer2.play();
    }

    public void GunShot()
    {
        mediaPlayer3 = new MediaPlayer(gunShot);
        mediaPlayer3.play();
    }

    public void StoneDrap()
    {
        mediaPlayer4 = new MediaPlayer(stoneDrap);
        mediaPlayer4.play();
    }

    public void Walking()
    {
        mediaPlayer5 = new MediaPlayer(walk);
        mediaPlayer5.play();
    }

    public void HidePot()
    {
        mediaPlayer6 = new MediaPlayer(pot);
        mediaPlayer6.play();
    }

    public void KillEnemy()
    {
        mediaPlayer7 = new MediaPlayer(killEnemy);
        mediaPlayer7.play();
    }

    public void HitmanDeath()
    {
        mediaPlayer8 = new MediaPlayer(hitdeath);
        mediaPlayer8.play();
    }

    public void EndGame()
    {
        mediaPlayer9 = new MediaPlayer(end);
        mediaPlayer9.play();
    }
}
