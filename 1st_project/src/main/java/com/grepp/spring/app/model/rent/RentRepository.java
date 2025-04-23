package com.grepp.spring.app.model.rent;

import com.grepp.spring.app.model.rent.dto.Rent;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentRepository {
    List<Rent> selectAll();
}
