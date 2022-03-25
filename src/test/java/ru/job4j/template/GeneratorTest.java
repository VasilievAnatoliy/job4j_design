package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenValidKeys() {
        Generator generator = new TestGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("Petr Arsentev", "you");
        Assert.assertThat(generator.produce(template, map), is("I am a Petr Arsentev, Who are you? "));
    }

    @Ignore
    @Test
    public void whenValidKeysNameAndAge() {
        Generator generator = new TestGenerator();
        String template = "Name ${name}, age ${age}.";
        Map<String, String> map = Map.of("Petr", "18");
        Assert.assertThat(generator.produce(template, map), is("Name Petr, age 18."));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidKey() {
        Generator generator = new TestGenerator();
        String template = "Name ${name}, age ${age}.";
        Map<String, String> map = Map.of("Petr", "Arsentev");
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNotNeededKeys() {
        Generator generator = new TestGenerator();
        String template = "Name ${name}, age ${age}.";
        Map<String, String> map = Map.of("Petr", "18", "Petr", "Arsentev");
        generator.produce(template, map);
    }
}