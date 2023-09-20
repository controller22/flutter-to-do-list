package shop.mtcoding.buyer8.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.buyer8.util.dateUtil;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;

    public String getCreatedAtToString() {
        return dateUtil.format(createdAt);
    }
}
