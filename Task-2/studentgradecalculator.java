import java.util.Scanner;

public class studentgradecalculator {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Marks for 5 subjects:");

        double sub1 = sc.nextDouble();
        double sub2 = sc.nextDouble();
        double sub3 = sc.nextDouble();
        double sub4 = sc.nextDouble();
        double sub5 = sc.nextDouble();

        double total = sub1 + sub2 + sub3 + sub4 + sub5;
        double average = total  / 5;

        System.out.println("Total Marks =" + total);
        System.out.println("Average = " + average);

        if (average >= 90){
            System.out.println("Grade: S");
        }
        else if ( average >= 80){
            System.out.println("Grade: A");
        }
        else if( average >= 70){
            System.out.println("Grade: B");
        }
        else if ( average >= 60){
            System.out.println("Grade: C");
        }
        else if ( average >= 50){
            System.out.println("Grade: D");
        }
        else{
            System.out.println("Grade: U (FAIL)");
        }

        sc.close();
    }
}