package org.latinschool;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;

public class StartScreen extends ScreenAdapter {
    Main game;
    String str;
    String easyHiScore;
    String normalHiScore;
    String hardHiScore;
    public StartScreen(Main game) {
        this.game = game;
        str = "";
        easyHiScore = "None";
        normalHiScore = "None";
        hardHiScore = "None";
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f,1f);
    }
    public StartScreen(Main game, boolean lost, int difficulty) {
        this.game = game;
        if (lost) {
            str = "You Lost!";
        }
        else {
            str = "You Won!!";
        }
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f,1f);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(16384);
        game.batch.begin();
        game.font.getData().setScale(.5f);
        game.font.draw(game.batch,str,(float) Gdx.graphics.getWidth() /2 - 200,(float) Gdx.graphics.getHeight() /2+150);
        game.font.draw(game.batch,"Press Space\n to Start", (float) Gdx.graphics.getWidth() /2 - 160, (float) Gdx.graphics.getHeight() /2+50);
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
