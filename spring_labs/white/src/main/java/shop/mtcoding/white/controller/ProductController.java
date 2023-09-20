package shop.mtcoding.white.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.white.model.Product;
import shop.mtcoding.white.model.ProductRepository;


@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping({"/","/product"})
    public String findAll(Model model) {
        List<Product> productList=productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/main";
    }
    @GetMapping("/product/{id}")
    public String findOne(@PathVariable int id,Model model) {
        Product product=productRepository.findOne(id);
        model.addAttribute("product", product);
        return "product/detail";
    }
    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        int result = productRepository.delete(id);
       if (result==1) {
           return "redirect:/";
           
        } else {
           return "redirect:/product/"+id;
        
       }
    }
    @GetMapping("/product/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        Product product=productRepository.findOne(id);
        model.addAttribute("product", product);
        return "product/updateForm";
    }
    @PostMapping("/product/{id}/update")
    public String update(@PathVariable int id, String name, int price, int qty) {
        int result = productRepository.update(id, name, price, qty);
        if (result==1) {
           return "redirect:/product/"+id;
           
        } else {
           return "redirect:/product/"+id+"/updateForm";
        
       }
    }
    @GetMapping("/product/addForm")
    public String addForm() {
        return "product/addForm";
    }
    @PostMapping("/product/{id}/add")
    public String add( String name, int price, int qty) {
        int result = productRepository.insert( name, price, qty);
        if (result==1) {
           return "redirect:/";
           
        } else {
           return "redirect:/product/addForm";
        
       }
    }
      
    
}