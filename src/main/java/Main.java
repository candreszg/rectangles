import geometry.Point;
import geometry.Rectangle;

public class Main {

    public static void main(String[] args) {

        Rectangle base_rectangle = new Rectangle(new Point(-1.5,0.5), 5, 7);
        Rectangle noIntersection_rectangle = new Rectangle(new Point(5.5,1), 5, 5);
        Rectangle intersection_rectangle = new Rectangle(new Point(), 5, 5);
        Rectangle contain_rectangle = new Rectangle(new Point(), 1, 1);
        Rectangle adjacent_rectangle = new Rectangle(new Point(4,3), 4, 4);

        System.out.println("\n");
        System.out.println("base_rectangle = " + base_rectangle);
        System.out.println("\n");

        System.out.println("No intersection case:");
        System.out.println("rectangle = " + noIntersection_rectangle);
        System.out.println("contains: " + base_rectangle.contains(noIntersection_rectangle));
        System.out.println("isAdjacent: " + base_rectangle.isAdjacent(noIntersection_rectangle));
        System.out.println("findIntersections: " + base_rectangle.findIntersections(noIntersection_rectangle));
        System.out.println("\n");

        System.out.println("Intersection case:");
        System.out.println("rectangle = " + intersection_rectangle);
        System.out.println("findIntersections: " + base_rectangle.findIntersections(intersection_rectangle));
        System.out.println("\n");

        System.out.println("Contains case:");
        System.out.println("rectangle = " + intersection_rectangle);
        System.out.println("contains: " + base_rectangle.findIntersections(contain_rectangle));
        System.out.println("\n");

        System.out.println("Adjacent case:");
        System.out.println("rectangle = " + adjacent_rectangle);
        System.out.println("contains: " + base_rectangle.findIntersections(contain_rectangle));
        System.out.println("\n");

    }

}
