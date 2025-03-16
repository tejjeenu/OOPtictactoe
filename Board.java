import javax.net.ssl.HostnameVerifier;

public class Board {
    position[][] boardpos = new position[3][3];

    public Board(){
        int x = 0;
        int y = 0;


        Player background = new Player("background");
        while(x < 3){
            y = 0;
            while(y < 3){
                this.boardpos[x][y] = new position(background);
                y = y + 1;
            }
            x = x + 1;
        }
    }

    public Boolean IsAvailable(Player p, int numpos)
    {
        Boolean available = true;
        int counter = 1;
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(counter == numpos)
                {
                    if(boardpos[x][y].isAvailable() == false){
                        available = false;
                    }
                }
                counter = counter + 1;
            }
        }
        return available;
    }

    public void printboard(){
        System.out.println("-------------");
        for(int x = 0; x < 3; x++){
            System.out.println("| " + this.boardpos[x][0].getVal() + " | " + this.boardpos[x][1].getVal() + " | " + this.boardpos[x][2].getVal() + " | ");
            System.out.println("-------------");
        }
    }

    public Boolean checkfull(){
        Boolean posempty = false;
        for(int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if(this.boardpos[x][y].isAvailable() == true){
                    posempty = true;
                }
            }
        }
        if(posempty == true){
            return false;
        }
        else{
            return true;
        }
    }

    public String checkwin()
    {
        String winner = "";
        String check1 = diagonalUpDownCheck();
        String check2 = diagonalDownUpCheck();
        String check3 = HorizontalCheck();
        String check4 = VerticalCheck();

        if(check1 != "" || check2 != ""|| check3 != ""|| check4 != "")
        {
            if(check1 != "")
            {
                winner = check1;
            }
            else if(check2 != "")
            {
                winner = check2;
            }
            else if(check3 != "")
            {
                winner = check3;
            }
            else if(check4 != "")
            {
                winner = check4;
            }
        }
        return winner;
    }

    private String diagonalUpDownCheck()
    {
        String winner = "";
        if(boardpos[0][0].getVal() == boardpos[1][1].getVal() && boardpos[1][1].getVal() == boardpos[2][2].getVal())
        {
            Player player = boardpos[0][0].getPlayer();
            if(player.getPlayertype() != "background") //this code will identify if a player put this position in or not
            {
                winner = player.getName();
            }
        }
        return winner;
    }

    private String diagonalDownUpCheck()
    {
        String winner = "";
        if(boardpos[2][0].getVal() == boardpos[1][1].getVal() && boardpos[1][1].getVal() == boardpos[0][2].getVal())
        {
            Player player = boardpos[2][0].getPlayer();
            if(player.getPlayertype() != "background")
            {
                winner = player.getName();
            }
        }
        return winner;
    }

    private String HorizontalCheck()
    {
        String winner = "";
        for(int x = 0; x < 3; x++){
            if(boardpos[x][0].getVal() == boardpos[x][1].getVal() && boardpos[x][1].getVal() == boardpos[x][2].getVal())
            {
                Player player = boardpos[x][0].getPlayer();
                if (player.getPlayertype() != "background") {
                    winner = player.getName();
                }
            }
        }
        return winner;
    }

    private String VerticalCheck()
    {
        String winner = "";
        for(int y = 0; y < 3; y++){
            if(boardpos[0][y].getVal() == boardpos[1][y].getVal() && boardpos[1][y].getVal() == boardpos[2][y].getVal())
            {
                Player player = boardpos[0][y].getPlayer();
                if (player.getPlayertype() != "background") {
                    winner = player.getName();
                }
            }
        }
        return winner;
    }

    public void Place(Player p, int numpos)
    {
        int counter = 1;
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(counter == numpos)
                {
                    boardpos[x][y] = new position(p);
                    boardpos[x][y].IsUsed();
                }
                counter = counter + 1;
            }
        }
    }

}
