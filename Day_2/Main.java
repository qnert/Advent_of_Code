import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;

public class Main{
    public static int[] convert_string(String str){
        int i = 0;
        String[] splitted = str.split(" ");
        int[] nbs = new int[splitted.length];
        for (String s: splitted){
            nbs[i++] = Integer.parseInt(s);
        }
        return nbs;
    }

    public static Boolean evalueate_safety(int[] levels){
        if (levels.length < 2)
            return false;
        if (levels[0] <= levels[1]){
            //increasing
            for (int i = 0; i < levels.length - 1; i++){
                if (levels[i] > levels[i + 1] || levels[i] == levels[i + 1])
                    return false;
                else if (levels[i + 1] - levels[i] > 3)
                    return false;
            }
        }
        else if (levels[0] >= levels[1]){
            //decreasing
            for (int i = 0; i < levels.length - 1; i++){
                if (levels[i] < levels[i + 1] || levels[i] == levels[i + 1])
                    return false;
                else if (levels[i] - levels[i + 1] > 3)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        try{
            int counter = 0;
            Vector<String> input = new Vector < > (1000);
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()){
                input.add(myReader.nextLine());
            }
            for (String s: input){
                if (evalueate_safety(convert_string(s)) == true)
                    counter++;
            }
            System.out.println(counter);
            myReader.close();

        }
        catch(Exception e){
            System.out.println("Error!");
        }
    }
}
