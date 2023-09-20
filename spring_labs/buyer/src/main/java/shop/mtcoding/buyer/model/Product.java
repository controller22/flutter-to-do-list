package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.buyer.util.DateUtil;

@Setter
@Getter
//name, price, qty, created_at
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;

    // createdAtToString
    public String getCreatedAtToString() {
        return DateUtil.format(createdAt);
    }
}
