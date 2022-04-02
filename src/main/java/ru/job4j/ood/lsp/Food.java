package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.Objects;

public class Food {
    String name;
    LocalDate expiryDate;
    LocalDate createDate;
    double price;
    int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate,
                double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.getPrice(), getPrice()) == 0
                && getDiscount() == food.getDiscount()
                && Objects.equals(getName(), food.getName())
                && Objects.equals(getExpiryDate(), food.getExpiryDate())
                && Objects.equals(getCreateDate(), food.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExpiryDate(), getCreateDate(),
                getPrice(), getDiscount());
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
