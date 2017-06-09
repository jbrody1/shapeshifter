package problem;

import java.util.Set;

public interface Shapeshifter
{
	public Set<Set<Point>> findShapes(Set<Point> points) throws Exception;

	public default Set<Point> findLargestShape(Set<Point> points) throws Exception
	{
		Set<Set<Point>> shapes = findShapes(points);
		Set<Point> largest = null;
		int largestSize = 0;
		for (Set<Point> shape : shapes)
		{
			int size = shape.size();
			if (size > largestSize)
			{
				largest = shape;
				largestSize = size;
			}
		}
		return largest;
	}
}

