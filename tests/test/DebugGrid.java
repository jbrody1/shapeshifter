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

	public boolean contains(Point point)
	{
		return points.contains(point);
	}

	public boolean containsAny(Collection<Point> points)
	{
		for (Point point : points)
		{
			if (this.points.contains(point))
			{
				return true;
			}
		}
		return false;
	}
}
