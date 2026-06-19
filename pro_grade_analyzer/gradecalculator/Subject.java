package gradecalculator;
public class Subject {
    private String name;
    private double mark;
    public Subject(String name, double mark) { this.name = name; this.mark = mark; }
    public String getName() { return name; }
    public double getMark() { return mark; }
    public String getStatus() { return this.mark >= 50 ? "PASS" : "FAIL"; }
}
