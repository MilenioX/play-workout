package controllers;

import java.util.List;
import javax.inject.*;
import play.mvc.*;
import play.data.FormFactory;
import play.data.Form;
import views.html.products.list;
import views.html.products.details;
import models.Product;

public class ProductController extends Controller {

    Form<Product> productForm;

    @Inject
    public ProductController(FormFactory formFactory) {
        this.productForm = formFactory.form(Product.class);
    }

    /**
     * List all products
     */
    public Result list() {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    /**
     * Show a blank product form
     */
    public Result newProduct() {
        return ok(details.render(productForm));
    }

    /**
     * Show a product edit form
     */
    public Result details(String ean) {
        return TODO;
    }

    /**
     * Save a product
     */
    public Result save() {
        return TODO;
    }
}