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
    
    public Result index() {
        return redirect(routes.ProductController.list(0));
    }

    /**
     * List all products
     */
    public Result list(Integer page) {
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
        final Product product = Product.findByEan(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }

        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    /**
     * Save a product
     */
    public Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        product.save();
        flash("success", String.format("Successfully added product %s", product));
        return redirect(routes.ProductController.list(1));
    }

    /**
     * Delete a product
     */
    public Result delete(String ean) {
        final Product product = Product.findByEan(ean);

        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }

        Product.remove(product);
        return redirect(routes.ProductController.list(1));
    }
}