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
    String num;
    int length;
    ArrayList<Customer> customers;

    @Override
    public void create() {
        length = 3;
        s = "";
        num = "Count: ";
        font = new BitmapFont(Gdx.files.internal("WUADS_HXd50qla1XGisjIeiq.TTF.fnt"));
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player(130, 150, 20);
        balloons = new ArrayList<>();
        customers = new ArrayList<>();
        for (int i = 0; i < length * 2; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
        for (int i = 0; i < 2; i++) {
            customers.add(new Customer(350+100*i,150,20));
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
        for (Customer customer : customers) {
            customer.draw(shape);
        }
        shape.end();
        shape.begin(Filled);
        for (Balloon balloon : balloons) {
            balloon.draw(shape);
        }
        for (Customer customer : customers) {
            for (Balloon balloon : customer.goal) {
                balloon.draw(shape);
            }
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
        font.getData().setScale(.5f);
        font.draw(batch,"",20,200);
        font.getData().setScale(0.2f);
        for (Balloon balloon : balloons) {
            font.draw(batch,balloon.num,balloon.x-2*(float) balloon.size / 5,balloon.y + (float) balloon.size / 2);
        }
        for (Customer customer : customers) {
            for (Balloon balloon : customer.goal) {
                font.draw(batch,balloon.num,balloon.x-2*(float)balloon.size/5,balloon.y+(float)balloon.size/2);
            }
        }
        batch.end();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Color temp = balloons.get(0).color;
            balloons.get(0).color = balloons.get(customers.get(0).goal.size()).color;
            balloons.get(customers.get(0).goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Color temp = balloons.get(1).color;
            balloons.get(1).color = balloons.get(1+customers.get(0).goal.size()).color;
            balloons.get(1+customers.get(0).goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Color temp = balloons.get(2).color;
            balloons.get(2).color = balloons.get(2+customers.get(0).goal.size()).color;
            balloons.get(2+customers.get(0).goal.size()).color = temp;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for (int i = 0; i < customers.get(0).goal.size(); i++) {
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
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (x < 100 && x > 20 && (Gdx.graphics.getHeight() - y) > 30 && (Gdx.graphics.getHeight() - y) < 80) {
                boolean yes = true;
                for (int i = 0; i < customers.get(0).goal.size(); i++) {
                    if (!(balloons.get(i).color == customers.get(0).goal.get(i).color)) {
                        yes = false;
                    }
                }
                if (yes) {
                    match();
                }
                else {
                    s = "No";
                }
            }
            else if(x < 240 && x > 160 && (Gdx.graphics.getHeight() - y) > 30 && (Gdx.graphics.getHeight() - y) < 80) {
                boolean yes = true;
                for (int i = 0; i < customers.get(0).goal.size(); i++) {
                    if (!(balloons.get(i+customers.get(0).goal.size()).color == customers.get(0).goal.get(i).color)) {
                        yes = false;
                    }
                }
                if (yes) {
                    match();
                }
                else {
                    s = "No";
                }
            }
            else if(x < 80 && x > 40 && (Gdx.graphics.getHeight() - y) > 100 && (Gdx.graphics.getHeight() - y) < 120) {
                Color temp = balloons.get(0).color;
                balloons.get(0).color = balloons.get(2).color;
                balloons.get(2).color = balloons.get(1).color;
                balloons.get(1).color = temp;
            }
            else if(x < 220 && x > 180 && (Gdx.graphics.getHeight() - y) > 100 && (Gdx.graphics.getHeight() - y) < 120) {
                Color temp = balloons.get(customers.get(0).goal.size()).color;
                balloons.get(customers.get(0).goal.size()).color = balloons.get(2+customers.get(0).goal.size()).color;
                balloons.get(2+customers.get(0).goal.size()).color = balloons.get(1+customers.get(0).goal.size()).color;
                balloons.get(1+customers.get(0).goal.size()).color = temp;
            }
        }
    }

    private void match() {
        customers.remove(0);
        for (Customer customer : customers) {
            customer.x -= 100;
            for (Balloon balloon : customer.goal) {
                balloon.x -= 100;
            }
        }
        customers.add(new Customer(customers.get(customers.size()-1).x+100,customers.get(customers.size()-1).y,customers.get(customers.size()-1).size));
    }

    @Override
    public void dispose() {

    }
}
