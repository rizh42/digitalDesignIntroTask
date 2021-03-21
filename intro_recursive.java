import java.util.*;
import java.util.regex.*;

public class intro_recursive {

    public static boolean verify(String str){
        int bracesCount = 0;

        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                if (str.charAt(i + 1) != '['){
                    return false;
                }
            }

            if (str.charAt(i) == '['){
                bracesCount++;
            }

            if (str.charAt(i) == ']'){
                bracesCount--;
            }
        }

        if (bracesCount != 0){
            return false;
        }

        return (Pattern.matches("[a-zA-Z\\[\\]0-9]+", str));
    }

    public static ArrayList allDigits(String str){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                res.add(str.charAt(i) - '0');
            }
        }
        return res;
    }



    public static String parse(String str){
        //tokenize(str);
        ArrayList<Integer> allCounts = allDigits(str);
        String tmp = "";
        String substr = "";
        String res = "";
        int count = 0;

        if (!verify(str)){
            return "Bad string";
        }
        for (int i = 0; i < str.length(); i++){
            char currChar = str.charAt(i);
            //System.out.println(currChar);
            if (Character.isDigit(currChar) && str.charAt(i + 1) == '['){
                count = allCounts.get(0);
                allCounts.remove(0);
                int openBraceInd = str.indexOf("[");
                int closeBraceInd = str.lastIndexOf("]");
                tmp = str.substring(openBraceInd + 1, closeBraceInd);
                i = str.lastIndexOf("]") + 1;

                if (Character.isLetter(currChar)){
                    res += currChar;
                }

                substr = parse(tmp);

                int begin = tmp.indexOf("[") - 1;
                int tmpEnd = tmp.indexOf("]");

                if (begin > 0 && tmpEnd > 0){
                    tmp = tmp.replace(tmp.substring(begin, tmpEnd), substr);
                }

                res += tmp.repeat(count);
            }
        }
        res = res.replaceAll("\\[", "");
        res = res.replaceAll("\\]", "");
        return res;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        System.out.println(parse(str));
    }
}