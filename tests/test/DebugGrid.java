package test;

import java.util.Collection;

import problem.Grid;
import problem.Point;

public class DebugGrid extends Grid
{
	public DebugGrid(int size)
	{
		super(size);
	}

	public boolean containsAny(Collection<Point> points)
	{
		for (Point point : points)
		{
			if (contains(point))
			{
				return true;
			}
		}
		return false;
	}
}

