import java.util.ArrayList;

/**
 * Az osztály a szerelők csapatának példányait reprezentálja. A szerelők meg
 * tudják javítani a pumpákat és a csöveket, el tudják utóbbit vágni, hogy új
 * pumpákkal bővítsék a pályát, illetve át is tudják helyezni a végeit.
 */
public class Mechanic extends Player
{
    /**
     * Pumpa eszkoztar
     */
    protected ArrayList<Pump> pumps;
    /**
     * Cso eszkoztar
     */
    protected ArrayList<Pipe> pipes;

    /**
     * Konstruktor
     */
    public Mechanic(String ID, Field position)
    {
        super(ID, position);
        pipes = new ArrayList<>();
        pumps = new ArrayList<>();
    }

    /**
     * Azonosító alapján lekéri a pumpát az inventoryból
     * 
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju pumpa
     */
    @Override
    public Pump GetPumpFromInventoryByID(String ID)
    {
        for (Pump pump : pumps)
        {
            if (pump.GetID().equals(ID))
            {
                return pump;
            }
        }
        return null;
    }

    /**
     * Azonosító alapján lekéri a csövet az inventoryból
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju cso
     */
    @Override
    public Pipe GetPipeFromInventoryByID(String ID)
    {
        for (Pipe pipe : pipes)
        {
            if (pipe.GetID().equals(ID))
            {
                return pipe;
            }
        }
        return null;
    }

    /**
     * kilistázza, hogy mi található az inventoryba
     *
     * @return Hibakod
     */
    @Override
    public int ListInventory()
    {
        for (Pipe pipe : pipes)
        {
            debugOutput.println(pipe);
        }
        for (Pump pump : pumps)
        {
            debugOutput.println(pump);
        }
        return -2;
    }


    @Override
    public ArrayList<String> getInventory()
    {   
        ArrayList<String> inventory = new ArrayList<>();
        for (Pump pump : pumps)
        {
         inventory.add(pump.GetID());   
        }
        for (Pipe pipe : pipes)
        {
            inventory.add(pipe.GetID());
        }
        return inventory;
    }

    /**
     * A pumpa folyási irányának beállítása
     *
     * @param input  a bemeneti csó
     * @param output a kimeneti cső
     * @return Hibakod
     */
    @Override
    public int SetPumpDirection(Pipe input, Pipe output)
    {
        if (position.SetPumpDirection(input, output) == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * A javítást megkezdő fgv
     *
     * @return Hibakod
     */
    @Override
    public int Repair()
    {
        if (position.Repair() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * A cuccos elrontasa, csak akkor ha ronthato
     *
     * @return Hibakod
     */
    @Override
    public int Damage()
    {
        if (position.Damage() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * A csovon amin all,megjavitja, lehelyez egy pumpat, majd raall a pumpara
     *
     * @param pump a lerakando pumpa
     * @return Hibakod
     */
    @Override
    public int PlacePump(Pump pump)
    {
        if (position.PlacePump(pump) == 0)
        {
            pumps.remove(pump);
            Move(pump);
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * felvesz pumpat ha drain
     *
     * @return Hibakod
     */
    @Override
    public int PickUpPump()
    {
        Pump newPump = position.PickUpPump();
        if (newPump != null)
        {
            pumps.add(newPump);
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Csúszóssá tesz egy csövet
     *
     * @return Hibakod
     */
    @Override
    public int MakeSticky()
    {
        if (position.MakeSticky() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Ha van olyan csove amire meghivodik akkor csak
     *
     * @param pipe a hozzákapcsolandó cső
     * @return Hibakod
     */
    @Override
    public int ConnectPipe(Pipe pipe)
    {
        if (position.ConnectPipe(pipe) == 0)
        {
            pipes.remove(pipe);
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Ha a mezon all akkor lekapcsolja a szomszedos csovet
     *
     * @param pipe a lekapcsolandó cső
     * @return Hibakod
     */
    @Override
    public int DisconnectPipe(Pipe pipe)
    {
        if (position.DisconnectPipe(pipe) == 0)
        {
            pipes.add(pipe);
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * felvesz csovet akkor ha drainen allt
     *
     * @return Hibakod
     */
    @Override
    public int PickUpPipe()
    {
        Pipe newPipe = position.PickUpPipe();
        if (newPipe != null)
        {
            pipes.add(newPipe);
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Mechanic - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }
}
