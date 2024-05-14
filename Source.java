/**
 * A hegyekben található források. Innen származik a végtelen mennyiségű víz,
 * melyet a csapatok a játék során gyűjtenek.
 * 
 */
public class Source extends Active
{
	public void Step()
	{
		PushWater();
	}

	protected void PushWater()
	{
		for (Pipe pipe : connectedPipes)
		{
			pipe.RecieveWater(Integer.MAX_VALUE);
		}
		System.out.println("Source: Az osszes kapcsolt cso feltoltve");
	}
}
