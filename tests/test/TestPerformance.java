package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import problem.Grid;
import problem.Point;
import problem.Shapeshifter;

public class TestPerformance extends AbstractTest
{
	private static final int gridSize = 1000000;
	private static final int numShapes = 100;
	private static final int minShapeSize = 10;
	private static final int maxShapeSize = 1000;
	private static final int iterations = 200;

	private Grid generateGrid()
	{
		DebugGrid grid = new DebugGrid(gridSize);
		generateShapes(grid, numShapes, minShapeSize, maxShapeSize);
		return grid;
	}

	@Test
	public void testPerformance() throws Exception
	{
		List<Grid> grids = new ArrayList<>();
		for (int i=0; i<iterations; i++)
		{
			grids.add(generateGrid());
		}

		long time = System.nanoTime();
		for (Grid grid : grids)
		{
			Shapeshifter shapeshifter = createShapeshifter(grid);
			Set<Point> shape = shapeshifter.findLargestShape();
			assertNotNull(shape);
			assertEquals(maxShapeSize, shape.size());
		}
		time = (System.nanoTime() - time) / 1000000;
		debug(iterations + " iterations took " + time + " millis");
		debug("avg of " + (time / iterations) + " millis per iteration");
	}
}
