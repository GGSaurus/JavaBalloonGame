package org.latinschool;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.math.MathUtils.random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    SpriteBatch batch;
    ShapeRenderer shape;
    Player player;
    ArrayList<Balloon> balloons;
    BitmapFont font;
    String s;
    int num;
    int numWrong;
    int length;
    ArrayList<Customer> customers;
    float autoTime;
    float time;
    Texture texture;

    @Override
    public void create() {
        font = new BitmapFont(Gdx.files.internal("WUADS_HXd50qla1XGisjIeiq.TTF.fnt"));
        shape = new ShapeRenderer();
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
