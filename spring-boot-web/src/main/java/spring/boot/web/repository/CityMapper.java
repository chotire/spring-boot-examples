package spring.boot.web.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import spring.boot.web.model.City;

@Repository
public interface CityMapper {
//    @Select("SELECT * FROM CITY WHERE state = #{state}")
    City findByState(@Param("state") String state);
}