package org.latinschool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DifficultyScreen extends ScreenAdapter {
    Main game;

    public DifficultyScreen(Main game) {
        this.game = game;
        Gdx.gl.glClearColor(0.8f, 0.792f, 0.761f, 1f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(16384);
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        game.shape.setColor(1,1,1,1);
        game.shape.rect((float) Gdx.graphics.getWidth() / 2 - 200, (float) Gdx.graphics.getHeight() / 2 + 70, 200, 60);
        game.shape.rect((float) Gdx.graphics.getWidth() / 2 - 200, (float) Gdx.graphics.getHeight() / 2 - 30, 200, 60);
        game.shape.rect((float) Gdx.graphics.getWidth() / 2 - 200, (float) Gdx.graphics.getHeight() / 2 - 130, 200, 60);
        game.shape.end();
        game.batch.begin();
        game.font.getData().setScale(.3f);
        game.font.draw(game.batch, "Easy", (float) Gdx.graphics.getWidth() / 2 - 140, (float) Gdx.graphics.getHeight() / 2 + 110);
        game.font.draw(game.batch, "Normal", (float) Gdx.graphics.getWidth() / 2 - 150, (float) Gdx.graphics.getHeight() / 2 + 10);
        game.font.draw(game.batch, "Hard", (float) Gdx.graphics.getWidth() / 2 - 140, (float) Gdx.graphics.getHeight() / 2 - 90);
        game.font.draw(game.batch,"Best Time: "+game.easyHiScore,(float) Gdx.graphics.getWidth() / 2 + 30,(float) Gdx.graphics.getHeight() / 2 + 110);
        game.font.draw(game.batch,"Best Time: "+game.normalHiScore,(float) Gdx.graphics.getWidth() / 2 + 30,(float) Gdx.graphics.getHeight() / 2 + 10);
        game.font.draw(game.batch,"Best Time: "+game.hardHiScore,(float) Gdx.graphics.getWidth() / 2 + 30,(float) Gdx.graphics.getHeight() / 2 - 90);
        game.batch.end();
        input();
    }

    private void input() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (x > (float) Gdx.graphics.getWidth() / 2 - 200 && x < (float) Gdx.graphics.getWidth() / 2 - 100 + 100 && Gdx.graphics.getHeight() - y > Gdx.graphics.getHeight() / 2 + 70 && Gdx.graphics.getHeight() - y < Gdx.graphics.getHeight() / 2 + 130) {
                game.setScreen(new GameScreen(game,1));
            }
            if (x > (float) Gdx.graphics.getWidth() / 2 - 200 && x < (float) Gdx.graphics.getWidth() / 2 - 100 + 100 && Gdx.graphics.getHeight() - y > Gdx.graphics.getHeight() / 2 - 30 && Gdx.graphics.getHeight() - y < Gdx.graphics.getHeight() / 2 + 30) {
                game.setScreen(new GameScreen(game, 2));
            }
            if (x > (float) Gdx.graphics.getWidth() / 2 - 200 && x < (float) Gdx.graphics.getWidth() / 2 - 100 + 100 && Gdx.graphics.getHeight() - y > Gdx.graphics.getHeight() / 2 - 130 && Gdx.graphics.getHeight() - y < Gdx.graphics.getHeight() / 2 - 70) {
                game.setScreen(new GameScreen(game,3));
            }
        }
    }
}
