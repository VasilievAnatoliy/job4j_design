package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenAgeActor30() {
        RoleStore role = new RoleStore();
        role.add(new Role("500", 30));
        Role result = role.findById("500");
        assertThat(result.getAgeActor(), is(30));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", 30));
        Role result = role.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindAgeActor27() {
        RoleStore role = new RoleStore();
        role.add(new Role("120", 27));
        role.add(new Role("120", 57));
        Role result = role.findById("120");
        assertThat(result.getAgeActor(), is(27));
    }

    @Test
    public void whenReplaceThenAgeActorIs41() {
        RoleStore role = new RoleStore();
        role.add(new Role("30", 30));
        role.replace("30", new Role("30", 41));
        Role result = role.findById("30");
        assertThat(result.getAgeActor(), is(41));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeAgeActor() {
        RoleStore role = new RoleStore();
        role.add(new Role("30", 30));
        role.replace("10", new Role("10", 41));
        Role result = role.findById("30");
        assertThat(result.getAgeActor(), is(30));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("250", 21));
        role.delete("250");
        Role result = role.findById("250");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsAgeActor50() {
        RoleStore role = new RoleStore();
        role.add(new Role("340", 50));
        role.delete("50");
        Role result = role.findById("340");
        assertThat(result.getAgeActor(), is(50));
    }
}