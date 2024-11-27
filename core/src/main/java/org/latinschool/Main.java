package org.latinschool;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    SpriteBatch batch;
    ShapeRenderer shape;
    BitmapFont font;
    String easyHiScore;
    String normalHiScore;
    String hardHiScore;
    Music music;

    @Override
    public void create() {
        font = new BitmapFont(Gdx.files.internal("WUADS_HXd50qla1XGisjIeiq.TTF.fnt"));
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);
        batch = new SpriteBatch();
        easyHiScore = "None";
        normalHiScore = "None";
        hardHiScore = "None";
        music = Gdx.audio.newMusic(Gdx.files.internal("BalloonGame.wav"));
        setScreen(new StartScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        font.dispose();
    }
}
