import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)
        students.put("ID1", new Student("Nurbek", 2.5, 18));
        students.put("ID2", new Student("Bayastan", 3.1, 18));
        students.put("ID3", new Student("Aman", 4.0, 17));
        students.put("ID4", new Student("John", 3.3, 19));
        students.put("ID5", new Student("Marsel", 3.3, 19));
        students.put("ID6", new Student("Bekbol", 4.0, 20));
        // TODO: Напечатай всех студентов (ID + объект)
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            String ID = entry.getKey();
            Student s = entry.getValue();
            System.out.println("ID: " + ID + "; Student: " + s);
        }
        // TODO: Найди студента по ID и выведи его
        String searchId = "ID2";
        System.out.println("Got student by id : " + students.get(searchId));

        // TODO: Удали одного студента по ID
        String removeId = "ID5";
        Student removedStudent = students.remove(removeId);
        System.out.println("\nRemoved student: " + removedStudent);

        // TODO: Обнови GPA у одного студента
        String updateId = "ID1";
        students.get(updateId).setGpa(3.3);
        System.out.println("\nUpdated student: " + students.get(updateId));
        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())
        ArrayList<Student> studentArrayList = new ArrayList<>(students.values());

        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи
        Collections.sort(studentArrayList);
        System.out.println("\nSorted by GPA (ascending)");
        for (Student s : studentArrayList) {
            System.out.println(s);
        }
        // TODO 6b: Отсортируй по имени (Comparator) и выведи
        studentArrayList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        System.out.println("\nSorted by name (descending)");
        for (Student s : studentArrayList) {
            System.out.println(s);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        ArrayList<Student> topStudents = new ArrayList<>(students.values());
        topStudents.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGpa(), s1.getGpa());
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + topStudents.get(i));
        }
        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<String>> gpaGroupStudents = new HashMap<>();
        for (Student s : students.values()) {
            double gpa = s.getGpa();
            if (!gpaGroupStudents.containsKey(gpa)) {
                gpaGroupStudents.put(gpa, new ArrayList<>());
            }
            gpaGroupStudents.get(gpa).add(s.getName());
        }
        boolean foundDuplicateGpa = false;
        for (Map.Entry<Double, List<String>> entry : gpaGroupStudents.entrySet()) {
            double gpa = entry.getKey();
            List<String> studentList = entry.getValue();

            if (studentList.size() > 1) {
                foundDuplicateGpa = true;
                System.out.printf("Gpa: %.2f Students\n", gpa);
                for (String name : studentList) {
                    System.out.println("Name: " + name);
                }
            }
        }
        if (!foundDuplicateGpa) {
            System.out.println("Not found any duplicate gpa");
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // TODO: Создай 2–3 курса, добавь студентов, выведи всё

        Course javaCourse = new Course("Java Basics");
        Course algorithmsCourse = new Course("Algorithms");
        Course databasesCourse = new Course("Databases");

        courseMap.put(javaCourse, new ArrayList<>());
        courseMap.put(algorithmsCourse, new ArrayList<>());
        courseMap.put(databasesCourse, new ArrayList<>());

        courseMap.get(javaCourse).add(students.get("ID1"));
        courseMap.get(javaCourse).add(students.get("ID2"));

        courseMap.get(algorithmsCourse).add(students.get("ID3"));
        courseMap.get(algorithmsCourse).add(students.get("ID4"));
        courseMap.get(algorithmsCourse).add(students.get("ID6"));

        courseMap.get(databasesCourse).add(students.get("ID1"));
        courseMap.get(databasesCourse).add(students.get("ID3"));
        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            Course course = entry.getKey();
            List<Student> courseStudents = entry.getValue();

            System.out.println(course);
            for (Student s : courseStudents) {
                System.out.println("  - " + s);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        studentArrayList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int gpaCompare = Double.compare(s1.getGpa(), s2.getGpa());
                if (gpaCompare != 0) {
                    return gpaCompare;
                }
                return s2.getName().compareTo(s1.getName());
            }
        });
        for (Student s : studentArrayList) {
            System.out.println(s);
        }
    }
}



