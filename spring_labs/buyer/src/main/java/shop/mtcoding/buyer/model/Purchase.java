package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//user_id, product_id, count, created_at
public class Purchase {
    private int id;
    private int usrId;
    private int productId;
    private int qty;
    private Timestamp createdAt;
}
