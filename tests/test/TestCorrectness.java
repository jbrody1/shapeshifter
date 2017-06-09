package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

import problem.Point;
import problem.Shapeshifter;

public class TestCorrectness extends AbstractTest
{
	private static final int gridSize = 10000;
	private static final int numShapes = 10;
	private static final int minShapeSize = 10;
	private static final int maxShapeSize = 100;

	private DebugGrid generateGrid(int maxShapeSize)
	{
		DebugGrid grid = new DebugGrid(gridSize);
		generateShapes(grid, numShapes, minShapeSize, maxShapeSize);
		return grid;
	}

	@Test
	public void testShapeDetection() throws Exception
	{
		int size = maxShapeSize + (int) (Math.random() * maxShapeSize);
		DebugGrid grid = generateGrid(size);

		Shapeshifter shapeshifter = createShapeshifter();
		Set<Set<Point>> found = shapeshifter.findShapes(grid.getAll(), grid.size());
		assertNotNull(found);
		assertEquals(numShapes, found.size());
		
		Set<Point> shape = shapeshifter.findLargestShape(grid.getAll(), grid.size());
		assertNotNull(shape);
		assertEquals(size, shape.size());
	}
}
