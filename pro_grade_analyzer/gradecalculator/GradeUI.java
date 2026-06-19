package gradecalculator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class GradeUI {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BOLD = "\u001B[1m";

    public static void printHeader() {
        System.out.println(CYAN + "==================================================" + RESET);
        System.out.println(PURPLE + BOLD + "   ⚡ PRO ENTERPRISE GRADE ANALYTICS ENGINE ⚡   " + RESET);
        System.out.println(CYAN + "==================================================" + RESET);
    }
    public static void printReportAndAppendDB(Student s) {
        System.out.println("\n" + CYAN + "==================================================" + RESET);
        System.out.println(GREEN + BOLD + "           📊 REPORT FOR STUDENT: " + s.getStudentId().toUpperCase() + "          " + RESET);
        System.out.println(CYAN + "==================================================" + RESET);
        System.out.println(YELLOW + "SUBJECT-WISE ANALYSIS:" + RESET);
        for (Subject sub : s.getSubjects()) {
            String statusCol = sub.getStatus().equals("PASS") ? GREEN : RED;
            System.out.printf("  %-15s : %.2f / 100 -> [%s%s%s]\n", sub.getName(), sub.getMark(), statusCol, sub.getStatus(), RESET);
        }
        System.out.println(CYAN + "--------------------------------------------------" + RESET);
        if(s.getHighestSubject() != null && s.getLowestSubject() != null) {
            System.out.println(CYAN + "🎯 PERFORMANCE INSIGHTS:" + RESET);
            System.out.printf("  🔥 Highest Proficiency : %s (%.2f)\n", s.getHighestSubject().getName(), s.getHighestSubject().getMark());
            System.out.printf("  🧊 Focus Needed On      : %s (%.2f)\n", s.getLowestSubject().getName(), s.getLowestSubject().getMark());
            System.out.println(CYAN + "--------------------------------------------------" + RESET);
        }
        System.out.printf(BOLD + "Total Marks Secured : " + RESET + "%.2f\n", s.calculateTotal());
        System.out.printf(BOLD + "Aggregate Percentage: " + RESET + "%.2f%%\n", s.calculateAverage());
        String g = s.calculateGrade();
        String col = g.contains("F") ? RED : GREEN;
        System.out.println(BOLD + "Standing Grade       : " + RESET + col + BOLD + g + RESET);
        System.out.println(CYAN + "==================================================" + RESET);
        try (PrintWriter writer = new PrintWriter(new FileWriter("Grade_Master_Database.csv", true))) {
            writer.printf("%s,%.2f,%.2f%%,%s\n", s.getStudentId(), s.calculateTotal(), s.calculateAverage(), g);
            System.out.println(GREEN + "💾 Record successfully committed to 'Grade_Master_Database.csv'!" + RESET);
        } catch (IOException e) { System.out.println(RED + "⚠️ Database commit failed."); }
    }
}
