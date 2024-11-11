package org.latinschool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Customer {
    int x,y,size,length;
    ArrayList<Balloon> goal;
    public Customer(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        length = 3;
        goal = new ArrayList<Balloon>();
        for (int i = 0; i < length; i++) {
            goal.add(new Balloon(x-20+20*i,y+50+30*i*(2-(i%3)),15,"" +(i+1)));
        }
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x,y,size);
        shape.line(x,y-size,x,y-(5*size));
        shape.line(x,y-(3*size),x-(2*size),y-(2*size));
        shape.line(x,y-(3*size),x+(2*size),y-(2*size));
        shape.line(x,y-(5*size),x-size,y-(7*size));
        shape.line(x,y-(5*size),x+size,y-(7*size));
        Gdx.gl20.glLineWidth(3);
        shape.arc((float) x, (float) (y-(0.2*size)), (float) size / 2, 180,180);
        shape.point((float) (x-(size * 0.3)), (float) (y+(size * 0.4)),0);
        shape.point((float) (x+(size * 0.3)), (float) (y+(size * 0.4)),0);
    }
}
