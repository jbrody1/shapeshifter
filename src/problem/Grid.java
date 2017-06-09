package problem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

	public Set<Point> getAll()
	{
		return Collections.unmodifiableSet(points);
	}

	public boolean contains(Point point)
	{
		return points.contains(point);
	}

	public int size()
	{
		return size;
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
		print(out, getAll(), new Point(0, 0), new Point(size()-1, size()-1));
	}

	public static void print(OutputStream out, Set<Point> points, Point topLeft, Point bottomRight) throws IOException
	{
		if (points.size() < 10000)
		{
			OutputStreamWriter writer = new OutputStreamWriter(out);
			SortedSet<Point> sorted = new TreeSet<>(points);
			
			Iterator<Point> i = sorted.iterator();
			Point point = i.hasNext() ? i.next() : null;
			for (int y=topLeft.y; y<=bottomRight.y; y++)
			{
				for (int x=topLeft.x; x<=bottomRight.x; x++)
				{
					if (point != null && point.x == x && point.y == y)
					{
						writer.write(point.getDisplay());
						point = i.hasNext() ? i.next() : null;
					}
					else
					{
						writer.write(' ');
					}
				}
				writer.write('\n');
			}
			writer.flush();
		}
	}
}

