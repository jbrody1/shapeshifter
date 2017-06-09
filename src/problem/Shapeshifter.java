package problem;

import java.util.Set;

public abstract class Shapeshifter
{
	protected final Grid grid;

	protected Shapeshifter(Grid grid)
	{
		this.grid = grid;
	}

	public abstract Set<Set<Point>> findShapes() throws Exception;

	public abstract Set<Point> findLargestShape() throws Exception;
}
