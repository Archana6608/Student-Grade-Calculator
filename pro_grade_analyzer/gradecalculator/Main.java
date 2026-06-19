package gradecalculator;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        GradeUI.printHeader();
        System.out.print(GradeUI.YELLOW + "Enter Student Roll No / Name: " + GradeUI.RESET);
        String studentId = scr.nextLine();
        Student student = new Student(studentId);
        System.out.println("\n" + GradeUI.PURPLE + "💡 SELECT INPUT MODE:" + GradeUI.RESET);
        System.out.println("1. Standard Entry (One by one)");
        System.out.println("2. Express Batch Loader (Bulk Entry)");
        System.out.print("Choose mode (1/2): ");
        int mode = scr.nextInt(); scr.nextLine(); 
        if (mode == 2) {
            System.out.println("\n" + GradeUI.CYAN + "📝 Enter data in format [SubName:Marks] separated by commas." + GradeUI.RESET);
            System.out.print("Example -> Maths:92,Physics:85,Chemistry:78 : ");
            String batchInput = scr.nextLine();
            String[] tokens = batchInput.split(",");
            for (String token : tokens) {
                try {
                    String[] subData = token.split(":");
                    String name = subData[0].trim();
                    double mark = Double.parseDouble(subData[1].trim());
                    student.addSubject(name, mark);
                } catch (Exception e) { System.out.println(GradeUI.RED + "⚠️ Skipping invalid batch token: " + token + GradeUI.RESET); }
            }
        } else {
            System.out.print(GradeUI.YELLOW + "Enter total number of subjects: " + GradeUI.RESET);
            int totalSub = scr.nextInt(); scr.nextLine(); 
            for (int i = 0; i < totalSub; i++) {
                System.out.print(GradeUI.CYAN + "\nEnter Subject " + (i + 1) + " Name: " + GradeUI.RESET);
                String subName = scr.nextLine();
                while (true) {
                    System.out.print("Enter Marks for " + subName + ": ");
                    try {
                        double mark = scr.nextDouble(); scr.nextLine(); 
                        student.addSubject(subName, mark); break;
                    } catch (MarkException e) { System.out.println(GradeUI.RED + e.getMessage() + GradeUI.RESET);
                    } catch (Exception e) { System.out.println(GradeUI.RED + "❌ Invalid Input! Enter numbers only." + GradeUI.RESET); scr.nextLine(); }
                }
            }
        }
        GradeUI.printReportAndAppendDB(student);
        scr.close();
    }
}
