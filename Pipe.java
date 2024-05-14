import java.util.ArrayList;

/**
 * Ez az osztály a cső. A játék során a cső nem szolgál víztárolóként, ezért ez
 * nem egy aktív elem. A csöveken mozognak a játékosok, egy csövön egyszerre
 * csak egy karakter állhat. Egy csövet egy szerelő bármikor elvághat egy új
 * pumpa elhelyezése céljából, mely művelet után a lehelyezett pumpában már
 * képes lesz víz gyűjtésére. A szabotőrök kilyukaszthatnak egy csövet, a lyukas
 * csöveket azt a szerelők befoltozhatják.
 * 
 */
public class Pipe extends Field
{
	protected static Game game;

	/**
	 * @param game a jatek entitas az elfolyashoz
	 * 
	 */
	public static void SetGame(Game game)
	{
		Pipe.game = game;
	}

	/**
	 * A vizszinteket es allapotot jelzo valtozok
	 * 
	 */
	protected int waterLevel;
	protected int maxWaterLevel;
	protected boolean isDamaged;
	protected ArrayList<Active> ends;

	public Pipe()
	{
		waterLevel = 0;
		maxWaterLevel = 15;
		isDamaged = false;
		ends = new ArrayList<>();
	}

	/**
	 * A kifolyo vizbol pontot ad
	 * 
	 */
	protected void SpillWater()
	{
		game.AddSaboteurPoint(waterLevel);
		waterLevel = 0;
		System.out.println("Pipe: Kifolyt víz");
	}

	/**
	 * @param quantity Az atszallitando viz mennyisege
	 * @return int
	 * 
	 */
	public int TransmitWater(int quantity)
	{
		int transmitted = Integer.min(quantity, waterLevel);
		waterLevel -= transmitted;
		System.out.println("Pipe: " + transmitted + " viz atadva, vizszint: " + waterLevel);
		return transmitted;
	}

	/**
	 * @param quantity a fogadando viz mennyisege
	 * @return int
	 * 
	 */
	public int RecieveWater(int quantity)
	{
		int recieved = Integer.min(quantity, maxWaterLevel - waterLevel);
		waterLevel += recieved;
		if (isDamaged || IsLoose())
		{
			SpillWater();
		}
		System.out.println("Pipe: " + recieved + " viz atveve, vizszint: " + waterLevel);
		return recieved;
	}

	/**
	 * Akkor szomszed ha az egyik vege
	 * 
	 */
	public boolean IsNeighbour(Field f)
	{
		return ends.contains(f);
	}

	/**
	 * Ha ures a players akkor jo
	 * 
	 */
	protected boolean IsFree()
	{
		return players.size() == 0;
	}

	/**
	 * Jelenleg akkor Loose ha nincs mind a 2 vege beallitva
	 * 
	 */
	protected boolean IsLoose()
	{
		return ends.size() < 2;
	}

	/**
	 * Felkapcsol egy aktiv mezot. Akkor tudjuk hozzadugni ha meg nincs 2 vege
	 * beallitva
	 * 
	 * @param a a kapcsolando aktiv mezo
	 * @return int
	 * 
	 */
	public int ConnectActive(Active a)
	{
		if (IsLoose())
		{
			if (!ends.contains(a))
			{
				ends.add(a);
				System.out.println("Pipe: Sikeres felkapcsolas");
				return 0;
			}
			else
			{
				System.out.println("Pipe: Mar felkapcsolt aktiv elem");
				return 3;
			}
		}
		else
		{
			System.out.println("Pipe: Mindket veg foglalt");
			return 2;
		}
	}

	/**
	 * Lekapcsol egy aktiv mezot. Lekoti a kert aktivot, csak sajat mezore hivodik
	 * 
	 * @param a a kapcsolando aktiv mezo
	 * @return int
	 * 
	 */
	public int DisconnectActive(Active a)
	{
		if (!IsLoose())
		{
			if (IsFree())
			{
				if (ends.remove(a))
				{
					SpillWater();
					System.out.println("Pipe: Sikeres lekapcsolas");
					return 0;
				}
				else
				{
					System.out.println("Pipe: Nincs ilyen veg");
					return 4;
				}
			}
			else
			{
				System.out.println("Pipe: Foglalt cso");
				return 3;
			}
		}
		else
		{
			System.out.println("Pipe: Lelogo cso");
			return 2;
		}
	}

	/**
	 * A csore helyez egy jatekost
	 * 
	 * @param player a csore adando jatekos
	 * @return int
	 * 
	 */
	public int AddPlayer(Player player)
	{
		if (!IsLoose())
		{
			if (IsFree())
			{
				players.add(player);
				System.out.println("Pipe: Sikeres hozzadas");
				return 0;
			}
			else
			{
				System.out.println("Pipe: Foglalt cso");
				return 3;
			}
		}
		else
		{
			System.out.println("Pipe: Lelogo cso");
			return 2;
		}
	}

	public Pipe GetNeighbourPipeReference(Field field)
	{
		return IsNeighbour(field) ? this : null;
	}

	/**
	 * Megjavitja ha meg lehet
	 * 
	 */
	public int Repair()
	{
		if (isDamaged == true)
		{
			isDamaged = false;
			System.out.println("Pipe: Sikeres javitas");
			return 0;
		}
		else
		{
			System.out.println("Pipe: Mar meg van javitva");
			return 2;
		}
	}

	/**
	 * Elrontja ha lehet
	 * 
	 */
	public int Damage()
	{
		if (isDamaged == false)
		{
			isDamaged = true;
			SpillWater();
			System.out.println("Pipe: Sikeres rongalas");
			return 0;
		}
		else
		{
			System.out.println("Pipe: Mar meg van rongalva");
			return 2;
		}
	}

	/**
	 * Lehelyez egy pumpat a cso mezore. Megjvaitja a mezot, majd letrehoz egy uj
	 * csovet, es ballitja a vegeket ezeknek megfeleloen mindharom entitason
	 * 
	 * @param pump A lehelyezendo pumpa
	 * @return int
	 * 
	 */
	public int PlacePump(Pump pump)
	{
		Repair();
		Pipe newPipe = new Pipe();
		ends.get(0).ReplacePipe(this, newPipe);
		newPipe.ConnectActive(ends.get(0));
		ends.remove(0);
		pump.ConnectPipe(this);
		pump.ConnectPipe(newPipe);
		System.out.println("Pipe: Sikeres pumpa lehelyezes");
		return 0;
	}
}
