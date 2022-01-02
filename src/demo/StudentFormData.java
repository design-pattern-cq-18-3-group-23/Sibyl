package demo;

import com.design_pattern_grp23.sibyl.constraints.*;
import demo.customs.Id;

public class StudentFormData {
    @NotEmpty(message = "Tên là bắt buộc")
    String firstname;

    @NotEmpty(message = "Họ là bắt buộc")
    @Length(min = 5, max = 10, message = "Họ từ 5 đến 10 ký tự")
    String lastname;

    @NotEmpty(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    String email;

    @NotEmpty(message = "MSSV là bắt buộc")
    @Id(message = "MSSV phải có I ở đầu và lớn hơn 7 ký tự", length = 7)
    @RegEx(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])", message = "MSSV phải bao gồm chữ và số")
    String id;

    @Min(value = 0, message = "ĐTB lớn hơn 0", inclusive = false)
    @Max(value = 10, message = "ĐTB bé hơn hoặc bằng 10")
    double gpa;

    public StudentFormData(String firstname, String lastname, String email, String id, double gpa) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.id = id;
        this.gpa = gpa;
    }
}
