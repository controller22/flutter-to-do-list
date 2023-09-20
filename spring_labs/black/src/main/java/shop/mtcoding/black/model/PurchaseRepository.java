package shop.mtcoding.black.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.black.dto.PurchaseAllDto;

@Mapper
public interface PurchaseRepository {
    List<PurchaseAllDto> findByUse = null;

    public int insert(@Param("userId") int userId, @Param("productId") int productId, @Param("count") int count);

    public List<Purchase> findAll();

    public Purchase findById(int id);

    public int updateById(@Param("id") int id, @Param("userId") int userId,
            @Param("qty") int qty, @Param("count") int count);

    public int deleteById(int id);

    public List<PurchaseAllDto> findByUserId(int userId);
}