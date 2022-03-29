package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HtmlReportTest {

    @Test
    public void whenHtmlReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new HtmlReport(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<table>")
                .append("<tr>")
                .append("<td>Name</td>")
                .append("<td>Hired</td>")
                .append("<td>Fired</td>")
                .append("<td>Salary</td>")
                .append("</tr>");
        expect.append("<tr>")
                .append("<td>").append(worker.getName()).append("</td")
                .append("<td>").append(worker.getHired()).append("</td")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>")
                .append("</table>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }


}