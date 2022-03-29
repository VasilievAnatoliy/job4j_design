package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class AccountingReport implements Report {
    private Store store;

    public AccountingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / 100).append("$").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
