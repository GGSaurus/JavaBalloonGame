package org.latinschool;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    SpriteBatch batch;
    ShapeRenderer shape;
    ArrayList<Balloon> balloons;
    BitmapFont font;

    @Override
    public void create() {
        font = new BitmapFont(Gdx.files.internal("WUADS_HXd50qla1XGisjIeiq.TTF.fnt"));
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);
        batch = new SpriteBatch();
        setScreen(new StartScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        font.dispose();
    }
}
