package ru.job4j.generic;

public class Role extends Base {
    private final Integer ageActor;

    public Role(String id, Integer ageActor) {
        super(id);

        this.ageActor = ageActor;
    }

    public Integer getAgeActor() {
        return ageActor;
    }
}
