import java.util.Scanner;
import java.io.File;

public class Main{
    public static Boolean is_valid_horizontal(String str, Boolean forward){
        if (forward && str.charAt(0) == 'X' && str.charAt(1) == 'M'
            && str.charAt(2) == 'A' && str.charAt(3) == 'S')
            return true;
        else if (!forward && str.charAt(0) == 'S' && str.charAt(1) == 'A'
            && str.charAt(2) == 'M' && str.charAt(3) == 'X')
            return true;
        return false;
    }

    public static Boolean is_valid_vertical(String[] str, int i, int j, Boolean forward){
        if (forward && str[i].charAt(j) == 'X' && str[i + 1].charAt(j) == 'M'
            && str[i + 2].charAt(j) == 'A' && str[i + 3].charAt(j) == 'S')
            return true;
        else if (!forward && str[i].charAt(j) == 'S' && str[i + 1].charAt(j) == 'A'
            && str[i + 2].charAt(j) == 'M' && str[i + 3].charAt(j) == 'X')
            return true;
        return false;
    }

    public static Boolean is_valid_diagonal_north_east(String[] str, int i, int j, Boolean forward){
        if (forward && str[i].charAt(j) == 'X' && str[i + 1].charAt(j + 1) == 'M'
            && str[i + 2].charAt(j + 2) == 'A' && str[i + 3].charAt(j + 3) == 'S')
            return true;
        else if (!forward && str[i].charAt(j) == 'S' && str[i + 1].charAt(j + 1) == 'A'
            && str[i + 2].charAt(j + 2) == 'M' && str[i + 3].charAt(j + 3) == 'X')
            return true;
        return false;
    }

    public static Boolean is_valid_diagonal_north_west(String[] str, int i, int j, Boolean forward){
        if (forward && str[i].charAt(j) == 'X' && str[i + 1].charAt(j - 1) == 'M'
            && str[i + 2].charAt(j - 2) == 'A' && str[i + 3].charAt(j - 3) == 'S')
            return true;
        else if (!forward && str[i].charAt(j) == 'S' && str[i + 1].charAt(j - 1) == 'A'
            && str[i + 2].charAt(j - 2) == 'M' && str[i + 3].charAt(j - 3) == 'X')
            return true;
        return false;
    }

    public static int check_horizontal(String[] str){
        String tmp;
        int sum = 0;
        outer:
        for (int i = 0; i < str.length; i++){
            inner:
            tmp = str[i];
            for(int j = 0; j < tmp.length(); j++){
                if (tmp.charAt(j) == 'X' && tmp.length() - j >= 4){
                    sum += is_valid_horizontal(tmp.substring(j), true) == true ? 1 : 0;
                }
                else if (tmp.charAt(j) == 'S' && tmp.length() - j >= 4)
                    sum += is_valid_horizontal(tmp.substring(j), false) == true ? 1 : 0;
            }
        }
        return sum;
    }

    public static int check_vertical(String[] str){
        String tmp;
        int sum = 0;
        outer:
        for (int i = 0; i < str.length; i++){
            inner:
            tmp = str[i];
            for(int j = 0; j < tmp.length(); j++){
                if (tmp.charAt(j) == 'X' && str.length - i >= 4)
                    sum += is_valid_vertical(str, i, j, true) == true ? 1 : 0;
                else if (tmp.charAt(j) == 'S' && str.length - i >= 4)
                    sum += is_valid_vertical(str, i, j, false) == true ? 1 : 0;
            }
        }
        return sum;
    }

    public static int check_diagonal(String[] str){
        String tmp;
        int sum = 0;
        outer:
        for (int i = 0; i <= str.length - 4; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                if (j <= str[i].length() - 4) {
                    if (str[i].charAt(j) == 'X')
                        sum += is_valid_diagonal_north_east(str, i, j, true) ? 1 : 0;
                    else if (str[i].charAt(j) == 'S')
                        sum += is_valid_diagonal_north_east(str, i, j, false) ? 1 : 0;
                }
                if (j >= 3) {
                    if (str[i].charAt(j) == 'X')
                        sum += is_valid_diagonal_north_west(str, i, j, true) ? 1 : 0;
                    else if (str[i].charAt(j) == 'S')
                        sum += is_valid_diagonal_north_west(str, i, j, false) ? 1 : 0;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args){
        try{
            int i = 0;
            String[] puzzle = new String[140];
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()){
                puzzle[i++] = myReader.nextLine();
            }
            System.out.println("The amount of occurences of the word 'XMAS' in the input is: " + (check_horizontal(puzzle) + check_vertical(puzzle) + check_diagonal(puzzle)));
            myReader.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
