package org.latinschool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class InstructionScreen extends ScreenAdapter {
    Main game;
    Player player;
    Customer customer;
    Customer customer2;
    ArrayList<Color> playerColors;
    ArrayList<Color> customerColors;
    ArrayList<Color> customer2Colors;
    ArrayList<Balloon> balloons;

    public InstructionScreen(Main game){
        this.game = game;
        playerColors = new ArrayList<>();
        customerColors = new ArrayList<>();
        customer2Colors = new ArrayList<>();
        balloons = new ArrayList<>();
        playerColors.add(Color.RED);
        playerColors.add(Color.BLUE);
        playerColors.add(Color.BLUE);
        playerColors.add(Color.GREEN);
        playerColors.add(Color.BLUE);
        playerColors.add(Color.RED);
        customerColors.add(Color.GREEN);
        customerColors.add(Color.BLUE);
        customerColors.add(Color.BLUE);
        customer2Colors.add(Color.BLUE);
        customer2Colors.add(Color.RED);
        customer2Colors.add(Color.RED);

        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f, 1f);
        player = new Player(130, 150, 20);
        customer = new Customer(350, 150, 20, customerColors);
        customer2 = new Customer(450, 150, 20, customer2Colors);
        for (int i = 0; i < 6; i++) {
            balloons.add(new Balloon(25+20*i + 100*(i/3),150+30*(i%3)*(2-(i % 3)),15,"" + (i%3+1),playerColors.get(i)));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(16384);
        game.shape.begin(ShapeRenderer.ShapeType.Line);
        player.draw(game.shape);
        customer.draw(game.shape);
        customer2.draw(game.shape);
        for (Balloon balloon : balloons) {
            balloon.draw(game.shape);
        }
        game.shape.end();
        game.batch.begin();
        game.font.getData().setScale(.6f);
        game.font.draw(game.batch,"Instructions", (float) Gdx.graphics.getWidth() /2-220,Gdx.graphics.getHeight()-30);
        game.font.getData().setScale(.3f);
        game.font.draw(game.batch,"Press Space to continue", (float) Gdx.graphics.getWidth() /2 - 210, (float) Gdx.graphics.getHeight() /2+140);
        game.font.getData().setScale(.2f);
        game.font.draw(game.batch,"Click on the balloons to switch\nthem between your hands",100,300);
        game.batch.end();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new DifficultyScreen(game));
                }
                return true;
            }
        });
    }
}
