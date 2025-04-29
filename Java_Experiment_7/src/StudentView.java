
    import java.util.Scanner;

    public class StudentView {
        public static void main(String[] args) {
            StudentController controller = new StudentController();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter Name: ");
            String name = scanner.next();
            System.out.print("Enter Department: ");
            String dept = scanner.next();
            System.out.print("Enter Marks: ");
            double marks = scanner.nextDouble();

            Student student = new Student(id, name, dept, marks);
            controller.addStudent(student);
            System.out.println("Student added successfully!");

            System.out.println("\nAll Students:");
            controller.getStudents().forEach(s -> System.out.println(s.getName() + " - " + s.getDepartment()));
            scanner.close();;
        }
    }


