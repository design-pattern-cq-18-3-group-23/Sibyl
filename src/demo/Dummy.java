package demo;

import com.design_pattern_grp23.sibyl.constraints.*;

public class Dummy {
    @NotNull(message = "Name must not be null")
    String firstname;

    @NotNull(message = "Last name must be not null")
    @Length(min = 2, max = 10, message = "Last name must be within 5 to 10 character")
    String lastname;

    @NotNull(message = "Required")
    @Email(message = "Please enter a valid email")
    String email;

    @NotNull(message = "Required")
    @RegEx(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])", message = "Must contain letter & number")
    String id;

    @Min(value = 0, message = "Greater than 0")
    @Max(value = 10, message = "Less than 10")
    double gpa;

    public Dummy(String firstname, String lastname, String email, String id, double gpa) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.id = id;
        this.gpa = gpa;
    }
}
