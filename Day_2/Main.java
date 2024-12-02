import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;

public class Main{
    public static Vector <Integer> convert_string(String str){
        int i = 0;
        String[] splitted = str.split(" ");
        Vector <Integer> nbs = new Vector <> (splitted.length);
        for (String s: splitted){
            nbs.add(Integer.parseInt(s));
        }
        return nbs;
    }

    public static Boolean evaluate_safety_first(Vector <Integer> levels){
        if (levels.elementAt(0) <= levels.elementAt(1)){
            //increasing
            for (int i = 0; i < levels.size() - 1; i++){
                if (levels.elementAt(i) > levels.elementAt(i + 1) || levels.elementAt(i) == levels.elementAt(i + 1))
                    return false;
                else if (levels.elementAt(i + 1) - levels.elementAt(i) > 3)
                    return false;
            }
        }
        else if (levels.elementAt(0) >= levels.elementAt(1)){
            //decreasing
            for (int i = 0; i < levels.size() - 1; i++){
                if (levels.elementAt(i) < levels.elementAt(i + 1) || levels.elementAt(i) == levels.elementAt(i + 1))
                    return false;
                else if (levels.elementAt(i) - levels.elementAt(i + 1) > 3)
                    return false;
            }
        }
        return true;
    }

    public static boolean isSafe(Vector<Integer> sequence) {
        boolean increasing = sequence.get(0) <= sequence.get(1);
        for (int i = 0; i < sequence.size() - 1; i++) {
            int current = sequence.get(i);
            int next = sequence.get(i + 1);

            if (increasing) {
                if (next <= current || next - current > 3) {
                    return false;
                }
            } else {
                if (next >= current || current - next > 3) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean evaluate_safety_second(Vector<Integer> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            Vector<Integer> modifiedSequence = new Vector<>(sequence);
            modifiedSequence.remove(i);
            if (isSafe(modifiedSequence)) {
                return true;
            }
        }
        return false;
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
                if (evaluate_safety_first(convert_string(s)) == true)
                    counter++;
            }
            System.out.println(counter);

            counter = 0;
            for (String s: input){
                if (evaluate_safety_second(convert_string(s)) == true)
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
