package shop.mtcoding.white.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository {

    public List<Product> findAll();

    public Product findOne(int id);
    
    public int delete(int id);
    
    public int insert(@Param("name")String name,@Param("price") int price,@Param("qty") int qty);
    
    public int update(@Param("id") int id, @Param("name")String name,@Param("price") int price,@Param("qty") int qty);

}
