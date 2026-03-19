package samuelvalentini;

public class Customer {
    private Long id;
    private String name;
    private Integer tier;

    public Customer(String name, Integer tier) {
        this.name = name;
        this.tier = tier;
        this.id = (long) (name + "!!" + tier).hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
