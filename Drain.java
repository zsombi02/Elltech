/**
 * A városokban található ciszterna. A szerelők csapata ide gyűjti a vizet. Egy
 * speciális példánya jelképezi a homokot. Ez a példány tartja nyilván a
 * szabotőrök által gyűjtött vizet.
 * 
 */
public class Drain extends Active
{
	protected static Game game;

	/**
	 * a közös Drain beállítása
	 * 
	 * @param game a jatek entitasa
	 * 
	 */
	public static void SetGame(Game game)
	{
		Drain.game = game;
	}

	/**
	 * Vízfolyam irányítása
	 * 
	 */
	public void Step()
	{
		PullWater();
	}

	/**
	 * Pontszámító, a befolyo vizet szamolja
	 * 
	 */
	protected void PullWater()
	{
		for (Pipe pipe : connectedPipes)
		{
			game.AddMechanicPoint(pipe.TransmitWater(Integer.MAX_VALUE));
		}
		System.out.println("Drain: Az osszes kapcsolt cso leszivva");
	}

	/**
	 * akkor hívódik meg, ha egy szerelő felvesz egy csövet
	 * 
	 * @return Pipe A felvett csovet adja vissza parameterkent
	 * 
	 */
	public Pipe PickUpPipe()
	{
		Pipe newPipe = new Pipe();
		ConnectPipe(newPipe);
		System.out.println("Drain: Sikeres cso felvetel");
		return newPipe;
	}

	/**
	 * akkor hívódik meg, ha egy szerelő felvesz egy pumpát
	 * 
	 * @return Pump a felvett pumpa
	 * 
	 */
	public Pump PickUpPump()
	{
		System.out.println("Drain: Sikeres pumpa felvetel");
		return new Pump(5);
	}
}
