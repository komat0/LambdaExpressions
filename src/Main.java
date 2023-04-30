import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

////      Получаю список сортудников, нанятых в 2017 году и сортирую по зарплате.
//        List<Employee> filteredEmployees = staff.stream()
//                .filter(e -> e.toString().contains("2017"))
//                .collect(Collectors.toList());
//
//        for (Employee e : filteredEmployees) {
//            System.out.println(e);
//        }

////      Получаю сортированный по зарплате, а затем и по имени список.
//        staff.sort(Comparator.comparing(Employee::getSalary)
//                .thenComparing((e1, e2) -> e1.getName().compareTo(e2.getName())));

//        for (Employee employee : staff) {
//            System.out.println(employee);
//        }

//      Получаю Максимальную и минимальную зарплату сотрудников, нанятых в 2017 году.
        Optional<Employee> maxSalary = staff.stream()
                .filter(e -> e.toString().contains("2017"))
                .max(Comparator.comparing(Employee::getSalary));
        Optional<Employee> minSalary = staff.stream()
                .filter(e -> e.toString().contains("2017"))
                .min(Comparator.comparing(Employee::getSalary));

        System.out.println(maxSalary);
        System.out.println(minSalary);


    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}