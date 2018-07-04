import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * @author Alex
 */
public class Fireworks extends PApplet {

    static PApplet processing;
    private ArrayList<Firework> fireworks;
    static PVector gravity = new PVector(0, (float) 0.2);

    public void settings() {
        size(640, 400, P2D);
    }

    public void setup() {
        processing = this;
        fireworks = new ArrayList<>();
        colorMode(HSB);
        background(51);
    }

    public void draw() {

        if (random(1) < 0.08) {
            fireworks.add(new Firework());
        }
        fill(51, 50);
        noStroke();
        rect(0, 0, width, height);

        for (int i = fireworks.size() - 1; i >= 0; i--) {
            Firework f = fireworks.get(i);
            f.run();
            if (f.done()) {
                fireworks.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Fireworks", args);
    }

}
