import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Boolean gamefinished;
        Scanner input = new Scanner(System.in);

        //recieve player and colour info from a file here
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        LED led1 = new LED(player1);
        LED led2 = new LED(player2);
        Sensor s = new Sensor();
        Player[] player = {player1, player2};
        LED[] led = {led1, led2};

        //System.out.println("How many games would you like to play?: ");
        //int numofgames = input.nextInt();
        int numofgames = 0;

        try{
             String gamenum = Files.readAllLines(Paths.get("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTdetails.txt")).get(2);
             numofgames = Integer.parseInt(gamenum);
        }
        catch(IOException e){
            System.out.println(e);
        }

        for(int i = 0; i < numofgames; i++){
            Board bd = new Board();
            gamefinished = false;
            Random rand = new Random(); //instance of random class
            int upperbound = 2;//generate random values from 0-1
            int pos;
            Boolean posavailable = false;
            int randompos = rand.nextInt(upperbound);
            player[0].PrintTally();
            player[1].PrintTally();
            while(gamefinished == false) {
                System.out.println(" ");
                System.out.println(player[randompos].getName() + "'s turn");
                led[randompos].indicateturn();

                bd.printboard();
                // in the place of this, do a time delay and read from a text file of specific name
                //System.out.println("which position would you like to place counter: ");
                //pos = input.nextInt();

                System.out.println("registering move...");
                pos = s.getpos();
                if (bd.IsAvailable(player[randompos], pos)) {
                    bd.Place(player[randompos], pos);
                    posavailable = true;
                }
                else{
                    System.out.println("sorry this position is not available, moving to next");
                }

                bd.printboard();
                String potentialwinner = bd.checkwin();

                if(potentialwinner != ""){
                    gamefinished = true;
                    System.out.println(potentialwinner + " has won this game");
                    player[randompos].WonGame();
                    led[randompos].indicatewin();
                    try{
                        Thread.sleep(15000); // allows the move to properly register as initially it may be a little wobbly
                    }
                    catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                else if(bd.checkfull() == true){
                    gamefinished = true;
                    System.out.println("this game is a draw, no winner");
                }

                if(randompos == 1){ // this will switch the player for the next turn
                    randompos = 0;
                }
                else{
                    randompos = 1;
                }

                // send a signal to an LED for the next persons go after a time delay

            }
        }

        if(player[0].getNumofgameswon() > player[1].getNumofgameswon()){
            System.out.println(player[0].getName() + " is the winner");
            // send a signal to flash multiple times gradual slow
            led[0].indicatefinalwin();
        }
        else if(player[1].getNumofgameswon() > player[0].getNumofgameswon()){
            System.out.println(player[1].getName() + " is the winner");
            // send a signal to flash multiple times gradual slow
            led[1].indicatefinalwin();
        }
        else{
            System.out.println("its a draw between " + player[0].getName() + " and " + player[1].getName());
            // send a signal to flash both colours
        }

    }

}