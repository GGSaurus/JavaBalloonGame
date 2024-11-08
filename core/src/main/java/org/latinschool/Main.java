package org.latinschool;

import com.badlogic.gdx.ApplicationAdapter;
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
public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    ShapeRenderer shape;
    Player player;
    ArrayList<Balloon> balloons;
    ArrayList<Balloon> goalBalloons;
    BitmapFont font;
    String s;

    @Override
    public void create() {
        s = "";
        font = new BitmapFont(Gdx.files.internal("ihnDehndwIxFP3fS_c1mSbr4.ttf.fnt"));
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player(130, 150, 20);
        balloons = new ArrayList<Balloon>();
        goalBalloons = new ArrayList<Balloon>();
        for (int i = 0; i < 6; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
        for (int i = 0; i < 3; i++) {
            goalBalloons.add(new Balloon(400+20*i,300+30*i*(2-(i%3)),15,"" +(i+1)));
        }
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f,1f);
    }

    @Override
    public void render() {
        input();
        Gdx.gl.glClear(16384);
        Gdx.gl20.glLineWidth(10);
        shape.begin(ShapeRenderer.ShapeType.Line);

        shape.setColor(0,0,0,1);

        player.draw(shape);
        shape.end();
        shape.begin(Filled);
        for (Balloon balloon : balloons) {
            balloon.draw(shape);
        }
        for (Balloon balloon : goalBalloons) {
            balloon.draw(shape);
        }
        shape.end();
        batch.begin();
        font.getData().setScale(5);
        font.setColor(Color.BLACK);
        font.draw(batch,s,250,250);
        font.getData().setScale(0.8f);
        for (Balloon balloon : balloons) {
            font.draw(batch,balloon.num,balloon.x- 2*(float) balloon.size /5,balloon.y+ (float) balloon.size /2);
        }
        for (Balloon balloon : goalBalloons) {
            font.draw(batch,balloon.num,balloon.x-2*(float) balloon.size /5, balloon.y + (float) balloon.size /2);
        }
        batch.end();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            if (balloons.get(0).color == goalBalloons.get(0).color) {
                goalBalloons.get(0).color = Color.MAGENTA;
            }
            else {
                goalBalloons.get(0).color = Color.BLACK;
            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            if (balloons.get(1).color == goalBalloons.get(1).color) {
                goalBalloons.get(1).color = Color.MAGENTA;
            }
            else {
                goalBalloons.get(1).color = Color.BLACK;
            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            if (balloons.get(2).color == goalBalloons.get(2).color) {
                goalBalloons.get(2).color = Color.MAGENTA;
            }
            else {
                goalBalloons.get(2).color = Color.BLACK;
            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for (Balloon balloon : goalBalloons) {
                switch(random.nextInt(3)) {
                    case 0:
                        balloon.color = Color.RED;
                        break;
                    case 1:
                        balloon.color = Color.GREEN;
                        break;
                    case 2:
                        balloon.color = Color.BLUE;
                        break;
                }
            }
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            boolean yes = true;
            for (int i = 0; i < goalBalloons.size(); i++) {
                if (!(balloons.get(i).color == goalBalloons.get(i).color)) {
                    yes = false;
                }
            }
            if (yes) {
                for (Balloon balloon : goalBalloons) {
                    s = "yes";
                }
            }
            else {
                s = "no";
            }
        }
    }

    @Override
    public void dispose() {

    }
}
