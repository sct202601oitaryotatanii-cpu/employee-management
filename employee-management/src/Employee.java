import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String department;
    private LocalDate joinDate;

    // DB取得用
    public Employee(int id, String name, String department, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.joinDate = joinDate;
    }

    // 登録用
    public Employee(String name, String department, LocalDate joinDate) {
        this.name = name;
        this.department = department;
        this.joinDate = joinDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    @Override
    public String toString() {
        return "ID:" + id + " / " + name + " / " + department + " / 入社日:" + joinDate;
    }
}
