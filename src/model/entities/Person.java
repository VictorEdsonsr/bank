package model.entities;

import Utils.DateUtils;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private Integer age;
    private LocalDate birthDate;

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = DateUtils.formatToLocalDate(birthDate);
        this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBithDate() {
        return DateUtils.formatToString(birthDate);
    }

    public void setBithDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
