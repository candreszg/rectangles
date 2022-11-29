package geometry;

import java.util.List;

public abstract class Shape <T extends Shape> {

    public abstract List<Point> findIntersections(T anotherShape);

    public abstract boolean contains(T anotherShape);

    public abstract boolean isAdjacent(T anotherShape);

}
