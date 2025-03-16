import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Sensor {
    public int getpos(){

        String data = "";
        int position = 0;


        while(position == 0)
        {
            try{
                Thread.sleep(3000); // allows the move to properly register as initially it may be a little wobbly
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }


            try {
                File myObj = new File("C:\\Users\\jeenu\\Desktop\\python programs\\python-arduino projects\\TTTpos.txt");
                Scanner myReader = new Scanner(myObj); // retrieves the number in the file which is the position
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                    position = Integer.parseInt(data);
                }
                myReader.close();
            } catch ( FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        return position;
    }
}
