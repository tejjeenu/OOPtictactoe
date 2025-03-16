import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class LED {
    private int R;
    private int G;
    private int B;

    public LED(Player player){
        String R = "";
        String G = "";
        String B = "";

        if(player.getPlayertype() == "player1")
        {
            String[] colordetails1 = getStringFromFile(3).split(",");
            R = colordetails1[0];
            G = colordetails1[1];
            B = colordetails1[2];
        }

        if(player.getPlayertype() == "player2")
        {
            String[] colordetails2 = getStringFromFile(4).split(",");
            R = colordetails2[0];
            G = colordetails2[1];
            B = colordetails2[2];
        }

        this.R = Integer.parseInt(R);
        this.G = Integer.parseInt(G);
        this.B = Integer.parseInt(B);

    }

    public void indicateturn(){
       sendsignal("turn");
    }

    public void indicatewin(){
        sendsignal("gwin");
    }

    public void indicatefinalwin(){
        sendsignal("fwin");
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

    private void sendsignal(String signal){
        String R = Integer.toString(this.R);
        String G = Integer.toString(this.G);
        String B = Integer.toString(this.B);

        if(R.length() == 1){
            R = "00" + R;
        }
        if(R.length() == 2){
            R = "0" + R;
        }
        if(G.length() == 1){
            G = "00" + G;
        }
        if(G.length() == 2){
            G = "0" + G;
        }
        if(B.length() == 1){
            B = "00" + B;
        }
        if(B.length() == 2){
            B = "0" + B;
        }

        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\jeenu\\Desktop\\python programs\\personal projects\\TTTsignals.txt");
            myWriter.write(signal + " " + R + "," + G + "," + B);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
