package geometry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RectangleTest {

    Rectangle base_rectangle;
    Rectangle noIntersection_rectangle;
    Rectangle intersection_rectangle;
    Rectangle contained_rectangle;
    Rectangle adjacent_rectangle;

    @BeforeAll
    public void setUp() throws Exception{
        base_rectangle = new Rectangle(new Point(-1.5,0.5), 5, 7);
        noIntersection_rectangle = new Rectangle(new Point(5.5,1), 5, 5);
        intersection_rectangle = new Rectangle(new Point(), 5, 5);
        contained_rectangle = new Rectangle(new Point(), 1, 1);
        adjacent_rectangle = new Rectangle(new Point(4,3), 4, 4);
    }

    @Test
    @DisplayName("Rectangle1 should find intersections with rectangle 2")
    void findIntersections() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(-2.5,-2.0));
        points.add(new Point(2.0,2.5));
        assertEquals(points,base_rectangle.findIntersections(intersection_rectangle));
    }

    @Test
    @DisplayName("Base_rectangle should not find intersections with noIntersection_rectangle")
    void notFindIntersections() {
        List<Point> points = new ArrayList<>();
        assertEquals(points,base_rectangle.findIntersections(noIntersection_rectangle));
    }

    @Test
    @DisplayName("Base_rectangle should not contains noIntersection_rectangle")
    void notContains() {
        assertEquals(false,base_rectangle.contains(noIntersection_rectangle));
    }

    @Test
    @DisplayName("Base_rectangle should not be adjacent to noIntersection_rectangle")
    void isNotAdjacent() {
        assertEquals(false,base_rectangle.isAdjacent(noIntersection_rectangle));
    }

    @Test
    @DisplayName("Base_rectangle should  contains contained_rectangle")
    void contains() {
        assertEquals(true, base_rectangle.contains(contained_rectangle));
    }

    @Test
    @DisplayName("Base_rectangle should be adjacent to adjacent_rectangle")
    void isAdjacent() {
        assertEquals(true,base_rectangle.isAdjacent(adjacent_rectangle));
    }
}