import java.util.ArrayList;

/**
 * A játékban található aktív elemek (forrás, ciszterna, cső) közös
 * tulajdonságait egyesítő absztrakt ősosztály.
 * 
 */
public abstract class Active extends Field
{
	/**
	 * a csövek, amikkel össze van kötve.
	 * 
	 */
	protected ArrayList<Pipe> connectedPipes;

	/**
	 * Konstruktor
	 * 
	 */
	public Active()
	{
		connectedPipes = new ArrayList<>();
	}

	public abstract void Step();

	/**
	 * Ha kapcsolt cso akkor neighbour
	 * 
	 * @param f a vizsgalando mezo amin nezzuk hogy szomszed-e
	 * @return boolean
	 * 
	 */
	public boolean IsNeighbour(Field f)
	{
		return connectedPipes.contains(f);
	}

	/**
	 * egy adott csovet elvesz es egy masikat lerak Lehet kell modositani amiatt h
	 * vizsgalja h vannak e rajta
	 * 
	 * @param oldPipe a lecserelendo cso
	 * @param newPipe az uj cso
	 * @return int
	 * 
	 */
	public int ReplacePipe(Pipe oldPipe, Pipe newPipe)
	{
		if (connectedPipes.remove(oldPipe))
		{
			connectedPipes.add(newPipe);
			System.out.println("Active: Sikeres csere");
			return 0;
		}
		else
		{
			System.out.println("Active: Nincs ilyen regi cso");
			return 2;
		}
	}

	/**
	 * Beallitja a kapcsolatot mindket elemen Ha sikertelen akkor hibauzenet
	 * 
	 */
	public int ConnectPipe(Pipe pipe)
	{
		int error = pipe.ConnectActive(this);
		if (error == 0)
		{
			connectedPipes.add(pipe);
			System.out.println("Active: Sikeres felkapcsolas");
			return 0;
		}
		else
		{
			System.out.println("Active: Sikertelen cso beallitas");
			return error;
		}
	}

	/**
	 * a megadott csövet lecsatlakoztatja. Csak ha szomszéd Hiba esetén jelez
	 * 
	 */
	public int DisconnectPipe(Pipe pipe)
	{
		if (IsNeighbour(pipe))
		{
			int error = pipe.DisconnectActive(this);
			if (error == 0)
			{
				connectedPipes.remove(pipe);
				System.out.println("Active: Sikeres lekapcsolas");
				return 0;
			}
			else
			{
				System.out.println("Active: Sikertelen cso beallitas");
				return error;
			}
		}
		// Nem szomszedos csore lett hivva
		System.out.println("Active: Nem szomszedos cso");
		return 2;
	}
}
