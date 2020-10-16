import java.awt.*;

/******************************************************************
 * Flock - The Flock class is an aggregation of Boid objects all with similar characteristics to include those that dictate its flocking behaviors of alignment, cohesion, and separation.
 *
 * @author  Ymani Nesmith
 * @version 1.0
 */

public class Flock {

    //attributes
    private Boid[] flock;
    private int numBoids;
    private String name;
    private Color color;
    private double radiusSeparation = 25;
    private double radiusCohesion = 100;
    private double radiusAlignment = 100;
    private double radiusEvasion = 100;
    private double weightCurrentVelocity = 0.4;
    private double weightSeparation = 0.2;
    private double weightCohesion = 0.2;
    private double weightAlignment = 0.2;
    private int size;
    private int speed;


    //Constructor

    /**
     *
     * @param name - name of the new flock
     * @param color - number of bods in the new flock
     * @param size - color of each boid in the flock
     * @param speed - size of each boid in the new flock
     * @param numBoids - speed of each boid in the new flock
     */
    public Flock(String name, Color color, int size, int speed, int numBoids){

        this.name = name;
        this.numBoids = numBoids;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.flock = new Boid[numBoids];

        for (int i = 0; i < this.numBoids; i++){
            flock[i] = new Boid(this.color, this.size, this.speed);
        }
    }

    public void draw(){
        for(Boid b : this.flock){
            b.draw();
        }
    }

    void setRadiusSeparation(int radiusSeparation){
        this.radiusSeparation = radiusSeparation;
    }

    void setRadiusAlignment(int radiusAlignment){

        this.radiusAlignment = radiusAlignment;
    }

    void setRadiusCohesion(int radiusCohesion){

        this.radiusCohesion = radiusCohesion;
    }
    void setRadiusEvasion(int radiusEvasion){

        this.radiusEvasion = radiusEvasion;
    }

    void setWeightSeparation(double weightSeparation){

        this.weightSeparation = weightSeparation;
    }

    void setWeightAlignment(double weightAlignment){

        this.weightAlignment = weightAlignment;
    }

    void setWeightCohesion(double weightCohesion){

        this.weightCohesion = weightCohesion;
    }

    /**
     * move() - Moves the flock first by computing a new velocity for each boid in the flock. Once all the boids have their new velocity,
     * update the velocity and apply it to move each boid.
     */
    void move(){

        for(Boid b: this.flock){
            Vector330 result = new Vector330();
            result = result.add(b.getVelocity().normalize().scale(weightCurrentVelocity));
            result = result.add(calculateAlignment(b).scale(weightAlignment));
            b.setNewVelocity(result.normalize().scale(this.speed));
            //cohesion and separation psuedocode ib assignment
            result = result.add(calculateCohesion(b).scale(weightCohesion));
            b.setLocation(result.normalize().scale(this.size));
            //cohesion then separation last
            //result is supposed to be the magnitude of  unit vector of the average separation vector

        }
        for(Boid b : this.flock){
            b.updateVelocity();
            b.move();
        }

    }

    private Vector330 calculateAlignment(Boid current) {
        Vector330 alignment = new Vector330();
        for(Boid other: this.flock){
            if(distance(other, current) < radiusAlignment){
                alignment = alignment.add(other.getVelocity());
            }
        }
        return alignment.normalize();
    }

    private Vector330 calculateCohesion(Boid boid) {
        Vector330 cohesion = new Vector330();
    for(Boid other : this.flock){
        if(distance(other,boid) < radiusCohesion){
            cohesion = cohesion.add(other.getLocation());
            numBoids++;
        }
    }
        return cohesion.normalize();
    }

    private Vector330 calculateSeparation(Boid boid){
        Vector330 sep = new Vector330();
        for(Boid other : this.flock){
            if(distance(other,boid) < radiusSeparation){
                sep = sep.subtract(other.getLocation());
                sep.scale(radiusSeparation).subtract(other.getLocation());
            }
        }

       return sep.normalize();
    }

    void evade(int x, int y){

    }

    private Vector330 getSeparationVector(Boid b){
        return this.calculateSeparation(b);
    }

    private Vector330 getAlignmentVector(Boid b){
        return this.calculateAlignment(b);

    }

    private Vector330 getCohesionVector(Boid b){
        return this.calculateCohesion(b);

    }

    private double distance(Boid a, Boid b){
        return  Math.sqrt(Math.pow(a.getLocation().getX() - b.getLocation().getX(), 2) +
                (Math.pow(a.getLocation().getY() - b.getLocation().getY(), 2)));

    }
}
