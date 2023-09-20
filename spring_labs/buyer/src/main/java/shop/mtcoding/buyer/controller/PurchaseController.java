package shop.mtcoding.buyer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.dto.PurchaseAllDto;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.PurchaseRepository;
import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.service.PurchaseService;

@Controller
public class PurchaseController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseService purchaseService;

    /*
     * 목적 : 세션이 있는지 체크, 구매 히스토리 남기기, 재고 수량 변경
     */
    
    @GetMapping("/purchase")
    public String purchase(Model model) {
        User principal = (User) session.getAttribute("principal");//메서드 setAttribute에 이용한 name을 알고 있으면 조회가능능
        if (principal == null) {
            return "redirect:/notfound";
        }
        List<PurchaseAllDto> purchaseList = purchaseRepository.findByUserId(principal.getId());
        model.addAttribute("purchaseList", purchaseList);
        return "purchase/list";
    }

    @PostMapping("/purchase/insert")
    public String insert(int productId, int count) {
        // 1. 세션이 있는지 체크
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }
        // 2. 서비스 호출
        int result = purchaseService.구매하기(principal.getId(), productId, count);
        if (result == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }
}
