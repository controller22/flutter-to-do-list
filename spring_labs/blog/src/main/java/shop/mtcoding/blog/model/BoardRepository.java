package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
   
    public List<Board> findAll();

    public List<Board> findByUserId (int userId);

    public int deleteById(int id);

    public int updateById(@Param("id") int id,@Param("title") String title, @Param("userId") String userId);

    public int insert(@Param("title") String title, @Param("userId") String userId);
    
}
