import java.util.ArrayList;

/**
 * A játékpálya egy eleme/mezője. Ősosztálya az aktív elemeknek és a csőnek. Egy
 * játékos megkísérelhet rálépni. Tartalmaz alapértelmezett implemetnációkat
 * 
 */
public abstract class Field
{
	/**
	 * A mezőn álló játékosok
	 * 
	 */
	protected ArrayList<Player> players;

	public Field()
	{
		players = new ArrayList<>();
	}

	public abstract boolean IsNeighbour(Field f);

	/**
	 * @param player a hozzaadando jatekos
	 * @return int siker eseten 0
	 * 
	 */
	public int InitAddPlayer(Player player)
	{
		players.add(player);
		System.out.println("Field: Sikeres hozzaadas");
		return 0;
	}

	/**
	 * @param field
	 * @return Pipe
	 * 
	 */
	public Pipe GetNeighbourPipeReference(Field field)
	{
		return null;
	}

	/**
	 * @param player a hozzaadando jatekos
	 * 
	 */
	public int AddPlayer(Player player)
	{
		if (player.position.IsNeighbour(this))
		{
			players.add(player);
			System.out.println("Field: Sikeres hozzaadas");
			return 0;
		}
		else
		{
			System.out.println("Field: Nem szomszedos mezo");
			return 2;
		}
	}

	/**
	 * Ha rajta all a player akkor leveszi Siker esetén 0 visszatérés
	 * 
	 */
	public int RemovePlayer(Player player)
	{
		if (players.remove(player))
		{
			System.out.println("Field: Sikeres eltavolitas");
			return 0;
		}
		else
		{
			System.out.println("Field: Nincs ilyen jatekos");
			return 2;
		}
	}

	public int SetPumpDirection(Pipe input, Pipe output)
	{
		return 1;
	}

	public int Repair()
	{
		return 1;
	}

	public int Damage()
	{
		return 1;
	}

	public int ConnectPipe(Pipe pipe)
	{
		return 1;
	}

	public int DisconnectPipe(Pipe pipe)
	{
		return 1;
	}

	public Pipe PickUpPipe()
	{
		return null;
	}

	public int PlacePump(Pump pump)
	{
		return 1;
	}

	public Pump PickUpPump()
	{
		return null;
	}
}
