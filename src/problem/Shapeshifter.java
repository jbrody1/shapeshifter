package problem;

import java.util.Set;

public interface Shapeshifter
{
	public Set<Set<Point>> findShapes(Grid grid) throws Exception;

	public default Set<Point> findLargestShape(Grid grid) throws Exception
	{
		Set<Set<Point>> shapes = findShapes(grid);
		Set<Point> largest = null;
		int largestSize = 0;
		if (shapes != null)
		{
    		for (Set<Point> shape : shapes)
    		{
    			int size = shape.size();
    			if (size > largestSize)
    			{
    				largest = shape;
    				largestSize = size;
    			}
    		}
		}
		return largest;
	}
}
