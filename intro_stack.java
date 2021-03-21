import java.util.regex.*;

public class intro1 {

    public static boolean verify(String str){
        int bracketCount = 0;

        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                if (str.charAt(i + 1) != '['){
                    return false;
                }
            }

            if (str.charAt(i) == '['){
                bracketCount++;
            }

            if (str.charAt(i) == ']'){
                bracketCount--;
            }
        }

        if (bracketCount != 0){
            return false;
        }

        return (Pattern.matches("[a-zA-Z\\[\\]0-9]+", str));
    }

    public static String parse(String str){
        String res = "";
        int count = 0;
        int bracketCount = 0;
        int firstBracketInd = 0;

        if (verify(str)){
            for (int i = 0; i < str.length(); i++){
                char currChar = str.charAt(i);
                if (Character.isLetter(currChar) && bracketCount == 0){
                    res += currChar;
                }

                if (Character.isDigit(currChar) && bracketCount == 0){
                    count = currChar - '0';
                }

                if (currChar == '['){
                    bracketCount++;
                    if (bracketCount == 1){
                        firstBracketInd = i;
                    }
                }

                if (currChar == ']'){
                    bracketCount--;
                    if (bracketCount == 0){
                        res += str.substring(firstBracketInd + 1, i).repeat(count);
                    }
                    firstBracketInd = 0;
                }
            }
        }
        else {
            return "Bad string";
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        System.out.println(parse(str));
    }
}