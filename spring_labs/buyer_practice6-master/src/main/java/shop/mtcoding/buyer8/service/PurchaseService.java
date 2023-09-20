package shop.mtcoding.buyer8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer8.model.Product;
import shop.mtcoding.buyer8.model.ProductRepository;
import shop.mtcoding.buyer8.model.Purchase;
import shop.mtcoding.buyer8.model.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Transactional
    public int 구매하기(int productId, int principalId, int count) {
        // 상품 존재 확인
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }

        // 재고가 있는지 확인
        if (product.getQty() < count) {
            return -1;
        }

        // product_tb 재고 반영 (update)
        int res1 = productRepository.updateById(productId, product.getName(), product.getPrice(),
                product.getQty() - count);
        if (res1 != 1) {
            return -1;
        }

        // purchase_tb 목록에 추가 (insert)
        int res2 = purchaseRepository.insert(principalId, productId, count);
        if (res2 != 1) {
            return -1;
        }

        return 1;
    }

    @Transactional
    public int 삭제하기(int purchaseId) {
        // 상품목록에 있는지 확인
        Purchase purchase = purchaseRepository.findById(purchaseId);
        if (purchase == null) {
            return -1;
        }

        // product_tb 재고 반영 (update)
        int productId = purchase.getProductId();
        Product product = productRepository.findById(productId);

        int res1 = productRepository.updateById(productId, product.getName(), product.getPrice(),
                product.getQty() + purchase.getCount());
        if (res1 != 1) {
            return -1;
        }

        // purchase_tb 목록에서 제거 (delete)
        int res2 = purchaseRepository.deleteById(purchaseId);
        if (res2 != 1) {
            return -1;
        }

        return 1;
    }
}
