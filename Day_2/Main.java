import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main{
    public static Vector <Integer> convert_string(String str){
        String[] splitted = str.split(" ");
        Vector <Integer> nbs = new Vector <> (splitted.length);
        for (String s: splitted){
            nbs.add(Integer.parseInt(s));
        }
        return nbs;
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

    public static boolean evaluate_safety(Vector<Integer> sequence, Boolean enabled) {
        if (enabled == true){
            for (int i = 0; i < sequence.size() - 1; i++){
                if(!isSafe(sequence))
                    return false;
            }
            return true;
        }
        else{
            for (int i = 0; i < sequence.size(); i++) {
                Vector<Integer> modifiedSequence = new Vector<>(sequence);
                modifiedSequence.remove(i);
                if (isSafe(modifiedSequence)) {
                    return true;
                }
            }
            return false;
        }
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
                if (evaluate_safety(convert_string(s), true) == true)
                    counter++;
            }
            System.out.println("[PART 1] The amount of safe reports: " + counter);

            counter = 0;
            for (String s: input){
                if (evaluate_safety(convert_string(s), false) == true)
                    counter++;
            }
            System.out.println("[PART 2] The amount of safe reports: " + counter);
            myReader.close();

        }
        catch(Exception e){
            System.out.println("Error!");
        }
    }
}
