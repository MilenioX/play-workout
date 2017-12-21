package controllers;

import play.mvc.*;
import views.html.products.list;
import models.Product;
import java.util.List;

public class ProductController extends Controller {

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
        return TODO;
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