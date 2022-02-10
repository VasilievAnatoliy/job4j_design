package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "model")
public class Model {

    @XmlAttribute
    private String model;

    public Model() {
    }

    public Model(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Model{"
               + "model='" + model + '\''
               + '}';
    }
}
