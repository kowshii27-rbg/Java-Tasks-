public class findseclargestelement{
    public static void main(String[] args){

        int[] arr = {10,50,70,20,30,100};

        int first = Integer.MIN_VALUE;
        int sec = Integer.MIN_VALUE;

        for ( int num : arr){
            if (num > first){
                sec = first;
                first = num;
            }
            else if (num > sec && num != first){
                sec = first;
            }
        }

        System.out.println("Second largest element is: " + sec);
    }
}