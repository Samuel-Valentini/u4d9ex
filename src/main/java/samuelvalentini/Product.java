package samuelvalentini;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = (long) (name + "&&" + category + "║║" + price).hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
