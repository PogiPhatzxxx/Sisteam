import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws Exception {
        
       new GradeCalculator();

       /* 
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter Prelim grade (0 if not taken): ");
        double prelim = sc.nextDouble();

        System.out.print("Enter Midterm grade (0 if not taken): ");
        double midterm = sc.nextDouble();

        System.out.print("Enter Prefinal grade (0 if not taken): ");
        double prefinal = sc.nextDouble();

        System.out.print("Enter target passing grade: ");
        double target = sc.nextDouble();

        double completed = 0;
        if (prelim > 0) completed += prelim * 0.2;
        if (midterm > 0) completed += midterm * 0.2;
        if (prefinal > 0) completed += prefinal * 0.2;

        int missing = 0;
        if (prelim == 0) missing++;
        if (midterm == 0) missing++;
        if (prefinal == 0) missing++;

        double remainingWeight = missing * 0.2 + 0.4;

        double neededAverage = (target - completed) / remainingWeight;

        double needed = neededAverage;

        System.out.println("You need an average of " + needed + " in the remaining exams to reach " + target);
*/
    }
}
