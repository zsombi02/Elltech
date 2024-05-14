/**
 * Az osztály a nomádok csapatának példányait reprezentálja. A nomádok ki tudják
 * lyukasztani a csöveket és át tudják állítani a pumpákat.
 * 
 */
public class Saboteur extends Player
{
	public Saboteur(Field position)
	{
		super(position);
	}

	/**
	 * Pumpa input output irányának beállítása
	 * 
	 * @param input  a bemeneti csó
	 * @param output a kimeneti cső
	 * @return int
	 * 
	 */
	public int SetPumpDirection(Pipe input, Pipe output)
	{
		if (position.SetPumpDirection(input, output) == 0)
		{
			System.out.println("Saboteur: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Saboteur: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * A cuccos elrontasa, csak akkor ha ronthato
	 * 
	 */
	public int Damage()
	{
		if (position.Damage() == 0)
		{
			System.out.println("Saboteur: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Saboteur: Sikertelen lepes");
			return -1;
		}
	}
}
