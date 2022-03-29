package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class HtmlReport implements Report {
    private Store store;

    public HtmlReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table>")
                .append("<tr>")
                .append("<td>Name</td>")
                .append("<td>Hired</td>")
                .append("<td>Fired</td>")
                .append("<td>Salary</td>")
                .append("</tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append("<td>").append(employee.getName()).append("</td")
                    .append("<td>").append(employee.getHired()).append("</td")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>");
        }
        text.append("</table>");
        return text.toString();
    }
}