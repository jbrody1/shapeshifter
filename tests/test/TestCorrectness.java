package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

import problem.Grid;
import problem.Point;
import problem.Shapeshifter;

public class TestCorrectness extends AbstractTest
{
	private static final int gridSize = 10000;
	private static final int numShapes = 10;
	private static final int minShapeSize = 10;
	private static final int maxShapeSize = 100;

	private Grid generateGrid(int maxShapeSize)
	{
		DebugGrid grid = new DebugGrid(gridSize);
		generateShapes(grid, numShapes, minShapeSize, maxShapeSize);
		return grid;
	}

	@Test
	public void testShapeDetection() throws Exception
	{
		int size = maxShapeSize + (int) (Math.random() * maxShapeSize);
		Grid grid = generateGrid(size);

		Shapeshifter shapeshifter = createShapeshifter(grid);
		Set<Set<Point>> found = shapeshifter.findShapes();
		assertNotNull(found);
		assertEquals(numShapes, found.size());
		
		Set<Point> shape = shapeshifter.findLargestShape();
		assertNotNull(shape);
		assertEquals(size, shape.size());
	}
}
