import java.io.*;

/**
 * A program teszteléséért felelős osztály, itt van megvalósítva A skeleton
 * menü, és a függvényhívások a teszt fájlból Itt van a Main metódus is ez a
 * program belépési pontja
 * 
 */
public class Skeleton
{
	public static void PrintTestCases()
	{
		System.out.println(" 1 - MoveFreePipeMechanic");
		System.out.println(" 2 - MoveOccupiedPipeMechanic");
		System.out.println(" 3 - MoveActiveMechanic");
		System.out.println(" 4 - MoveFreePipeSaboteur");
		System.out.println(" 5 - MoveOccupiedPipeSaboteur");
		System.out.println(" 6 - MoveActiveSaboteur");
		System.out.println(" 7 - PipeDisconnectConnect");
		System.out.println(" 8 - PipeDamage");
		System.out.println(" 9 - PipeRepair");
		System.out.println("10 - PumpDamage");
		System.out.println("11 - PumpRepair");
		System.out.println("12 - PumpPlace");
		System.out.println("13 - PumpSetDirection");
		System.out.println("14 - DrainPickUpPipe");
		System.out.println("15 - DrainPickUpPump");
		System.out.println("16 - WaterPump");
		System.out.println("17 - WaterDamagedPump");
		System.out.println("18 - WaterFullPipe");
		System.out.println("19 - WaterEmptyPipe");
		System.out.println("20 - WaterToDamagedPipe");
		System.out.println("21 - WaterFromDamagedPipe");
		System.out.println("22 - WaterDrain");
		System.out.println("23 - WaterSource");
		System.out.println("24 - WaterLoosePipe");
	}

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		boolean exit = false;
		PrintTestCases();
		while (!exit)
		{
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("");
			System.out.print("Kivalasztott teszteset: ");
			int option = Integer.parseInt(console.readLine());

			switch (option)
			{
			case 1:
			{
				Test.MoveFreePipeMechanic();
				break;
			}
			case 2:
			{
				Test.MoveOccupiedPipeMechanic();
				break;
			}
			case 3:
			{
				Test.MoveActiveMechanic();
				break;
			}
			case 4:
			{
				Test.MoveFreePipeSaboteur();
				break;
			}
			case 5:
			{
				Test.MoveOccupiedPipeSaboteur();
				break;
			}
			case 6:
			{
				Test.MoveActiveSaboteur();
				break;
			}
			case 7:
			{
				Test.PipeDisconnectConnect();
				break;
			}
			case 8:
			{
				Test.PipeDamage();
				break;
			}
			case 9:
			{
				Test.PipeRepair();
				break;
			}
			case 10:
			{
				Test.PumpDamage();
				break;
			}
			case 11:
			{
				Test.PumpRepair();
				break;
			}
			case 12:
			{
				Test.PumpPlace();
				break;
			}
			case 13:
			{
				Test.PumpSetDirection();
				break;
			}
			case 14:
			{
				Test.DrainPickUpPipe();
				break;
			}
			case 15:
			{
				Test.DrainPickUpPump();
				break;
			}
			case 16:
			{
				Test.WaterPump();
				break;
			}
			case 17:
			{
				Test.WaterDamagedPump();
				break;
			}
			case 18:
			{
				Test.WaterFullPipe();
				break;
			}
			case 19:
			{
				Test.WaterEmptyPipe();
				break;
			}
			case 20:
			{
				Test.WaterToDamagedPipe();
				break;
			}
			case 21:
			{
				Test.WaterFromDamagedPipe();
				break;
			}
			case 22:
			{
				Test.WaterDrain();
				break;
			}
			case 23:
			{
				Test.WaterSource();
				break;
			}
			case 24:
			{
				Test.WaterLoosePipe();
				break;
			}
			case 0:
			{
				exit = true;
				continue;
			}
			default:
			{
				System.out.println("Ervenytelen ertek!");
				continue;
			}
			}
			console.readLine();
			PrintTestCases();
		}

		/*
		 * A demo és legelső skeleton tesztelések megvalósítva, szzükség eseti debugolás
		 * céljából benne hagytuk kikommentelve Figyelmen kívül hagyandó az ellenőrzés
		 * alatt System.out.println("Hello, World!"); Pump p = new Pump(5); Drain d =
		 * new Drain(); Pipe pipe = new Pipe(); Pipe pip2 = new Pipe(); Pipe pipe3 = new
		 * Pipe(); Source source = new Source(); Mechanic tester = new Mechanic(d);
		 * 
		 * source.ConnectPipe(pip2); p.ConnectPipe(pipe); p.ConnectPipe(pip2);
		 * d.ConnectPipe(pipe); d.AddPlayer(tester); pipe.ConnectActive(d);
		 * pipe.ConnectActive(p); pip2.isDamaged = true; p.ConnectPipe(pipe3);
		 * d.ConnectPipe(pipe3);
		 * 
		 * tester.Move(pipe); tester.Move(p); tester.SetPumpDirection(pipe, pip2);
		 * tester.Move(pip2); tester.Repair(); tester.Move(p);
		 * tester.SetPumpDirection(pipe, pipe3); tester.DisconnectPipe(pip2);
		 * tester.Move(pipe); tester.Move(d); tester.PickUpPump(); tester.PickUpPipe();
		 * tester.ConnectPipe(pip2); tester.ConnectPipe(tester.pipes.get(0));
		 * tester.Move(pipe); tester.Move(p); tester.ConnectPipe(tester.pipes.get(0));
		 * 
		 * // Saboteur tester2 = new Saboteur(); // tester2.SetPosition(pipe); //
		 * pipe.AddPlayer(tester2); // tester.PickUpPump(); tester.Move(pipe); //
		 * tester2.Damage(); tester.PlacePump(tester.pumps.get(0));
		 */
		System.out.println("Vege");

	}
}
