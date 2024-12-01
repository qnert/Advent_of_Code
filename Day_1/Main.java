import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class Main{
    public static int calc_similarity(int[] arr, int nb){
        int counter = 0;
        for (int i: arr){
            if (i == nb)
                counter++;
        }
        return counter;
    }

    public static int calc_distance(int[] left, int[] right){
        int sum = 0;
        Arrays.sort(left);
        Arrays.sort(right);
        for (int j = 0; j < 1000; j++){
            sum += Math.abs(left[j] - right[j]);
        }
        return sum;
    }

    public static void main(String[] args){
        try{
            long sum = 0;
            long apperance = 0;
            int[] left = new int[1000];
            int[] right = new int[1000];
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()){
                String[] line = myReader.nextLine().split("   ");
                left[i] = Integer.parseInt(line[0]);
                right[i++] = Integer.parseInt(line[1]);
            }
            myReader.close();
            //part 1
            sum = calc_distance(left, right);

            //part 2
            for (int j = 0; j < 1000; j++){
                apperance += left[j] * calc_similarity(right, left[j]);
            }
            System.out.println("The total distance is: " + sum);
            System.out.println("The similarity factor is: " + apperance);

        }
        catch(Exception e){
            System.out.println("Error!");
        }
    }
}
