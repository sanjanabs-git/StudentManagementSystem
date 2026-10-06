import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    static final String URL = "jdbc:mysql://localhost:3306/student_management";
    static final String USER = "root";
    static final String PASS = "sanju@123456";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(" Connected to Database!");

            while(true) {
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Course");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int ch = sc.nextInt();

                if(ch==1){
                    System.out.print("Name: "); String name = sc.next();
                    System.out.print("Age: "); int age = sc.nextInt();
                    System.out.print("Course: "); String course = sc.next();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO students(name,age,course) VALUES(?,?,?)");
                    ps.setString(1,name); ps.setInt(2,age); ps.setString(3,course);
                    ps.executeUpdate();
                    System.out.println("Added!");
                } else if(ch==2){
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM students");
                    while(rs.next()){
                        System.out.println(rs.getInt("id")+" | "+rs.getString("name")+" | "+rs.getInt("age")+" | "+rs.getString("course"));
                    }
                } else if(ch==3){
                    System.out.print("Enter ID to update: "); int id = sc.nextInt();
                    System.out.print("New Course: "); String course = sc.next();
                    PreparedStatement ps = con.prepareStatement("UPDATE students SET course=? WHERE id=?");
                    ps.setString(1,course); ps.setInt(2,id);
                    ps.executeUpdate();
                    System.out.println("Updated!");
                } else if(ch==4){
                    System.out.print("Enter ID to delete: "); int id = sc.nextInt();
                    PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
                    ps.setInt(1,id);
                    ps.executeUpdate();
                    System.out.println("Deleted!");
                } else if(ch==5){
                    break;
                }
            }
            con.close();
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}