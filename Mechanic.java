import java.util.ArrayList;

/**
 * Az osztály a szerelők csapatának példányait reprezentálja. A szerelők meg
 * tudják javítani a pumpákat és a csöveket, el tudják utóbbit vágni, hogy új
 * pumpákkal bővítsék a pályát, illetve át is tudják helyezni a végeit.
 * 
 */
public class Mechanic extends Player
{
	protected ArrayList<Pipe> pipes;
	protected ArrayList<Pump> pumps;

	public Mechanic(Field position)
	{
		super(position);
		pipes = new ArrayList<>();
		pumps = new ArrayList<>();
	}

	/**
	 * @param pipe a hozzáadandó cső
	 * 
	 */
	public void Add(Pipe pipe)
	{
		pipes.add(pipe);
	}

	/**
	 * @param pump a hozzáadandó pumpa
	 * 
	 */
	public void Add(Pump pump)
	{
		pumps.add(pump);
	}

	/**
	 * @param pipe az eltávolítandó pumpa
	 * 
	 */
	public void Remove(Pipe pipe)
	{
		pipes.remove(pipe);
	}

	/**
	 * @param pump az eltávolítandó pumpa
	 * 
	 */
	public void Remove(Pump pump)
	{
		pumps.remove(pump);
	}

	/**
	 * A pumpa folyási irányának beállítása
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
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * A javítást megkezdő fgv
	 * 
	 * @return int
	 * 
	 */
	public int Repair()
	{
		if (position.Repair() == 0)
		{
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * Ha van olyan csove amire meghivodik akkor csak
	 * 
	 * @param pipe a hozzákapcsolandó cső
	 * @return int
	 * 
	 */
	public int ConnectPipe(Pipe pipe)
	{
		if (position.ConnectPipe(pipe) == 0)
		{
			pipes.remove(pipe);
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * Ha a mezon all akkor lekapcsolja a szomszedos csovet
	 * 
	 * @param pipe a lekapcsolandó cső
	 * @return int
	 * 
	 */
	public int DisconnectPipe(Pipe pipe)
	{
		if (position.DisconnectPipe(pipe) == 0)
		{
			pipes.add(pipe);
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * felvesz csovet akkor ha drainen allt
	 * 
	 * @return int
	 * 
	 */
	public int PickUpPipe()
	{
		Pipe newPipe = position.PickUpPipe();
		if (newPipe != null)
		{
			pipes.add(newPipe);
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * A csovon amin all,megjavitja, lehelyez egy pumpat, majd raall a pumpara
	 * 
	 * @param pump a lerakando pumpa
	 * @return int
	 * 
	 */
	public int PlacePump(Pump pump)
	{
		if (position.PlacePump(pump) == 0)
		{
			pumps.remove(pump);
			Move(pump);
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}

	/**
	 * felvesz pumpat ha drain
	 * 
	 */
	public int PickUpPump()
	{
		Pump newPump = position.PickUpPump();
		if (newPump != null)
		{
			pumps.add(newPump);
			System.out.println("Mechanic: Sikeres lepes");
			return 0;
		}
		else
		{
			System.out.println("Mechanic: Sikertelen lepes");
			return -1;
		}
	}
}
