import java.util.Scanner;
import java.util.regex.Pattern;

/******************************************************************
 * Vector330 - provides a 2D vector with associated operations and support
 *
 * @author  Ymani Nesmith
 * @version 1.0
 */
public class Vector330 {

    // variables declared here are visible throughout entire class
    private static final double EPS = 1.0E-9; //constant

    //Attributes
    private double x;
    private double y;

    //Constructors

    /**
     * zero argument constructor
     */
    public Vector330() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Vector330 - constructor intializing both x and y components (double)
     *
     * @param x new value for x component of the vector
     * @param y new vaue for y component of the vector
     */
    public Vector330(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * getX - return the x component of the vector as a double
     *
     * @return the value of y component as a double
     */
    public double getX() {
        return this.x;
    }

    /**
     * setX - sets the x component of vector using an input of type double
     *
     * @param x new value for x component as a double
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getY - return the y component of the vector as a double
     *
     * @return the value of y component as a double
     */
    public double getY() {
        return this.y;
    }

    /**
     * setY - sets the y component of vector using an input of type double
     *
     * @param y new value for x component as a double
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * getXint - return the y component of the vector as a int
     *
     * @return the value of x component as a double
     */
    public int getXint(){
        return (int) this.x;
    }

    /**
     * getYint - return the y component of the vector as a int
     *
     * @return the value of y component as a double
     */
    public int getYint(){
        return (int) this.y;
    }

    /**
     * getXlong - return the y component of the vector as a long
     *
     * @return the value of x component as a double
     * @param v
     */
    public long getXlong(double v){
        return (long) this.x;
    }

    /**
     * getYlong - return the y component of the vector as a long
     *
     * @return the value of y component as a double
     */
    public long getYlong(){
        return (long) this.y;
    }

/**
 * setX - sets the x component of vector using an input of type int
 *
 * @param x new value for x component as a double
 */
    public void setX(int x){
        this.x = (double) x;
    }

    /**
     * setY - sets the y component of vector using an input of type int
     *
     * @param y new value for x component as a double
     */
    public void setY(int y){
        this.y = (double) y;
    }

    /**
     * setX - sets the x component of vector using an input of type long
     *
     * @param x new value for x component as a double
     */
    public void setX(long x){
        this.x = (double)x;
    }

    /**
     * setY - sets the y component of vector using an input of type long
     *
     * @param y new value for x component as a double
     */
    public void setY(long y){
        this.y = (double) y;
    }


    /**
     * equals() - checks for vector equality if this and the other (other) vector have components within epsilon of each other
     *
     * @param other other vector passed in
     * @return boolean true if this and other vectors are close enough, else false
     */
    public boolean equals(Vector330 other) {
        return (Math.abs(this.x - other.x) < EPS) && (Math.abs(this.y - other.y) < EPS);
    }


    /**
     * add() - does vector addition of this vector with the one passed in
     *
     * @param other other vector passed in
     * @return vector sum of this and the other vector
     */
    public Vector330 add(Vector330 other) {
        return new Vector330(this.x + other.x, this.y + other.y);
    }

    /**
     * subtract() - subtracts passed in vector from this vector
     *
     * @param other other vector passed in
     * @return vector difference of this vector minus the other vector passed in
     */
    public Vector330 subtract(Vector330 other) {
        return new Vector330(this.x - other.x, this.y - other.y);
    }

    /**
     * dotProduct() - computes the dot product of this vector and the other vector
     *
     * @param other other vector to compute the dot product with
     * @return the scalar (double) dot product of this vector and the other vector
     */
    public double dotProduct(Vector330 other) {
        return (this.getX() * other.getX()) + (this.getY() * other.getY());
    }

    /**
     * scale() - does a scalar-vector multiplication of this vector with double value passed in
     *
     * @param s value to scale the vector by
     * @return the scaled vector
     */
    public Vector330 scale(double s) {
        return new Vector330(this.getX() * s, this.getY() * s);
    }

    /**
     * magnitude() - computes the magnitude (2-norm or length) of this vector
     *
     * @return magnitude of the vector
     */
    public double magnitude() {
        return (Math.sqrt(this.getX() * this.getX()) + (this.getY() * this.getY()));
    }

    /**
     * direction() - computes and returns the direction (orientation) of the vector in radians
     *
     * @return angle of the vector in radians
     */
    public double direction() {
        return Math.atan2(this.getY(), this.getX());
    }

    /**
     * normalize() - creates a normalized (of length one) vector in same direction as this vector
     *
     * @return the normalized vector or the zero vector is original vector is close to zero in magnitude
     *
     */
    public Vector330 normalize() {
        if (this.magnitude() <= EPS) {
            return new Vector330();
        } else {
            return this.scale(1.0 / this.magnitude());
        }
    }

    /**
     * toString() - overrides the default toString() method producing an angle-bracket version of this vector
     *
     * @return string representation of the vector in the form "&lt; 3.0, 4.0 &gt;"
     */
    @Override
    public String toString() {
        return "< " + this.x + ", " + this.y + " >";
    }

    /**
     * parseVector() - inputs a Scanner object from which it reads and parses a string representing the vector with
     * and expected form of "&lt; 3.0, 4.0 &gt;" - note spaces are needed after '&lt;', after the comma,
     * and before the '&gt;'.
     *
     * @param input Scanner object from which to read the String representation of the vector
     * @return a new Vector330Class object based upon the provided input
     * @throws Exception object with the message set to a description of the parsing error encountered
     */
    public static Vector330 parseVector(String input) throws Exception {
        //vector calculator is going to call this method - after the scanner identifies an operator

        Scanner s = new Scanner(input);

        double xCo = 0;
        double yCo = 0;

        Pattern orgPattern = s.delimiter(); //retain original delimiters

        s.useDelimiter("[" + orgPattern + ",]");

        if (s.hasNext(",")) {
            s.next(",");

            if (s.hasNextDouble()) {
                yCo = s.nextDouble();
                s.useDelimiter(orgPattern);

                if(s.hasNext(",")){
                    s.next(",");
                    if (s.hasNext(">")) {
                    s.next(">");

                        if(s.hasNext("<")){
                        s.next("<");

                    return new Vector330(xCo, yCo);

                } else {
                throw new Exception(" PARSE ERROR: Missing '>' to end vector");
                    }

             } else {
            throw new Exception("PARSE ERROR: Missing Y Coordinate for vector");
                 }

         } else {
            throw new Exception("PARSE ERROR: Missing comma between X and Y coordinates");
                }

         }else {
            throw new Exception("PARSE ERROR: Missing X Coordinate for vector");
        }

    } else{
            throw new Exception("PARSE ERROR: Missing '<' to start vector");
        }
    }
}

