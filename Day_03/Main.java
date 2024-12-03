import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Main{
    public static int is_correct_format(String str, Boolean enabled){
        if (str.length() < 7 || str.length() > 11 || !str.contains(",") || enabled == false)
            return -1;
        String first_nb = new String();
        String second_nb = new String();
        first_nb = str.substring(4, str.indexOf(','));
        second_nb = str.substring(str.indexOf(',') + 1, str.length());
        return Integer.parseInt(first_nb) * Integer.parseInt(second_nb);
    }

    public static int calc_mult(String str, Boolean enabled){
        int tmp = is_correct_format(str, enabled);
        if (tmp == -1)
            return 0;
        return tmp;
    }

    public static Boolean check_enabled(String str){
        if (str.contains("do()") && str.contains("don't()")){
            if (str.indexOf("do()") > str.indexOf("don't()"))
                return false;
            else
                return true;
        }
        else if (str.contains("do()") && !str.contains("don't")){
            return true;
        }
        else if (!str.contains("do()") && str.contains("don't")){
            return false;
        }
        else
            return true;
    }

    public static void main(String[] args){
        try{
            int i = 0;
            int index = 0;
            int start = 0;
            int end = 0;
            int mult = 0;
            Boolean enabled = true;
            String input = new String();
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()){
                input += myReader.nextLine();
            }
            outer:
            while(i < input.length() && input.contains("mul(")){
                start = input.indexOf("mul(");
                index = start + 4;
                while (input.charAt(index) != ')' && index < input.length()){
                    if (input.charAt(index) == 'm'){
                        input = input.substring(index);
                        i = index;
                        continue outer;
                    }
                    else if (input.length() == index)
                        break outer;
                    index++;
                }
                end = index;
                mult += calc_mult(input.substring(start, end), enabled);
                input = input.substring(end + 1);
                i = 0;
                if ((input.contains("do()") && input.indexOf("mul(") > input.indexOf("do()")) || (input.contains("don't()") && input.indexOf("mul(") > input.indexOf("don't("))){
                    enabled = check_enabled(input);
                }
            }
            System.out.println(mult);
            myReader.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
