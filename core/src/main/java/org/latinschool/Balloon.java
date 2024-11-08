package org.latinschool;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.math.MathUtils.random;

public class Balloon {
    int x,y,size;
    String num;
    Color color;
    public Balloon(int x, int y, int size, String num) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.num = num;
        switch(random.nextInt(3)) {
            case 0:
                this.color = Color.RED;
                break;
            case 1:
                this.color = Color.GREEN;
                break;
            case 2:
                this.color = Color.BLUE;
                break;
        }
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);

    }
}
