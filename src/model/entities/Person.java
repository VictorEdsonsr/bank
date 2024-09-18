package model.entities;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private int age;
    private LocalDate birthDate;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age = Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBithDate() {
        return birthDate;
    }

    public void setBithDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
