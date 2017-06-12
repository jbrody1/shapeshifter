package problem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Grid
{
	protected final int size;
	protected final Set<Point> points = new HashSet<Point>();

	public Grid(int size)
	{
		this.size = size;
	}

	public void add(Point point)
	{
		points.add(point);
	}

	public void addAll(Collection<Point> points)
	{
		for (Point point : points)
		{
			add(point);
		}
	}

	public boolean contains(int x, int y)
	{
		return contains(new Point(x, y));
	}

	public boolean contains(Point point)
	{
		return points.contains(point);
	}

	public int size()
	{
		return size;
	}
}
