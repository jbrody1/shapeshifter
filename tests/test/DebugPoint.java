package test;
import problem.Point;

public class DebugPoint extends Point
{
	public final char display;
	
	public DebugPoint(char display, int x, int y)
	{
		super(x, y);
		this.display = display;
	}

	public DebugPoint(int x, int y)
	{
		this('X', x, y);
	}

	@Override
	public String toString()
	{
		return display + ' ' + super.toString();
	}

	@Override
	public char getDisplay()
	{
		return display;
	}

	public Point move(Point delta)
	{
		return new DebugPoint(display, x + delta.x, y + delta.y);
	}
}
