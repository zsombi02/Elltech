import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * proto osztály létrehozása
 */
public class Proto
{

    /**
     * A jatek peldanya
     */
    public static Game game;

    /**
     * az input alapján végez változtatást
     */
    public static int Interpreter(String input)
    {
        return Interpreter(input, null);
    }

    /**
     * Az Interpreter metódus felelős a játékos parancsok értelmezéséért és
     * végrehajtásáért a játékban.
     */
    public static int Interpreter(String input, Player player)
    {
        String[] command = input.split(" ");
        if (player == null && command.length > 1)
        {
            player = game.GetPlayerByID(command[1]);
        }
        switch (command[0].toLowerCase())
        {
        case "debug":
        {
            if (command[1].equals("on"))
            {
                Game.SetDebugEnabled(true);
                return -2;
            }
            if (command[1].equals("off"))
            {
                Game.SetDebugEnabled(false);
                return -2;
            }
            return -1;
        }
        case "wait":
        {
            return 0;
        }
        case "listinventory":
        {
            return player.ListInventory();
        }
        case "listneighbours":
        {
            return player.ListNeighbours();
        }
        case "move":
        {
            return player.Move(game.GetFieldByID(command[2]));
        }
        case "repair":
        {
            return player.Repair();
        }
        case "damage":
        {
            return player.Damage();
        }
        case "setpumpdirection":
        {
            return player.SetPumpDirection((Pipe) game.GetFieldByID(command[2]),
                    (Pipe) game.GetFieldByID(command[3]));
        }
        case "placepump":
        {
            return player.PlacePump(player.GetPumpFromInventoryByID(command[2]));
        }
        case "pickuppump":
        {
            return player.PickUpPump();
        }
        case "makeslippy":
        {
            return player.MakeSlippy();
        }
        case "makesticky":
        {
            return player.MakeSticky();
        }
        case "connectpipe":
        {
            return player.ConnectPipe(player.GetPipeFromInventoryByID(command[2]));
        }
        case "disconnectpipe":
        {
            return player.DisconnectPipe((Pipe) game.GetFieldByID(command[2]));
        }
        case "pickuppipe":
        {
            return player.PickUpPipe();
        }
        case "step":
        {
            game.Step();
            return 0;
        }
        default:
        {
            System.out.println("Ervenytelen parancs: " + input);
            return -1;
        }
        }
    }

    /**
     * A GameMode() metódus a játékot futtató mód, amely lehetővé teszi a
     * felhasználó számára, hogy soronként kiadja a parancsokat a játékosok számára.
     *
     * @throws IOException
     */
    public static void GameMode() throws IOException
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("#############");
        System.out.println("# Jatek mod #");
        System.out.println("#############");
        System.out.println("");
        System.out.println("");
        System.out.println("A program soronkent olvassa a parancsokat:");
        System.out.println("");
        while (true)
        {
            for (int i = 0; i < game.players.size(); i++)
            {
                System.out.println(game.players.get(i) + " jatekos kovetkezik");
                System.out.println("");
                String input = console.readLine();
                if (input.toLowerCase().equals("exit"))
                {
                    game.Exit();
                    return;
                }
                else
                {
                    if (Interpreter(input, game.players.get(i)) != 0)
                    {
                        i--;
                    }
                    Game.debugOutput.println("");
                }
            }
        }
    }

    /**
     * TEszt mod futtatasa az automata tesztekert
     *
     * @throws IOException
     */
    public static void TestMode(String filename) throws IOException
    {
        BufferedReader file = new BufferedReader(
                new InputStreamReader(new FileInputStream("tests/" + filename + "/test.txt")));
        System.out.println("############");
        System.out.println("# Test mod #");
        System.out.println("############");
        System.out.println("");
        System.out.println("");
        System.out.println("A program a parancsokat a " + filename + " leiro fajlbol olvassa");
        System.out.println("");
        while (file.ready())
        {
            String input = file.readLine();
            Interpreter(input);
        }
        file.close();
        if (Arrays.equals(Files.readAllBytes(Paths.get("tests/" + filename + "/expected.txt")),
                Files.readAllBytes(Paths.get("tests/" + filename + "/result.txt"))))
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("A teszt az elvart kimenetet produkalta");
        }
        else
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("A teszt NEM az elvart kimenetet produkalta");
        }
    }

    /**
     * A bemeneti argumentum alapjan eldonti hogy milyen moot inditson
     *
     * @throws IOException
     */
   /*  public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            game = new Game(System.out);
            GameMode();
        }
        else
        {
            game = new Game(
                    new PrintStream(new FileOutputStream("tests/" + args[0] + "/result.txt")));
            TestMode(args[0]);
        }
    }
    */
}
