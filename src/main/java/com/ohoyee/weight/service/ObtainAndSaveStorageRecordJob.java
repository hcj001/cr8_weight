package com.ohoyee.weight.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ohoyee.weight.entity.WeightItem;
import com.ohoyee.weight.mapper.IWeightItemMapper;
import com.ohoyee.weight.mapper2.IWeightItemMapper2;
import com.ohoyee.weight.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ObtainAndSaveStorageRecordJob {
    private final static Logger LOGGER = LoggerFactory.getLogger(ObtainAndSaveStorageRecordJob.class);
    //偏移量，一次推送的最大条数
    public static final int OFFSET = 20;

   @Resource
   IWeightItemMapper weightItemMapper;
   @Resource
   IWeightItemMapper2 weightItemMapper2;

   @Value("${handleWeight.url}")
    private String url;

    /**
     * 推送任务
     * BatchNo,编号规则：2019050001,年月+四位自增数
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void execute() {
        LOGGER.info("计时读取物料入库信息任务开始！");
        String start = weightItemMapper2.getBatchNoStart();
        List<WeightItem> list = weightItemMapper.getWeightItemList(start,OFFSET);
        LOGGER.info("本次查询条件[start:"+start+",offset:"+OFFSET+"];list size:"+list.size());
        if(list != null && list.size()>0) {
            SerializeConfig config = SerializeConfig.getGlobalInstance();
            config.setTypeKey("yyyy-MM-dd hh:mm:ss");
            String jsonData = JSONArray.toJSONString(list,config, SerializerFeature.WriteDateUseDateFormat);
            LOGGER.info("推送数据："+jsonData);
            String result = post(url,jsonData,0);
            LOGGER.info("result:"+result);
            System.out.println(result);
            //保存失败的批次编号
            if("false".equals(result)) {
                saveErrorBatchNo(list);
                LOGGER.info("推送数据失败！！！:"+list);
            } else if(StringUtils.isEmpty(result) || "HttpHostConnectException".equals(result)) {
                LOGGER.info("HttpHostConnectException:连接失败!");
            } else {
                //倒序取第一个为最大BatchNo
                Collections.sort(list, new Comparator<WeightItem>() {
                    @Override
                    public int compare(WeightItem o1, WeightItem o2) {
                        return o2.getBatchNo().compareTo(o1.getBatchNo());
                    }
                });
                //最大数+1作为新起始点,2019059999+1= 2019060000,2019050001+1=2019050002
                Long newStart = Long.parseLong(list.get(0).getBatchNo())+1;
                weightItemMapper2.updateBatchNoStart(newStart+"");
                LOGGER.info("计时读取物料入库信息任务结束！ newStart:"+newStart);
            }
        } else {
            LOGGER.info("暂时未查到推送数据！");
        }
    }
    /**
     * 执行未推送成功的数据
     */
    @Scheduled(cron = "0 5/20 * * * ?")
    public void executeErrors() {
        List<String> listBatchNo = weightItemMapper2.getFaildBatchNoList();
        String inStr = getInStr(listBatchNo);
        List<WeightItem> list = weightItemMapper.getWeightItemList2(inStr);
        if(list != null && list.size()>0) {
            LOGGER.info("开始推送失败次数小于4的数据:"+list);
            String jsonData = JSONArray.toJSONString(list);
            String result = post(url,jsonData,0);
            if("success".equals(result)) {
                //成功后删除faild表里的记录
                deleteFaildBatchNo(list);
            } else {
                //更新失败次数
                updateFaildTime(list);
                LOGGER.info("推送E_Faild表的数据失败！");
            }
            LOGGER.info("推送E_Faild表的数据结束！");
        }
    }

    /**
     * 调八局系统保存接口
     * @param url
     * @param jsonData
     * @param error  第一次推送错误次数，设置为0，用于循环调用判断次数
     * @return
     */
    public String post(String url,String jsonData,int error) {
        //递归次数
        error ++;
        String result = HttpClientUtil.doPostJson(url,jsonData);
        if("false".equals(result) && error <=4) {
            post(url,jsonData,error);
        }
        return result;
    }

    /**
     * 保存调用三次仍然失败的批次编号
     * @param list
     */
    public void saveErrorBatchNo(List<WeightItem> list) {
        StringBuilder values = new StringBuilder();
        for(int i=0;i<list.size();i++) {
            //拼sql ('xxx'),('xxxx')
            if(i != list.size()-1) {
                values.append("('"+list.get(i).getBatchNo()+"'),");
            } else {
                values.append("('"+list.get(i).getBatchNo()+"')");
            }
        }
        weightItemMapper2.saveErrorBatchNo(values.toString());
    }
    public String getInStr(List<String> list) {
        StringBuilder values = new StringBuilder();
        values.append("('");
        for(int i=0;i<list.size();i++) {
            //拼sql ('xxx'),('xxxx')
            if(i != list.size()-1) {
                values.append(list.get(i)+"','");
            } else {
                values.append(list.get(i));
            }
        }
        values.append("')");
        return values.toString();
    }
    /**
     * 删除faild表里成功后的记录
     * @param list
     * @return
     */
    public void deleteFaildBatchNo(List<WeightItem> list) {
        StringBuilder str = new StringBuilder();
        str.append("(");
        for (int i = 0; i < list.size(); i++) {
            if(i != list.size() -1) {
                str.append(list.get(i).getBatchNo()+",");
            } else {
                str.append(list.get(i).getBatchNo());
            }
        }
        str.append(")");
        weightItemMapper2.deleteFaildBatchNo(str.toString());
    }
     /**
     * 更新faild表里失败的次数
     * @param resultList
     * @return
     */
    public void updateFaildTime(List<WeightItem> resultList) {
        for(int i=0;i<resultList.size();i++) {
            weightItemMapper2.updateFaildTime(resultList.get(i).getBatchNo());
        }
    }

}
