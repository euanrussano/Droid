package com.sophia.droid.controller;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Box;
import com.sophia.droid.service.BoxFactory;

import java.util.ArrayList;

public class BoxController {
    private BoxFactory boxFactory;
    private final Arena arena;
    private final Stage uiStage;
    private final Stage mainStage;

    public BoxController(BoxFactory boxFactory, Arena arena, Stage uiStage, Stage mainStage) {
        this.boxFactory = boxFactory;
        this.arena = arena;
        this.uiStage = uiStage;
        this.mainStage = mainStage;
    }

    public void update(float delta) {
        // check if the box was removed from the arena, in positive case generate another
        if (arena.getBox() == null){
            Box box = boxFactory.create();

            Array<Body> bodies = new Array<>();
            box.getBody().getWorld().getBodies(bodies);

            Rectangle boxRectangle = new Rectangle(box.getX()-0.5f, box.getY()-0.5f, box.getWidth(), box.getHeight());
            Rectangle rectangle = new Rectangle();
            // Step 3: Iterate over existing bodies and check for collisions
            boolean overlapping = true;
            int x = 0;
            int y = 0;
            while(overlapping) {
                overlapping = false;
                // place it randomly
                x = random.nextInt(arena.getWidth());
                y = random.nextInt(arena.getHeight());
                boxRectangle.setPosition(x, y);

                for (Body body : bodies) {
                    if (body.getUserData() != null && !body.equals(box.getBody())) {
                        // Adjust this condition as per your requirements for checking specific bodies
                        rectangle.set(body.getPosition().x-0.5f, body.getPosition().y-0.5f, 1f, 1f);
                        if (boxRectangle.overlaps(rectangle)){
                                overlapping = true;
                        }
                    }
                }
            }


            box.getBody().setTransform(new Vector2(x+0.5f, y+0.5f), 0);
            arena.setBox(box);

        }
    }
}
