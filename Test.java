import java.util.ArrayList;

/**
 * A tesztesetek megvalósításai A blokkok egy játéktér inicializálással
 * kezdődnek Ezt követően megtörténnek a szükséges lépések a lefutás érdekében
 * 
 */
public class Test
{
    public static void PrintInit()
    {
        System.out.println("");
        System.out.println("############################################");
        System.out.println("");
        System.out.println("Test: Init");
        System.out.println("");
    }

    public static void PrintTest()
    {
        System.out.println("");
        System.out.println("Kesz");
        System.out.println("");

        System.out.println("Test: Teszteset");
        System.out.println("");
    }

    public static void PrintFinish()
    {
        System.out.println("");
        System.out.println("Kesz");
        System.out.println("");
        System.out.println("############################################");
    }

    /**
     * sikeres mozgás üres csőre
     * 
     */
    public static void MoveFreePipeMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        mechanic.Move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikertelen mozgás foglalt csőre
     * 
     */
    public static void MoveOccupiedPipeMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(1));
        PrintTest();
        mechanic.Move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikeres mozgás aktív elemre
     * 
     */
    public static void MoveActiveMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(1));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        mechanic.Move(fields.get(2));
        PrintFinish();
    }

    /**
     * sikeres mozgás üres csőre
     * 
     */
    public static void MoveFreePipeSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        saboteur.Move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikertelen mozgás foglalt csőre
     * 
     */
    public static void MoveOccupiedPipeSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(1));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        saboteur.Move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikeres mozgás aktív elemre
     * 
     */
    public static void MoveActiveSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).ConnectPipe((Pipe) fields.get(1));
        fields.get(2).ConnectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(1));
        PrintTest();
        saboteur.Move(fields.get(0));
        PrintFinish();
    }

    /**
     * Lekapcsol egy csövet arrébb viszi, felkapcsolja máshova
     * 
     */
    public static void PipeDisconnectConnect()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.Move(pipe1);
        mechanic.Move(actives.get(1));
        mechanic.DisconnectPipe(pipe1);
        mechanic.Move(pipe2);
        mechanic.Move(actives.get(2));
        mechanic.ConnectPipe(pipe1);
        PrintFinish();
    }

    /**
     * Kilyukaszt egy csovet
     * 
     */
    public static void PipeDamage()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        Saboteur sab = new Saboteur(actives.get(0));
        PrintTest();
        sab.Move(pipe1);
        sab.Damage();
        PrintFinish();
    }

    /**
     * Megjavít egy csövet
     * 
     */
    public static void PipeRepair()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        Saboteur sab = new Saboteur(actives.get(0));
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        sab.Move(pipe1);
        sab.Damage();
        sab.Move(actives.get(1));
        mechanic.Move(pipe1);
        mechanic.Repair();
        PrintFinish();
    }

    /**
     * A rendszer elront egy pumpát
     * 
     */
    public static void PumpDamage()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        source.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe1);
        PrintTest();
        pump.TrytoDamage();
        PrintFinish();
    }

    /**
     * Megjavít egy pumpát
     * 
     */
    public static void PumpRepair()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        source.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe1);
        pump.TrytoDamage();
        Mechanic mechanic = new Mechanic(source);
        PrintTest();
        mechanic.Move(pipe1);
        mechanic.Move(pump);
        mechanic.Repair();
        PrintFinish();
    }

    /**
     * lehelyez egy pumpat a jatekos
     * 
     */
    public static void PumpPlace()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.PickUpPump();
        mechanic.Move(pipe1);
        mechanic.PlacePump(mechanic.pumps.get(0));
        PrintFinish();
    }

    /**
     * Beallitja a pumpa iranyat
     * 
     */
    public static void PumpSetDirection()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        Drain drain = new Drain();
        Pipe pipe2 = new Pipe();
        source.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe2);
        drain.ConnectPipe(pipe2);
        Mechanic mechanic = new Mechanic(source);
        PrintTest();
        mechanic.Move(pipe1);
        mechanic.Move(pump);
        mechanic.SetPumpDirection(pipe1, pipe2);
        PrintFinish();
    }

    /**
     * Felvesz egy csovet a drainbol
     * 
     */
    public static void DrainPickUpPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.PickUpPipe();
        PrintFinish();
    }

    /**
     * felvesz egy pumpat a drainbol
     * 
     */
    public static void DrainPickUpPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.PickUpPump();
        PrintFinish();
    }

    /**
     * Vizet pumpal a rendszerben
     * 
     */
    public static void WaterPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Eloszor eppen, majd Vizet folyat serult pumpaba
     * 
     */
    public static void WaterDamagedPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(8));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe4);
        actives.get(4).ConnectPipe(pipe4);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        actives.get(3).SetPumpDirection(pipe3, pipe4);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(2)).TrytoDamage();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat teli csovekbe
     * 
     */
    public static void WaterFullPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(2)).TrytoDamage();
        System.out.println("--------- Test: Pumpa elrontva, a megelozo cso fel fog telni");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat ures csobe
     * 
     */
    public static void WaterEmptyPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(1)).TrytoDamage();
        System.out.println("--------- Test: Pumpa elrontva, az output cso ki fog urulni");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet dolyat serult csobe
     * 
     */
    public static void WaterToDamagedPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        pipe2.Damage();
        System.out.println("--------- Test: Cso elrontva, a belekerulo viz kifolyik");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet probal szivni serult csobol
     * 
     */
    public static void WaterFromDamagedPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe3);
        actives.get(3).ConnectPipe(pipe3);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        actives.get(2).SetPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        pipe2.Damage();
        System.out.println(
                "--------- Test: Cso elrontva, a belole nem lehet vizet kiszivni, mert ures");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat a drainbe
     * 
     */
    public static void WaterDrain()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet sziv a forrasbol
     * 
     */
    public static void WaterSource()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat felig bedugott csobe
     * 
     */
    public static void WaterLoosePipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.SetGame(game);
        Pipe.SetGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe1);
        actives.get(1).ConnectPipe(pipe2);
        actives.get(2).ConnectPipe(pipe2);
        actives.get(1).SetPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        actives.get(2).DisconnectPipe(pipe2);
        System.out.println("--------- Test: Cso lecsatlakoztatva, a belekerulo viz kifolyik");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.Step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }
}
