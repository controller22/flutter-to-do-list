package shop.mtcoding.blog.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String userId;
    private Timestamp createdAt;

}
