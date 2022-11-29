package geometry;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * A rectangle is a quadrilateral with four right angles.
 * Each angle has 4 points, which are connected in that order in a CCW loop and the last point connects with the first.
 * A rectangle is not allowed to have any duplicated vertices.
 * This rectangle couldn't be rotated and its axis are aligned with cartesian X and Y axis.
 */
public class Rectangle extends Shape <Rectangle> {
    private List<Point> points = new ArrayList<>();
    private double area;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private final List<Double> slopes = new ArrayList<>();

    /** initialize a rectangle with no rotation with a given center point and sides length
     *
     * @param center rectangle center point
     * @param w rectangle width
     * @param l rectangle length
     */
    public Rectangle(Point center, double w, double l) {

        if(w <= 0 || l <= 0){
            throw new InvalidParameterException("w and l should be a positive number greater than 0");
        }

        this.points.add(new Point(center.getX()-l/2, center.getY()-w/2));
        this.points.add(new Point(center.getX()+l/2, center.getY()-w/2));
        this.points.add(new Point(center.getX()+l/2, center.getY()+w/2));
        this.points.add(new Point(center.getX()-l/2, center.getY()+w/2));

        setMaxAndMin();
        setSlopes();
        setArea(w,l);
    }

    /**
     * Set the max and min X and Y values of the rectangle
     */
    private void setMaxAndMin() {

        this.xMin = this.getPoints().get(0).getX();
        this.xMax = this.getPoints().get(2).getX();
        this.yMin = this.getPoints().get(0).getY();
        this.yMax = this.getPoints().get(2).getY();

    }

    public List<Point> getPoints() {
        return points;
    }

    /**
     * Set rectangle slopes CCW, starting with the bottom line.
     */
    private void setSlopes(){

        for (int i=0; i<4; i++){

            double x1 = this.getPoints().get(i).getX();
            double y1 = this.getPoints().get(i).getY();
            double x2 = (i<3) ? this.getPoints().get(i + 1).getX() : this.getPoints().get(0).getX();
            double y2 = (i<3) ? this.getPoints().get(i + 1).getY() : this.getPoints().get(0).getY();

            double m = (Math.abs(x2-x1) == 0 ) ? Math.signum((y2 - y1)) : ((y2 - y1) / (x2 - x1));

            this.slopes.add(m);
        }
    }

    private void setArea(double w, double l){
        this.area = w*l;
    }

    public double getArea() {
        return area;
    }

    public List<Double> getSlopes() {
        return slopes;
    }

    public double getxMin() {
        return xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }

    @Override
    public String toString() {
        return getPoints().toString();
        }

    @Override
    public List<Point> findIntersections (Rectangle rectangle){
        ArrayList<Point> intersectionPointsList = new ArrayList<>();

        for (int i=0; i<4; i++){
            double slope_r1 = this.getSlopes().get(i);

            for (int j=0; j<4; j++){
                double slope_r2 = rectangle.getSlopes().get(j);

                //Parallels never intersect
                if(areParallels(slope_r1,slope_r2)) continue;

                double x,y, xMin,xMax,yMin,yMax;

                // when a line is parallel to the X axis, its equation is X = k_x
                // and when a line is parallel to the Y axis, its equation is Y = k_y
                // the intersection point of two perpendicular lines
                // parallel to cartesian axis is (k_x,k_y).
                if (isVertical(slope_r1)){
                    x = this.getPoints().get(i).getX();
                    y = rectangle.getPoints().get(j).getY();
                    xMin = rectangle.getxMin();
                    xMax = rectangle.getxMax();
                    yMin = this.getyMin();
                    yMax = this.getyMax();
                } else {
                    x = rectangle.getPoints().get(j).getX();
                    y = this.getPoints().get(i).getY();
                    xMin = this.getxMin();
                    xMax = this.getxMax();
                    yMin = rectangle.getyMin();
                    yMax = rectangle.getyMax();
                }

                if ( x >= xMin && x <= xMax && y >= yMin && y <= yMax){
                    intersectionPointsList.add(new Point(x,y));
                }
            }
        }
        return intersectionPointsList;
    }

    private boolean areParallels(double slope1, double slope2){
        return Math.abs(slope1) == Math.abs(slope2);
    }

    private boolean isVertical(double slope){
        return Math.abs(slope) == 1;
    }

    @Override
    public boolean contains(Rectangle rectangle){
        // A rectangle only can contain a smaller rectangle
        if (!this.isBigger(rectangle)){
            return false;
        }

        // A contained rectangle should have all the points inside the container rectangle
        for(int i=0; i<4; i++){
            Point p_r2 = rectangle.getPoints().get(i);
            double x = p_r2.getX();
            double y = p_r2.getY();

            if( x <= this.getxMin()  ||  x >= this.getxMax()) return false;
            if(y <= this.getyMin()  ||  y >= this.getyMax()) return false;
        }
        return true;
    }

    protected boolean isBigger(Rectangle rectangle){
        return  (rectangle.getArea() < this.getArea());
    }

    @Override
    public boolean isAdjacent (Rectangle rectangle) {

        for (int i = 0; i < 4; i++) {
            //define variables for rectangle 1
            Point p1_r1, p2_r1;
            double x1_r1, x2_r1, y1_r1, y2_r1, slope_r1;

            p1_r1 = this.getPoints().get(i);
            p2_r1 = (i==3) ? this.getPoints().get(0) : this.getPoints().get(i+1);

            x1_r1 = p1_r1.getX();
            x2_r1 = p2_r1.getX();

            y1_r1 = p1_r1.getY();
            y2_r1 = p2_r1.getY();
            slope_r1 = this.getSlopes().get(i);

            for (int j = 0; j < 4; j++) {
                //define variables for rectangle 2
                Point p1_r2, p2_r2;
                double x1_r2, x2_r2, y1_r2, y2_r2, slope_r2;

                slope_r2 = rectangle.getSlopes().get(j);
                p1_r2 = rectangle.getPoints().get(j);
                p2_r2 = (j==3) ? rectangle.getPoints().get(0) : rectangle.getPoints().get(j+1);

                x1_r2 = p1_r2.getX();
                x2_r2 = p2_r2.getX();
                y1_r2 = p1_r2.getY();
                y2_r2 = p2_r2.getY();

                if (areParallels(slope_r1,slope_r2)) continue;

                // if vertical, check if X is the same and check if Y values are inside the range
                if (isVertical(slope_r1)){

                    // If X value ins not te same in both lines, skip cycle
                    if (x1_r1 != x1_r2) continue;

                    // if the X coordinate of a point which belongs to a line
                    // is inside the range of its parallel line, return true.
                    if ((y1_r2 > this.getyMin() && y1_r2 < this.getyMax()) || (y2_r2 > this.getyMin() && y2_r2 < this.getyMax()) ) return true;
                    if ((y1_r1 > rectangle.getyMin() && y1_r1 < rectangle.getyMax()) || (y2_r1 > rectangle.getyMin() && y2_r1 < rectangle.getyMax()) ) return true;

                } else {
                    // If Y value is not te same in both lines, skip cycle
                    if (y1_r1 != y1_r2) continue;

                    // if the X coordinate of a point which belongs to a line
                    // is inside the range of its parallel line, return true.
                    if ((x1_r2 > this.getxMin() && x1_r2 < this.getxMax()) || (x2_r2 > this.getxMin() && x2_r2 < this.getxMax()) ) return true;
                    if ((x1_r1 > rectangle.getxMin() && x1_r1 < rectangle.getxMax()) || (x2_r1 > rectangle.getxMin() && x2_r1 < rectangle.getxMax()) ) return true;
                }
            }
        }
        return false;
    }


}

