package com.bin.students.dao;

import com.bin.students.entity.boy;
import com.bin.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface boyMapper extends MyMapper<boy> {
    List<Map> getToTalAve();

    List<Integer> getNum(@Param("low")Double low, @Param("high")Double high);

    List<boy> selectStudentsOrderBytotal();
}