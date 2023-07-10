package project.spring.build.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.spring.build.component.DesignDTO;

public interface DesignMapper {

    @Select("SELECT design FROM map WHERE first = #{a} AND second = #{b}")
    public String getNum(@Param("a") String a, @Param("b")String b);
    
    @Select("SELECT feature FROM placefeature WHERE placenum = #{designNum}")
    public String feature(@Param("designNum")String designNum);
    
    @Select("SELECT locationX, locationY FROM map WHERE first = '서울특별시' AND second = '중구'")
    public DesignDTO getXY(@Param("a") String a, @Param("b")String b);
}