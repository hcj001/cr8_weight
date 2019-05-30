package com.ohoyee.weight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ohoyee.weight.entity.WeightItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IWeightItemMapper extends BaseMapper<WeightItem> {

    List<WeightItem> getWeightItemList(@Param("start") String start,@Param("offset") int offset);
    List<WeightItem> getWeightItemList2(@Param("inStr") String inStr);
//    String getBatchNoStart();
//    int saveErrorBatchNo(@Param("values") String values);
//    int updateBatchNoStart(@Param("BatchNo")String BatchNo);
//    List<WeightItem> getFaildWeightItemList();
//    int deleteFaildBatchNo(@Param("BatchNos")String BatchNos);
//    int updateFaildTime(@Param("BatchNo") String BatchNo);
}


