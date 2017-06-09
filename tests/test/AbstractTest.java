package test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import glint.SetShape;
import glint.Shape;
import problem.Grid;
import problem.Point;
import problem.Shapeshifter;
import solution.MyShapeshifter;

public abstract class AbstractTest
{
	public Shapeshifter createShapeshifter(Grid grid)
	{
		return new MyShapeshifter(grid);
	}

	public void testShapeGeneration() throws IOException
	{
		DebugGrid grid = new DebugGrid(10000);
		Set<Shape> shapes = generateShapes(grid, 10, 10, 100);
		debug(grid);
		assertEquals(10, shapes.size());
	}

	protected Set<Shape> generateShapes(DebugGrid grid, int numShapes, int minSize, int maxSize)
	{
		Set<Shape> shapes = new HashSet<>();
		if (numShapes <= 0) return shapes;

		minSize = Math.max(minSize, 1);
		maxSize = Math.max(minSize, maxSize);

		int size = maxSize;
		int step = (maxSize - minSize) / (numShapes-1);

		List<Point> points = new ArrayList<>();
		for (int i=numShapes; i>0; i--)
		{
			char display = Character.forDigit(i, 10);
			Shape shape = generateShape(grid, display, size);
			shapes.add(shape);
			points.addAll(shape.getPoints());
			size -= step;
		}
		// randomize point order before adding to grid, just in case
		Collections.shuffle(points);
		grid.addAll(points);
		return shapes;
	}

	private Shape generateShape(DebugGrid grid, char display, int size)
	{
		Point point;
		// try 5 times before giving up
		for (int i=0; i<5; i++)
		{
			//point = new TestPoint(display, (int)(Math.random() * grid.size()), (int)(Math.random() * grid.size()));
			point = new Point((int)(Math.random() * grid.size()), (int)(Math.random() * grid.size()));
			SetShape shape = new SetShape();
			if (generateShape(grid, point, shape, display, size)) return shape;
		}
		throw new RuntimeException("unable to generate shape " + display);
	}

	private boolean generateShape(DebugGrid grid, Point point, SetShape shape, char display, int size)
	{
		if (grid.contains(point) || shape.contains(point)) return false;

		Set<Point> adjacent = getAdjacentPoints(point, grid);
		adjacent.removeAll(shape.getPoints());
		if (grid.containsAny(adjacent)) return false;

		//debug("adding point " + point + " to shape " + display);
		shape.add(point);
		if (shape.size() == size) return true;

		List<Point> list = new ArrayList<>(adjacent);
		int start = (int) (list.size() * Math.random());
		for (int i=0; i<list.size(); i++)
		{
			Point next = list.get((start + i) % list.size());
			if (generateShape(grid, next, shape, display, size)) return true;
		}
		return false;
	}

	private static Set<Point> getAdjacentPoints(Point point, Grid grid)
	{
		Set<Point> adjacent = point.getAdjacentPoints();
		Set<Point> points = new HashSet<>(adjacent.size(), 1);
		for (Point p : adjacent)
		{
			if (p.x >= 0 && p.x < grid.size() &&
				p.y >= 0 && p.y < grid.size())
			{
				points.add(p);
			}
		}
		return points;
	}

	public void debug(Object o)
	{
		System.out.println(o);
	}

	public void debug(Grid grid) throws IOException
	{
		grid.print(System.out);
	}

	public void debug(Set<Point> points) throws IOException
	{
		debug(new SetShape(points.toArray(new Point[0])));
	}

	public void debug(Shape shape) throws IOException
	{
		shape.print(System.out);
	}
}