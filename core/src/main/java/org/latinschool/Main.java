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
    int length;

    @Override
    public void create() {
        length = 3;
        s = "";
        font = new BitmapFont(Gdx.files.internal("ihnDehndwIxFP3fS_c1mSbr4.ttf.fnt"));
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player(130, 150, 20);
        balloons = new ArrayList<Balloon>();
        goalBalloons = new ArrayList<Balloon>();
        for (int i = 0; i < length * 2; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
        for (int i = 0; i < length; i++) {
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
        shape.setColor(.3f,0,0,1);
        shape.rect(20,30,80,50);
        shape.rect(160,30,80,50);
        shape.setColor(0,.3f,.3f,1);
        shape.rect(40,100,40,20);
        shape.rect(180,100,40,20);
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
            Color temp = balloons.get(0).color;
            balloons.get(0).color = balloons.get(goalBalloons.size()).color;
            balloons.get(goalBalloons.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Color temp = balloons.get(1).color;
            balloons.get(1).color = balloons.get(1+goalBalloons.size()).color;
            balloons.get(1+goalBalloons.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Color temp = balloons.get(2).color;
            balloons.get(2).color = balloons.get(2+goalBalloons.size()).color;
            balloons.get(2+goalBalloons.size()).color = temp;
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
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() < 100 && Gdx.input.getX() > 20 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 30 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 80) {
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
            else if(Gdx.input.getX() < 240 && Gdx.input.getX() > 160 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 30 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 80) {
                boolean yes = true;
                for (int i = 0; i < goalBalloons.size(); i++) {
                    if (!(balloons.get(i+goalBalloons.size()).color == goalBalloons.get(i).color)) {
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
            else if(Gdx.input.getX() < 80 && Gdx.input.getX() > 40 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 100 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 120) {
                Color temp = balloons.get(0).color;
                balloons.get(0).color = balloons.get(2).color;
                balloons.get(2).color = balloons.get(1).color;
                balloons.get(1).color = temp;
            }
            else if(Gdx.input.getX() < 180 && Gdx.input.getX() > 140 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 100 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 120) {
                Color temp = balloons.get(goalBalloons.size()).color;
                balloons.get(goalBalloons.size()).color = balloons.get(2+goalBalloons.size()).color;
                balloons.get(2+goalBalloons.size()).color = balloons.get(1+goalBalloons.size()).color;
                balloons.get(1+goalBalloons.size()).color = temp;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
