package com.ohoyee.weight.mapper2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ohoyee.weight.entity.WeightItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IWeightItemMapper2 extends BaseMapper<WeightItem> {

    String getBatchNoStart();
    int saveErrorBatchNo(@Param("values") String values);
    int updateBatchNoStart(@Param("BatchNo") String BatchNo);
    List<String> getFaildBatchNoList();
    int deleteFaildBatchNo(@Param("BatchNos") String BatchNos);
    int updateFaildTime(@Param("BatchNo") String BatchNo);
}


