package gradecalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
public class Student {
    private String studentId;
    private List<Subject> subjects = new ArrayList<>();
    public Student(String studentId) { this.studentId = studentId; }
    public String getStudentId() { return studentId; }
    public void addSubject(String name, double mark) throws MarkException {
        if (mark < 0 || mark > 100) throw new MarkException("❌ Marks must be between 0 and 100!");
        subjects.add(new Subject(name, mark));
    }
    public double calculateTotal() { return subjects.stream().mapToDouble(Subject::getMark).sum(); }
    public double calculateAverage() { return subjects.isEmpty() ? 0 : calculateTotal() / subjects.size(); }
    public String calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A+ (Excellent)";
        if (avg >= 80) return "A (Very Good)";
        if (avg >= 70) return "B (Good)";
        if (avg >= 60) return "C (Satisfactory)";
        if (avg >= 50) return "D (Pass)";
        return "F (Fail)";
    }
    public List<Subject> getSubjects() { return subjects; }
    public Subject getHighestSubject() { return subjects.stream().max(Comparator.comparingDouble(Subject::getMark)).orElse(null); }
    public Subject getLowestSubject() { return subjects.stream().min(Comparator.comparingDouble(Subject::getMark)).orElse(null); }
}
