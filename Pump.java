import java.util.Random;

/**
 * A pálya egyik aktív eleme a pumpa. A pumpák kötik össze a csöveket, tárolnak
 * vizet, pumpálják át a vizet a csöveken keresztül egymásba. Új pumpát
 * beszerelni a szerelők tudnak, pumpát átállítani mindkét csapat tud.
 * 
 */
public class Pump extends Active
{
	protected int waterLevel;
	protected int maxWaterLevel;
	protected int maxConnectedPipes;
	protected boolean isDamaged;
	protected Pipe input;
	protected Pipe output;

	public Pump(int maxConnectedPipes)
	{
		waterLevel = 0;
		maxWaterLevel = 50;
		this.maxConnectedPipes = maxConnectedPipes;
		isDamaged = false;
	}

	public void Step()
	{

		PushWater();
		PullWater();
		// TrytoDamage();
	}

	/**
	 * Tolja a vizet
	 * 
	 */
	protected void PushWater()
	{
		if (!isDamaged && output != null)
		{
			waterLevel -= output.RecieveWater(waterLevel);
			System.out.println("Pump: Sikeres nyomas");
		}
	}

	/**
	 * Szivja a vizet
	 * 
	 */
	protected void PullWater()
	{
		if (!isDamaged && input != null)
		{
			waterLevel += input.TransmitWater(maxWaterLevel - waterLevel);
			System.out.println("Pump: Sikeres szivas");
		}
	}

	/**
	 * itt tortenik meg az iranyok beallitasa ha szomszedok
	 * 
	 * @param input  a bementi irany
	 * @param output a kimeneti irany
	 * @return int
	 * 
	 */
	public int SetPumpDirection(Pipe input, Pipe output)
	{
		if (IsNeighbour(input) && IsNeighbour(output))
		{
			this.input = input;
			this.output = output;
			System.out.println("Pump: Sikeres beallitas");
			return 0;
		}
		else
		{
			System.out.println("Pump: Nem megfelelo parameterek");
			return 2;
		}
	}

	/**
	 * A pumpa megjavítasa
	 * 
	 */
	public int Repair()
	{
		if (isDamaged == true)
		{
			isDamaged = false;
			System.out.println("Pump: Sikeres javitas");
			return 0;
		}
		else
		{
			System.out.println("Pump: Mar meg van javitva");
			return 2;
		}
	}

	/**
	 * Ha lehet akkor elrontja
	 * 
	 */
	public int TrytoDamage()
	{
		/*
		 * if (new Random().nextDouble() < 0.2) {
		 */
		if (isDamaged == false)
		{
			System.out.println("Pump: Sikeres rongalas");
			isDamaged = true;
			return 0;
		}
		else
		{
			System.out.println("Pump: Mar meg van rongalva");
			return 3;
		}
		/*
		 * } else { System.out.println("Pump: Sikertelen rongalas"); return 2; }
		 */
	}

	/**
	 * Hozzakapcsol egy csovet a pumpahoz
	 * 
	 * @param pipe a kapcsolando pumpa
	 * @return int
	 * 
	 */
	public int ConnectPipe(Pipe pipe)
	{
		if (connectedPipes.size() < maxConnectedPipes - 1)
		{
			return super.ConnectPipe(pipe);
		}
		else
		{
			System.out.println("Pump: Nincs tobb szabad hely");
			return 4;
		}
	}

	/**
	 * Lekapcsol egy pumpat
	 * 
	 * @param pipe a lekapcsolando pumpa
	 * @return int
	 * 
	 */
	public int DisconnectPipe(Pipe pipe)
	{
		int error = super.DisconnectPipe(pipe);
		if (error == 0)
		{
			if (pipe == input)
			{
				input = null;
			}
			if (pipe == output)
			{
				output = null;
			}
			return 0;
		}
		else
		{
			return error;
		}
	}
}
