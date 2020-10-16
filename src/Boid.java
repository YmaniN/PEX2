import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;
import java.util.Random;

/******************************************************************
 * Boid - Boid class provides the single entity level for members of the flocks.
 *
 * @author  Ymani Nesmith
 * @version 1.0
 */

public class Boid { //Base zero-argument constructor that creates the Boid with a random location and a random 360 deg velocity

    //static attributes
    private static Graphics2D g;
    private static int screenWidth;
    private static int screenHeight;
    private static Random rand = new Random(System.currentTimeMillis());

    //instance attributes
    private Color color;
    private int size;
    private int speed;
    private Vector330 location;
    private Vector330 velocity;
    private Vector330 newVelocity;

    public Boid(Color color, int size, int speed){
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.location = new Vector330(rand.nextDouble()*screenWidth, rand.nextDouble()*screenHeight);
        double angle = 2 * Math.PI * rand.nextDouble();
        this.velocity = new Vector330(this.speed * Math.cos(angle), this.speed * Math.sin(angle));
    }


    public static void setWindow(Graphics2D g, int screenWidth, int screenHeight){
        Boid.g = g;
        Boid.screenWidth = screenWidth;
        Boid.screenHeight = screenHeight;
    }

    /**
     * draws the boid at its current location on the DrawingPanel
     */
    void draw(){
        g.setColor(this.color);
        g.fillOval(this.location.getXint()-this.size, this.location.getYint()-this.size, 2* this.size, 2*this.size);
        Vector330 endPoint = this.location.add(this.velocity.normalize().scale(2*this.size));
        g.drawLine(this.location.getXint(), this.location.getYint(), endPoint.getXint(), endPoint.getYint());
    }

    /**
     * Move the Boid away from the (x,y) disruption position passed in so that the Boid is the evadeRadius away from the disruption point.
     *
     * @param x - x coordinate of the disruption point
     * @param y - y coordinate of the disruption point
     * @param evadeRadius - distance used to detect if evasion is needed and to determine how far away to move
     */
     void evade(int x, int y, int evadeRadius){


     }

     Vector330 getLocation(){

         return this.location;
     }

     public double getSpeed(){

         return this.speed;
    }

     Vector330 getVelocity(){
         return this.velocity;
    }

    /*****************************************************************
     * move() - advances the projectile by its velocity components
     * 		bouncing off of the limits as appropriate
     */
   public void move(){

       this.location = this.location.add(getVelocity());
       if(this.location.getXint() > screenWidth || (this.location.getXint() < 0  )){
           this.velocity.setX(this.velocity.getX()*-1); //reverses the x direction
       }
       if (this.location.getYint() >screenHeight || (this.location.getYint() <0)){
           this.velocity.setY(this.velocity.getY()*-1);//reverses the y direction
       }
   }

    /**
     * @param color - new color
     */
    public void setColor(Color color){

        this.color = color;
    }

    /**
     *
     * @param image - new image for the boid
     * @throws IOException
     */
    void setImage(BufferedImage image) throws IOException {
        BufferedImage img = ImageIO.read(new File("f://pex2.jpg"));
        ImageIcon icon = new ImageIcon(img);


    }

    /**
     *setLocation - Updates this Boid's location based upon the location vector passed in
     * @param l - new location vector passed in
     */
    void setLocation(Vector330 l){

       l = this.location;

    }

    /**
     *setsNewVelocity - Sets the new velocity vector based upon the one provided (via alias)
     * @param nv -new velocity vector passed in
     */
    void setNewVelocity(Vector330 nv){

       this.newVelocity = nv;
    }

    /**
     * setSize - change the boid's size
     * @param size - new size for the boid
     */
   public void setSize(int size){

        this.size = size;

    }

    /**
     * setSpeed - Change the boid's speed
     * @param speed - new speed
     */
    void setSpeed(double speed){

        this.speed = (int) speed;
    }

    /**
     * setVelocity - Updates this Boid's velocity based upon the velocity vector passed in
     * @param v - new velocity vector passed in
     */
    void setVelocity(Vector330 v){

      this.velocity = v;
    }

    /**
     * updateVelocity - Sets the current velocity to the new velocity vector; this allows the new velocity calculation to
     * be based upon current velocities of the other Boids. Once all of the Boids have a new velocity,
     * we can use this method to make their current velocity the new velocity
     */
    void updateVelocity(){

        this.velocity = this.newVelocity;

    }


}
