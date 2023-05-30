package com.sophia.droid.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


import java.util.ArrayList;

public class Droid {

    private float speed = 2f;;
    private Arena arena;
    private Vector2 target = null;

    private float width = 1f;
    private float height = 1f;
    private final ArrayList<DroidStrategy> droidStrategies = new ArrayList<>();
    private Body body;
    private int healthPoints = 3;
    private int boxes = 0;

    public Droid(World world) {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(7.5f, 7.5f);

        // Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);
        body.setUserData(this);

        // Create a circle shape and set its radius to 6
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        //body.setFixedRotation(true);
        setBody(body);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        polygonShape.dispose();
    }

    public float getX() {

        return body.getPosition().x;

    }

    public float getY() {

        return body.getPosition().y;

    }

    public float getSpeed() {

        return speed;

    }

    public void setSpeed(float speed) {

        this.speed = speed;

    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setTarget(float targetX, float targetY) {

        this.target = new Vector2(targetX, targetY);

    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public Vector2 getTarget() {
        return this.target;
    }

    public void removeTarget() {
        this.target = null;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public boolean removeDroidStrategy(Class<? extends DroidStrategy> droidStrategyClass) {
        for (DroidStrategy strategy : droidStrategies){
            if (strategy.getClass().equals(droidStrategyClass)){
                droidStrategies.remove(strategy);
                return true;
            }
        }
        return false;
    }

    public void addDroidStrategy(DroidStrategy droidStrategy) {
        // when adding a strategy make sure that it has only one element of class
        // e.g droid can not have 2 MoveToTarget strategies
        removeDroidStrategy(droidStrategy.getClass());
        this.droidStrategies.add(droidStrategy);
    }

    public void update(float delta) {

        for (DroidStrategy droidStrategy : droidStrategies){
            droidStrategy.update(this, delta);
        }
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void reduceHealthPoints(int points) {
        this.healthPoints -= 1;
        if (this.healthPoints <= 0){
            // if dead, remove from arena
            arena = null;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void interactWith(Box box) {
        boxes += 1;
        box.getArena().removeCoin(box);
    }

    public int getBoxes() {
        return boxes;
    }
}
