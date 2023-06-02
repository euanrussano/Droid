package com.sophia.droid.service;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sophia.droid.model.Box;

public class BoxFactory {

    private World world;

    public BoxFactory(World world) {
        this.world = world;
    }

    public Box create() {

        Box box = new Box();
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
//        bodyDef.position.set(x+0.5f, y+0.5f);

        // Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);
        body.setUserData(box);

        // Create a circle shape and set its radius to 6
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f,0.5f );

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        box.setBody(body);

        return box;
    }
}
