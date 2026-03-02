public class palindromecheck{
    public static void main(String[] args){

        String str = "kowshik";

        int first = 0;
        int last = str.length() - 1;
        boolean ispalindrome = true;


        while( first < last ){
            if ( str.charAt(first) != str.charAt(last)){
                ispalindrome = false;
                break;

            }
            first++;
            last--;
        }

        if (ispalindrome){
            System.out.println("The string is a palindrome");
        }
        else{
            System.out.println("The string is not a palindrome");
        }
    }
}