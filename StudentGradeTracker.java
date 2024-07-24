import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class StudentGradeTracker {

    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean moreGrades = true;

        while (moreGrades) {
            try {
                System.out.print("Enter a grade (or type 'done' to finish): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("done")) {
                    moreGrades = false;
                } else {
                    int grade = Integer.parseInt(input);
                    if (grade >= 0 && grade <= 100) {
                        grades.add(grade);
                    } else {
                        System.out.println("Please enter a grade between 0 and 100.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid grade or 'done'.");
            }
        }

        if (grades.isEmpty()) {
            System.out.println("No grades were entered.");
        } else {
            double average = calculateAverage(grades);
            int highest = findHighest(grades);
            int lowest = findLowest(grades);

            System.out.println("Average Grade: " + average);
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
        }

        scanner.close();
    }

    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    private static int findHighest(ArrayList<Integer> grades) {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    private static int findLowest(ArrayList<Integer> grades) {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}
