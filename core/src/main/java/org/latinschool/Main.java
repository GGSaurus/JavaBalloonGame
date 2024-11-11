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
    BitmapFont font;
    String s;
    int length;
    Customer customer;
    ArrayList<Customer> customers;

    @Override
    public void create() {
        length = 3;
        s = "";
        font = new BitmapFont(Gdx.files.internal("WUADS_HXd50qla1XGisjIeiq.TTF.fnt"));
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player(130, 150, 20);
        balloons = new ArrayList<>();
        customers = new ArrayList<>();
        customer = new Customer(350,150,20);
        for (int i = 0; i < length * 2; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
       // for (int i = 0; i < 3; i++) {
        //    customers.add(new Customer())
       // }
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
        customer.draw(shape);
        shape.end();
        shape.begin(Filled);
        for (Balloon balloon : balloons) {
            balloon.draw(shape);
        }
        for (Balloon balloon : customer.goal) {
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
        font.getData().setScale(1);
        font.setColor(Color.BLACK);
        font.draw(batch,s,200,300);
        font.getData().setScale(0.2f);
        for (Balloon balloon : balloons) {
            font.draw(batch,balloon.num,balloon.x-2*(float) balloon.size / 5,balloon.y + (float) balloon.size / 2);
        }
        for (Balloon balloon : customer.goal) {
            font.draw(batch,balloon.num,balloon.x-2*(float) balloon.size / 5,balloon.y + (float) balloon.size / 2);
        }
        batch.end();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Color temp = balloons.get(0).color;
            balloons.get(0).color = balloons.get(customer.goal.size()).color;
            balloons.get(customer.goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Color temp = balloons.get(1).color;
            balloons.get(1).color = balloons.get(1+customer.goal.size()).color;
            balloons.get(1+customer.goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Color temp = balloons.get(2).color;
            balloons.get(2).color = balloons.get(2+customer.goal.size()).color;
            balloons.get(2+customer.goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for (int i =0; i < customer.goal.size(); i++) {
                switch(random.nextInt(3)) {
                    case 0:
                        balloons.get(i).color = Color.RED;
                        break;
                    case 1:
                        balloons.get(i).color = Color.GREEN;
                        break;
                    case 2:
                        balloons.get(i).color = Color.BLUE;
                        break;
                }
            }
        }
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() < 100 && Gdx.input.getX() > 20 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 30 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 80) {
                boolean yes = true;
                for (int i = 0; i < customer.goal.size(); i++) {
                    if (!(balloons.get(i).color == customer.goal.get(i).color)) {
                        yes = false;
                    }
                }
                if (yes) {
                    for (Balloon balloon : customer.goal) {
                        s = "Yes";
                    }
                }
                else {
                    s = "No";
                }
            }
            else if(Gdx.input.getX() < 240 && Gdx.input.getX() > 160 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 30 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 80) {
                boolean yes = true;
                for (int i = 0; i < customer.goal.size(); i++) {
                    if (!(balloons.get(i+customer.goal.size()).color == customer.goal.get(i).color)) {
                        yes = false;
                    }
                }
                if (yes) {
                    for (Balloon balloon : customer.goal) {
                        s = "Yes";
                    }
                }
                else {
                    s = "No";
                }
            }
            else if(Gdx.input.getX() < 80 && Gdx.input.getX() > 40 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 100 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 120) {
                Color temp = balloons.get(0).color;
                balloons.get(0).color = balloons.get(2).color;
                balloons.get(2).color = balloons.get(1).color;
                balloons.get(1).color = temp;
            }
            else if(Gdx.input.getX() < 220 && Gdx.input.getX() > 180 && (Gdx.graphics.getHeight() - Gdx.input.getY()) > 100 && (Gdx.graphics.getHeight() - Gdx.input.getY()) < 120) {
                Color temp = balloons.get(customer.goal.size()).color;
                balloons.get(customer.goal.size()).color = balloons.get(2+customer.goal.size()).color;
                balloons.get(2+customer.goal.size()).color = balloons.get(1+customer.goal.size()).color;
                balloons.get(1+customer.goal.size()).color = temp;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
