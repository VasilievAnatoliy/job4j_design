package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private String color;

    @XmlAttribute
    private boolean suv;

    @XmlAttribute
    private int doors;

    private Model model;

    @XmlElementWrapper(name = "optionses")
    @XmlElement(name = "options")
    private String[] options;

    public Car() {
    }

    public Car(String color, boolean suv, int doors, Model model, String[] options) {
        this.color = color;
        this.suv = suv;
        this.doors = doors;
        this.model = model;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "color='" + color + '\''
                + ", suv=" + suv
                + ", doors=" + doors
                + ", model=" + model
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) throws IOException, JAXBException {
        Car car = new Car("black", true, 5,
                new Model("Niva"), new String[]{"ABS", "winter package"});

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Car.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
