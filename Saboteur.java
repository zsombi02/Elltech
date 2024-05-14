/**
 * Az osztály a nomádok csapatának példányait reprezentálja. A nomádok ki tudják
 * lyukasztani a csöveket és át tudják állítani a pumpákat.
 */
public class Saboteur extends Player
{
    /**
     * Konstruktor
     */
    public Saboteur(String ID, Field position)
    {
        super(ID, position);
    }

    /**
     * Pumpa input output irányának beállítása
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
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
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
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Csúszóssá teszi az elemet
     *
     * @return Hibakod
     */
    @Override
    public int MakeSlippy()
    {
        if (position.MakeSlippy() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Ragadóssá teszi az elemet
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
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }
}
