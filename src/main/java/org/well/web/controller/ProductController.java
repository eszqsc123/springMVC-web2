package org.well.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.well.web.entity.Product;
import org.well.web.entity.ProductForm;
import org.well.web.service.ProductService;

import java.math.BigDecimal;

@Controller
public class ProductController {

    /**
     * spring框架中的Autowired注释是根据类型查找指定的bean对象,并进行注入
     */
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/input-product")
    public String inputProduct(Model model) {
        model.addAttribute("name", "Mr.chen");
        System.out.println("inputProduct: " + model);
        return "ProductForm";
    }

    @RequestMapping(value = "/save-product", method = RequestMethod.POST)
    public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(new BigDecimal(productForm.getPrice()));
        } catch (Exception e) {
        }
        Product saveProduct = productService.add(product);
        /*
         * 要让客户端的浏览器重定向,在不使用HttpResponse的sentRedirect方法的情况下,
         * 可以使用返回值为redirect:/路径/让客户端的用户重定向。
         * 但是对于重定向的数据绑定,可以通过RedirectAttribute对象的addFlashAttribute方法绑定数据
         * 解决重定向的传值问题。
         */
        redirectAttributes.addFlashAttribute("message", "The product was successfully added");
        return "redirect:/product-view/" + saveProduct.getId();
    }

    @RequestMapping(value = "/product-view/{id}")
    /*
        spring进行传值的使用一定会创建一个model对象,该对象用于存放数据
     */
    public String viewProduct(@PathVariable("id") Long id, Model model) {
        Product product =productService.get(id);
        /*
        model的实质是调用了request.addAttribute方法,他是一个Map
         */
//        model.addAttribute("product", product);
        System.out.println("viewProduct:  " + model);
        return "ProductView";
    }

}
