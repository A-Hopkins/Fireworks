import java.util.ArrayList;

/**
 * @author Alex
 */
class Firework {

    private ArrayList<Particle> particles;
    private Particle firework;
    private float hu;

    Firework() {
        hu = Fireworks.processing.random(255);
        firework = new Particle(Fireworks.processing.random(Fireworks.processing.width), Fireworks.processing.height, hu);
        particles = new ArrayList<>();
    }

    boolean done() {
        return firework == null && particles.isEmpty();
    }

    void run() {
        if (firework != null) {
            Fireworks.processing.fill(hu, 255, 255);
            firework.applyForce(Fireworks.gravity);
            firework.update();
            firework.display();

            if (firework.explode()) {
                for (int i = 0; i < 100; i++) {
                    particles.add(new Particle(firework.position, hu));
                }
                firework = null;
            }
        }

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.applyForce(Fireworks.gravity);
            p.run();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }
}