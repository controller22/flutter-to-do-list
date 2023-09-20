package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//username, password, email, created_at
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
}
