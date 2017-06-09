package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import problem.Grid;
import problem.Point;

public class DebugShape
{
	private final Set<Point> points = new HashSet<>();

	public DebugShape(Point...points)
	{
		for (Point point : points) this.points.add(point);
	}

	public Set<Point> getPoints()
	{
		return Collections.unmodifiableSet(points);
	}

	public void add(Point point)
	{
		points.add(point);
	}

	public boolean contains(Point point)
	{
		return points.contains(point);
	}

	public int size()
	{
		return points.size();
	}

	public String toString()
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			print(out);
			return new String(out.toByteArray());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void print(OutputStream out) throws IOException
	{
		Set<Point> points = getPoints();
		Point topLeft = computeTopLeft(points);
		Point bottomRight = computeBottomRight(points);
		Grid.print(out, points, topLeft, bottomRight);
	}

	static Point computeTopLeft(Set<Point> points)
	{
		if (points.isEmpty()) return null;
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
		for (Point point : points)
		{
			if (point.x < x) x = point.x;
			if (point.y < y) y = point.y;
		}
		return new Point(x, y);
	}

	static Point computeBottomRight(Set<Point> points)
	{
		if (points.isEmpty()) return null;
		int x = Integer.MIN_VALUE;
		int y = Integer.MIN_VALUE;
		for (Point point : points)
		{
			if (point.x > x) x = point.x;
			if (point.y > y) y = point.y;
		}
		return new Point(x, y);
	}
}
