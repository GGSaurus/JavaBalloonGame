package org.latinschool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class InstructionScreen extends ScreenAdapter {
    Main game;
    Customer player;
    Customer customer;
    Customer customer2;
    ArrayList<Color> playerColors;
    ArrayList<Color> customerColors;
    ArrayList<Color> customer2Colors;

    public InstructionScreen(Main game){
        this.game = game;
        playerColors = new ArrayList<>();
        customerColors = new ArrayList<>();
        customer2Colors = new ArrayList<>();
        playerColors.add(Color.RED);
        playerColors.add(Color.BLUE);
        customerColors.add(Color.BLUE);
        playerColors.add(Color.GREEN);
        customerColors.add(Color.GREEN);
        customer2Colors.add(Color.GREEN);
        customerColors.add(Color.RED);
        customer2Colors.add(Color.RED);
        customer2Colors.add(Color.BLUE);

        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f, 1f);
        player = new Customer(130, 150, 20, playerColors);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(16384);
        game.shape.begin(ShapeRenderer.ShapeType.Line);
        player.draw(game.shape);
        game.shape.end();
        game.batch.begin();
        game.font.getData().setScale(.6f);
        game.font.draw(game.batch,"Instructions", (float) Gdx.graphics.getWidth() /2-220,Gdx.graphics.getHeight()-50);
        //for ()
        game.batch.end();
    }
}
