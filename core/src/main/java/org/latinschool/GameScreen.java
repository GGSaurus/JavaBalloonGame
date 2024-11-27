package org.latinschool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.round;

public class GameScreen extends ScreenAdapter {
    Main game;
    String s;
    int num;
    int numWrong;
    int length;
    ArrayList<Customer> customers;
    float autoTime;
    float time;
    float timer;
    Texture error;
    Texture rotate;
    Player player;
    ArrayList<Balloon> balloons;
    int difficulty;
    public GameScreen(Main game) {
        this.game = game;
        game.music.play();
        error = new Texture(Gdx.files.internal("file.png"));
        rotate = new Texture(Gdx.files.internal("rotate.png"));
        autoTime = 8.0f;
        time = 0;
        length = 3;
        s = "";
        num = 0;
        numWrong = 0;
        player = new Player(130, 150, 20);
        balloons = new ArrayList<>();
        customers = new ArrayList<>();
        for (int i = 0; i < length * 2; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
        for (int i = 0; i < 5; i++) {
            customers.add(new Customer(350+100*i,150,20));
        }
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f,1f);
    }
    public GameScreen(Main game, int difficulty) {
        this.game = game;
        game.music.play();
        error = new Texture(Gdx.files.internal("file.png"));
        rotate = new Texture(Gdx.files.internal("rotate.png"));
        this.difficulty = difficulty;
        switch(difficulty) {
            case 1:
                autoTime = 15;
                break;
            case 2:
                autoTime = 10;
                break;
            case 3:
                autoTime = 7.5f;
                break;
        }
        time = 0;
        length = 3;
        s = "";
        num = 0;
        numWrong = 0;
        player = new Player(130, 150, 20);
        balloons = new ArrayList<>();
        customers = new ArrayList<>();
        for (int i = 0; i < length * 2; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1)));
        }
        for (int i = 0; i < 5; i++) {
            customers.add(new Customer(350+100*i,150,20));
        }
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f,1f);
    }
    @Override
    public void render(float delta) {
        input();
        logic();
        time += delta;
        timer += delta;
        Gdx.gl.glClear(16384);
        Gdx.gl20.glLineWidth(10);
        game.shape.begin(ShapeRenderer.ShapeType.Line);

        game.shape.setColor(0,0,0,1);

        player.draw(game.shape);
        for (Customer customer : customers) {
            customer.draw(game.shape);
        }
        game.shape.end();
        game.shape.begin(Filled);
        for (Balloon balloon : balloons) {
            balloon.draw(game.shape);
        }
        game.shape.setColor(.23f,.38f,.85f,1);
        game.shape.rect(20,30,80,50);
        game.shape.rect(160,30,80,50);
        game.shape.setColor(0,.7f,.2f,1);
        game.shape.rect(40,100,40,20);
        game.shape.rect(180,100,40,20);
        game.shape.setColor(.89f,.16f,.06f,1);
        game.shape.rect(20,210,80,50);
        game.shape.rect(160,210,80,50);
        game.shape.end();
        game.batch.begin();
        game.font.getData().setScale(1);
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch,s,200,300);
        game.font.getData().setScale(.2f);
        game.font.draw(game.batch,"Completed: " + num,20,450);
        game.font.draw(game.batch,"Customers Remaining: " + customers.size(), 20,400);
        game.font.draw(game.batch,"Timer: "+Float.toString(timer).split("\\.")[0]+"."+Float.toString(timer).split("\\.")[1].charAt(0),400,400);
        game.font.getData().setScale(0.2f);
        for (Balloon balloon : balloons) {
            game.font.draw(game.batch,balloon.num,balloon.x-2*(float) balloon.size / 5,balloon.y + (float) balloon.size / 2);
        }
        for (Customer customer : customers) {
            for (Balloon balloon : customer.goal) {
                game.font.draw(game.batch,balloon.num,balloon.x-2*(float)balloon.size/5,balloon.y+(float)balloon.size/2);
            }
        }
        for (int i=0;i<numWrong;i++) {
            game.batch.draw(error,400+50*i,400, (float) Gdx.graphics.getWidth()/10, (float) Gdx.graphics.getWidth()*9/10/16);
        }
        game.font.getData().setScale(.3f);
        game.font.draw(game.batch,"Sell",27,65);
        game.font.draw(game.batch,"Sell",167,65);
        game.font.getData().setScale(.2f);
        game.font.draw(game.batch,"Reset",30,242);
        game.font.draw(game.batch,"Reset",170,242);
        game.batch.draw(rotate,50,103,20, (float) 20 /225*164);
        game.batch.draw(rotate,190,103,20, (float) 20 /225*164);
        game.batch.end();
    }
    private void logic() {
        if (time > autoTime) {
            Customer lastCustomer;
            if (!customers.isEmpty()) {
                lastCustomer = customers.get(customers.size()-1);
                customers.add(new Customer(lastCustomer.x+100,lastCustomer.y,lastCustomer.size));
            }
            else {
                customers.add(new Customer(350,150,20));
            }
            time -= autoTime;
        }
        if (numWrong >= 3) {
            game.setScreen(new StartScreen(game,true,difficulty));
        }
        if (customers.isEmpty()) {
            game.setScreen(new StartScreen(game,false,difficulty, (float) round(timer * 10) /10));
        }
    }
    private void input() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            System.out.println(x+" "+(480-y));
            System.out.println(x+" "+Gdx.graphics.getHeight());
            if (x < 100 && x > 20 && (Gdx.graphics.getHeight() - y) > 30 && (Gdx.graphics.getHeight() - y) < 80) {
                boolean yes = true;
                for (int i = 0; i < customers.get(0).goal.size(); i++) {
                    if (!(balloons.get(i).color == customers.get(0).goal.get(i).color)) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    match(0);
                }
                else {
                    numWrong +=1;
                }
            }
            else if(x < 240 && x > 160 && (Gdx.graphics.getHeight() - y) > 30 && (Gdx.graphics.getHeight() - y) < 80) {
                boolean yes = true;
                for (int i = 0; i < customers.get(0).goal.size(); i++) {
                    if (!(balloons.get(i+customers.get(0).goal.size()).color == customers.get(0).goal.get(i).color)) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    match(1);
                }
                else {
                    numWrong +=1;
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
            else if(x<100 && x>20 && (Gdx.graphics.getHeight()-y)>210 && (Gdx.graphics.getHeight()-y)<260) {
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
            else if(x<240 && x>160 && (Gdx.graphics.getHeight()-y)>210 && (Gdx.graphics.getHeight()-y)<260) {
                for (int i = 0; i < customers.get(0).goal.size(); i++) {
                    switch(random.nextInt(3)) {
                        case 0:
                            balloons.get(i+length).color = Color.RED;
                            break;
                        case 1:
                            balloons.get(i+length).color = Color.GREEN;
                            break;
                        case 2:
                            balloons.get(i+length).color = Color.BLUE;
                            break;
                    }
                }
            }
            else if(((Math.pow((x-balloons.get(0).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(0).y,2))<Math.pow(balloons.get(0).size,2))||((Math.pow((x-balloons.get(balloons.size()/2).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(balloons.size()/2).y,2))<Math.pow(balloons.get(balloons.size()/2).size,2))) {
                Color temp = balloons.get(0).color;
                balloons.get(0).color = balloons.get(balloons.size()/2).color;
                balloons.get(balloons.size()/2).color = temp;
            }
            else if(((Math.pow((x-balloons.get(1).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(1).y,2))<Math.pow(balloons.get(1).size,2))||((Math.pow((x-balloons.get(balloons.size()/2+1).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(balloons.size()/2+1).y,2))<Math.pow(balloons.get(balloons.size()/2+1).size,2))) {
                Color temp = balloons.get(1).color;
                balloons.get(1).color = balloons.get(balloons.size()/2+1).color;
                balloons.get(balloons.size()/2+1).color = temp;
            }
            else if(((Math.pow((x-balloons.get(2).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(2).y,2))<Math.pow(balloons.get(2).size,2))||((Math.pow((x-balloons.get(balloons.size()/2+2).x),2)+Math.pow(y-Gdx.graphics.getHeight()+balloons.get(balloons.size()/2+2).y,2))<Math.pow(balloons.get(balloons.size()/2+2).size,2))) {
                Color temp = balloons.get(2).color;
                balloons.get(2).color = balloons.get(balloons.size()/2+2).color;
                balloons.get(balloons.size()/2+2).color = temp;
            }
        }
    }
    private void match(int n) {
        customers.remove(0);
        for (Customer customer : customers) {
            customer.x -= 100;
            for (Balloon balloon : customer.goal) {
                balloon.x -= 100;
            }
        }
        for (int i = 0; i < length; i++) {
            switch(random.nextInt(3)) {
                case 0:
                    balloons.get(i+n*3).color = Color.RED;
                    break;
                case 1:
                    balloons.get(i+n*3).color = Color.GREEN;
                    break;
                case 2:
                    balloons.get(i+n*3).color = Color.BLUE;
                    break;
            }
        }
        num +=1;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
        });
    }
}
