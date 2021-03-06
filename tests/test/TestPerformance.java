package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import problem.Point;
import problem.Shapeshifter;

public class TestPerformance extends AbstractTest
{
	private static final int gridSize = 10000;
	private static final int numShapes = 10;
	private static final int minShapeSize = 1000;
	private static final int maxShapeSize = 10000;
	private static final int iterations = 2;

	private DebugGrid generateGrid()
	{
		DebugGrid grid = new DebugGrid(gridSize);
		generateShapes(grid, numShapes, minShapeSize, maxShapeSize);
		return grid;
	}

	@Test
	public void testPerformance() throws Exception
	{
		List<DebugGrid> grids = new ArrayList<>();
		for (int i=0; i<iterations; i++)
		{
			grids.add(generateGrid());
		}

		long time = System.nanoTime();
		for (DebugGrid grid : grids)
		{
			Shapeshifter shapeshifter = createShapeshifter();
			Set<Point> shape = shapeshifter.findLargestShape(grid);
			assertNotNull(shape);
			assertEquals(maxShapeSize, shape.size());
		}
		time = (System.nanoTime() - time) / 1000000;
		debug(iterations + " iterations took " + time + " millis");
		debug("avg of " + (time / iterations) + " millis per iteration");
	}
}
