import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Player {
    private String Name;
    private String playertype;
    private char boardpiece;
    private int numofgameswon;


    public Player(String playertype)
    {
        String Name = "";
        char boardpiece = ' ';
        // take the details from a text file here
        if(playertype == "player1"){
            String[] player1details = getStringFromFile(0).split(",");
            Name = player1details[0];
            boardpiece = player1details[1].charAt(0);
        }
        if(playertype == "player2"){
            String[] player2details = getStringFromFile(1).split(",");
            Name = player2details[0];
            boardpiece = player2details[1].charAt(0);
        }
        if(playertype == "background"){
            Name = "background";
            boardpiece = ' ';
        }

        this.Name = Name;
        this.playertype = playertype;
        this.boardpiece = boardpiece;
        this.numofgameswon = 22;
    }

    public String getName() {
        return Name;
    }

    public String getPlayertype() {
        return playertype;
    }

    public char getBoardpiece() {
        return boardpiece;
    }

    public int getNumofgameswon() {
        return this.numofgameswon;
    }

    public void PrintTally(){
        String tally = "";
        if(this.numofgameswon > 0){
            int numoffivegates = this.numofgameswon / 5;
            int numofsinglegates = this.numofgameswon % 5;

            for(int i = 0; i < numoffivegates; i++){
                tally = tally + "卌 ";
            }

            for(int i = 0; i < numofsinglegates; i++){
                tally = tally + "|";
            }
            System.out.println(this.getName() + "'s wins: " + tally);
        }
    }

    public void WonGame(){
        this.numofgameswon = this.numofgameswon + 1;
    }

    private String getStringFromFile(int n){
        String line = "";
        try{
            line = Files.readAllLines(Paths.get("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTdetails.txt")).get(n);
            //System.out.println(line);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return line;
    }
}
