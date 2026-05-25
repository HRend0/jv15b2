import java.util.ArrayList;
import java.util.Optional;

public class SubjectManager<T extends Subject> {
    private ArrayList<T> subjectList;

    public SubjectManager() {
        this.subjectList = new ArrayList<>();
    }

    public void addSubject(T subject) {
        subjectList.add(subject);
        System.out.println("Thêm môn học thành công.");
    }

    public boolean deleteSubject(String code) {
        return subjectList.removeIf(subject -> subject.getCode().equalsIgnoreCase(code));
    }

    public void displayAll() {
        if (subjectList.isEmpty()) {
            System.out.println("Danh sách môn học hiện đang trống!");
            return;
        }
        System.out.println("--- Danh sách môn học ---");
        subjectList.forEach(System.out::println);
    }

    public void searchByName(String name) {
        Optional<T> result = subjectList.stream()
                .filter(subject -> subject.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();

        if (result.isPresent()) {
            System.out.println("Môn học tìm thấy: " + result.get());
        } else {
            System.out.println("Không có môn học phù hợp");
        }
    }

    public void filterByCredits() {
        System.out.println("Các môn học có số tín chỉ > 3:");
        long count = subjectList.stream()
                .filter(subject -> subject.getCredits() > 3)
                .peek(System.out::println)
                .count();

        if (count == 0) {
            System.out.println("(Không có môn học nào có số tín chỉ > 3)");
        }
    }
}