package model.entities;

public class Account {
    private Person person;
    private Currency currency;
    private Boolean active;
    private Double salary;

    public Account(Person person, Currency currency, Boolean active, Double salary) {
        this.active = active;
        this.salary = salary;
        this.person = person;
        this.currency = currency;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
