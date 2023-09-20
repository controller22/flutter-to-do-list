package shop.mtcoding.black.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.black.util.DateUtil;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;

    public String getcreatedAtToString(){
        return DateUtil.format(createdAt);
    }
}
