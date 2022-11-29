package geometry;

/** A point models an exact location in the cartesian space. It is specified through an X and Y coordinates.
 * It can be used to define geometrical shapes.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Create a point in the origin (0,0)
     */
    public Point() {

    }

    /**
     * Create a point in a given location
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ( "(" + getX() + "," + getY() + ")");
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.getX())^Double.hashCode(this.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Point.class) return false;
        if (obj == this) return true;

        return ((Point) obj).getX() == this.getX() && ((Point) obj).getY() == this.getY();

    }



}
