package problem;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Point implements Comparable<Point>
{
	// immutable
	public final int x;
	public final int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Point move(Point delta)
	{
		return new Point(x + delta.x, y + delta.y);
	}

	@Override
	public int hashCode()
	{
        return x * 31 + y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Point)
		{
			Point other = (Point) obj;
			return other.x == x && other.y == y;
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(Point point)
	{
		if (y == point.y) return Integer.compare(x, point.x);
		else return Integer.compare(y, point.y);
	}

	@Override
	public String toString()
	{
		return "[" + y + "," + x + "]";
	}

	public char getDisplay()
	{
		return 'X';
	}

	private static final Set<Point> adjacency = Collections.unmodifiableSet(new HashSet<Point>(Arrays.asList(
			new Point(-1, 0),
			new Point(0, -1),
			new Point(1, 0),
			new Point(0, 1)
			/*
			, // for supporting diagonal adjacency
			new Point(-1, -1),
			new Point(-1, 1),
			new Point(1, -1),
			new Point(1, 1)
			*/
	)));

	public Set<Point> getAdjacentPoints()
	{
		Set<Point> points = new HashSet<>(adjacency.size(), 1);
		for (Point delta : adjacency) points.add(move(delta));
		return points;
	}
}
