package shop.mtcoding.white.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//name, price, qty, created_at
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp created_at;
}
