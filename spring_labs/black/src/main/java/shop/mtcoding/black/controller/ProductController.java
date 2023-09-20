package shop.mtcoding.black.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.black.model.Product;
import shop.mtcoding.black.model.ProductRepository;

@Controller
public class ProductController {
   
    @Autowired 
    ProductRepository productRepository;

    @GetMapping({"/","/product"})
    public String home(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/list";
    }
    
    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, Model model){
        Product product = productRepository.findById(id);
        if (product == null) {
            return "redirect:/notfound";
        } else {
            model.addAttribute("product", product);
            return "product/detail";
        }
    }
}
