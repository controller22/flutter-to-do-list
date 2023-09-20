package shop.mtcoding.black.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//quser_id, product_id, count, created_at
public class Purchase {
    private int id;
    private int userId;
    private int productId;
    private int qty;
    private Timestamp createdAt;
}
