import processing.core.PVector;

/**
 * @author Alex
 */
class Particle {
    PVector position;
    private PVector velocity;
    private PVector acceleration;
    private float lifespan;

    private boolean seed = false;

    private float hu;

    Particle(float x, float y, float h) {
        hu = h;

        acceleration = new PVector(0, 0);
        velocity = new PVector(0, Fireworks.processing.random(-12, -5));
        position = new PVector(x, y);
        seed = true;
        lifespan = (float) 255.0;
    }

    Particle(PVector l, float h) {
        hu = h;
        acceleration = new PVector(0, 0);
        velocity = PVector.random2D();
        velocity.mult(Fireworks.processing.random(4, 8));
        position = l.copy();
        lifespan = (float) 255.0;
    }

    void applyForce(PVector force) {
        acceleration.add(force);
    }

    void run() {
        update();
        display();
    }

    boolean explode() {
        if (seed && velocity.y > 0) {
            lifespan = 0;
            return true;
        }
        return false;
    }

    // Method to update position
    void update() {

        velocity.add(acceleration);
        position.add(velocity);
        if (!seed) {
            lifespan -= 8.0;
            velocity.mult((float) 0.95);
        }
        acceleration.mult(0);
    }

    void display() {
        Fireworks.processing.stroke(hu, 255, 255, lifespan);
        if (seed) {
            Fireworks.processing.strokeWeight(4);
        } else {
            Fireworks.processing.strokeWeight(2);
        }
        Fireworks.processing.point(position.x, position.y);
    }

    boolean isDead() {
        return lifespan < 0.0;
    }
}
