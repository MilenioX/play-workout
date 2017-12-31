package models;

import play.data.validation.Constraints;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;

public class Tag {

    private static List<Tag> tags = new LinkedList<Tag>();

    static {
        tags.add(new Tag(1L, "lightweight", Product.findByName("paperclips 1")));
        tags.add(new Tag(2L, "metal", Product.findByName("paperclips")));
        tags.add(new Tag(3L, "plastic", Product.findByName("paperclips")));
    }

    public Long id;
    @Constraints.Required
    public String name;
    public List<Product> products;


    public Tag() {
        // Left empty
    }

    public Tag(Long id, String name,
        Collection<Product> products) {
        
        this.id = id;
        this.name = name;
        this.products = new LinkedList<Product>(products);
        for (Product product : products) {
            product.tags.add(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public static Tag findById(Long id) {
        for (Tag tag : tags) {
            if (tag.id == id) return tag;
        }
        return null;
    }
}