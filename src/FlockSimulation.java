import java.awt.*;
import java.lang.Object;
import java.util.ArrayList;

/******************************************************************
 * FlockSimulation - Top level class for the Flocking Simulation with the activate() method to run the simulation
 *
 * @author  Ymani Nesmith
 * @version 1.0
 */
public class FlockSimulation {

    public static void main(String[] args) {
        FlockSimulation flock = new FlockSimulation();
        flock.activate();
    }

        private final Color BACKGROUND_COLOR = Color.gray;
        private final int SCREEN_WIDTH = 1000;
        private final int SCREEN_HEIGHT = 800;

        private boolean pause = false;


        FlockSimulation(){ //Zero argument constructor for the FlockingSimulation
        }

    /**
     * Sets up the DrawingPanel, creates the flocks, and runs the animation to include pause/resume via spacebar,
     * disruptions (via a left mouse click), and exits with a right mouse click
     */
    void activate(){
            DrawingPanel panel = new DrawingPanel(SCREEN_WIDTH,SCREEN_HEIGHT);

            Graphics2D g = panel.getGraphics();

            Boid.setWindow(g, SCREEN_WIDTH, SCREEN_HEIGHT);

            panel.setBackground(BACKGROUND_COLOR);

            panel.setWindowTitle("Welcome to Flopping Birds. Press the spacer to continue. If you wish to exit" +
                    "Right Click anywhere on the screen");

            ArrayList<Flock> flocks = new ArrayList<>();

            flocks.add(new Flock("Pigeons", Color.RED, 10, 5, 30));

            flocks.add(new Flock("Falcons", Color.BLUE, 7, 20, 15));
            for(Flock f : flocks){
                f.draw();
            }

            panel.copyGraphicsToScreen();

            while(!panel.mouseClickHasOccurred(DrawingPanel.RIGHT_BUTTON)){
                panel.setBackground(BACKGROUND_COLOR);
                for(Flock f : flocks){
                    f.move();
                }
                for(Flock f : flocks){
                    f.draw();
                }

                panel.copyGraphicsToScreen();

                panel.sleep(100);
            }
            panel.closeWindow();
        }


    }
