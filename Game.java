/**
 * A játék elemeit (mezők és játékosok) és lefutásának legfontosabb függvényeit
 * tartalmazza.
 * 
 */
public class Game
{
	/**
	 * A pontokat játékosokat és aktívokat tároló paraméterek
	 * 
	 */
	private int mechanicPoints;
	private int saboteurPoints;
	protected Mechanic mechanics;
	protected Saboteur saboteurs;
	protected Active actives;

	public Game()
	{
		mechanicPoints = 0;
		saboteurPoints = 0;
	}

	/**
	 * A mechanic csapatnak pontot ad
	 * 
	 * @param points a hozzaadando pontok
	 * 
	 */
	public void AddMechanicPoint(int points)
	{
		System.out.println("Game: " + points + " pont a Griffendelnek");
		mechanicPoints += points;
	}

	/**
	 * A saboteur csapatnak pontot ad
	 * 
	 */
	public void AddSaboteurPoint(int points)
	{
		System.out.println("Game: " + points + " pont a Mardekarnak");
		saboteurPoints += points;
	}

	public void MechanicRound()
	{
	}

	public void SabouteurRound()
	{
	}
}
