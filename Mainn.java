import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Mainn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubjectManager<Subject> manager = new SubjectManager<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("\n--- MENU QUẢN LÝ MÔN HỌC ---");
            System.out.println("1. Hiển thị danh sách môn học");
            System.out.println("2. Thêm môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học theo tín chỉ (>3)");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số từ 1 đến 6!");
                continue;
            }

            switch (choice) {
                case 1:
                    manager.displayAll();
                    break;

                case 2:
                    try {
                        System.out.print("Nhập mã môn học (code): ");
                        String code = scanner.nextLine().trim();

                        System.out.print("Nhập tên môn học (name): ");
                        String name = scanner.nextLine().trim();

                        System.out.print("Nhập số tín chỉ (credits): ");
                        int credits = Integer.parseInt(scanner.nextLine());

                        if (credits < 0 || credits > 10) {
                            throw new IllegalArgumentException("Lỗi: Số tín chỉ không hợp lệ (phải nằm trong khoảng từ 0 đến 10)!");
                        }

                        System.out.print("Nhập ngày bắt đầu (dd-MM-yyyy): ");
                        LocalDate startDate = LocalDate.parse(scanner.nextLine(), formatter);

                        Subject newSubject = new Subject(code, name, credits, startDate);
                        manager.addSubject(newSubject);

                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: Số tín chỉ bắt buộc phải là một số nguyên!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("Lỗi: Định dạng ngày nhập vào không hợp lệ (yêu cầu dạng dd-MM-yyyy)!");
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã môn học cần xóa: ");
                    String codeDelete = scanner.nextLine().trim();
                    if (manager.deleteSubject(codeDelete)) {
                        System.out.println("Xóa môn học thành công.");
                    } else {
                        System.out.println("Lỗi: Không tìm thấy môn học nào có mã " + codeDelete);
                    }
                    break;

                case 4:
                    System.out.print("Nhập tên môn học cần tìm: ");
                    String searchName = scanner.nextLine().trim();
                    manager.searchByName(searchName);
                    break;

                case 5:
                    manager.filterByCredits();
                    break;

                case 6:
                    System.out.println("Đang dừng chương trình. Tạm biệt!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Chức năng không hợp lệ, vui lòng chọn từ 1-6.");
            }
        }
    }
}