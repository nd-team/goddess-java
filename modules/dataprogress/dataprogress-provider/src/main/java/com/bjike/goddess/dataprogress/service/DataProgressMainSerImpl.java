package com.bjike.goddess.dataprogress.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dataprogress.bo.DataProgressChildBO;
import com.bjike.goddess.dataprogress.bo.DataProgressMainBO;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.dto.DataProgressMainDTO;
import com.bjike.goddess.dataprogress.entity.DataProgressChild;
import com.bjike.goddess.dataprogress.entity.DataProgressMain;
import com.bjike.goddess.dataprogress.to.DataProgressChildTO;
import com.bjike.goddess.dataprogress.to.DataProgressMainTO;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资料收集进度管理主表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dataprogressSerCache")
@Service
public class DataProgressMainSerImpl extends ServiceImpl<DataProgressMain, DataProgressMainDTO> implements DataProgressMainSer {
    @Autowired
    private DataProgressChildSer dataProgressChildSer;

    @Override
    public Long count(DataProgressChildDTO dataProgressChildDTO) throws SerException {
        Long count = dataProgressChildSer.count(dataProgressChildDTO);
        return count;
    }

    @Override
    public DataProgressMainBO getId(String id) throws SerException {
        DataProgressMain entity = super.findById(id);
        DataProgressMainBO bo = BeanTransform.copyProperties(entity, DataProgressMainBO.class);
        if(bo!= null){
            DataProgressChildDTO dataProgressChildDTO = new DataProgressChildDTO();
            dataProgressChildDTO.getConditions().add(Restrict.eq("dataProgressMain.id",bo.getId()));
            List<DataProgressChild> childList = dataProgressChildSer.findByCis(dataProgressChildDTO);
            List<DataProgressChildBO> childBOS = BeanTransform.copyProperties(childList,DataProgressChildBO.class);
            bo.setDataProgressChildBOS(childBOS);
        }
        return bo;
    }

    @Override
    public List<DataProgressMainBO> list(DataProgressMainDTO dataProgressMainDTO) throws SerException {
        List<DataProgressMain> mainList = super.findByCis(dataProgressMainDTO);
        List<DataProgressMainBO> mainBOList = BeanTransform.copyProperties(mainList,DataProgressMainBO.class);
        if(mainBOList != null){
            for(DataProgressMainBO mainBO:mainBOList){
                DataProgressChildDTO dataProgressChildDTO = new DataProgressChildDTO();
                dataProgressChildDTO.getConditions().add(Restrict.eq("dataProgressMain.id",mainBO.getId()));
                List<DataProgressChild> childList = dataProgressChildSer.findByCis(dataProgressChildDTO);
                List<DataProgressChildBO> childBOList = BeanTransform.copyProperties(childList,DataProgressChildBO.class);
                mainBO.setDataProgressChildBOS(childBOList);
            }
        }
        return mainBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void add(DataProgressMainTO to) throws SerException {
        DataProgressMain main = BeanTransform.copyProperties(to, DataProgressMain.class, true);
        main.setPlanCollectionTime(DateUtil.parseDate(to.getPlanCollectionStartTime() + "至" + to.getPlanCollectionEndTime()));
        main.setCreateTime(LocalDateTime.now());
        super.save(main);
        List<DataProgressChildTO> dataProgressChildTOS = to.getDataProgressChildTOS();
        for (DataProgressChildTO dataProgressChildTO : dataProgressChildTOS) {
            DataProgressChild child = BeanTransform.copyProperties(dataProgressChildTO, DataProgressChild.class, true);
            child.setCreateTime(LocalDateTime.now());
            child.setDataProgressMain(main);
            dataProgressChildSer.save(child);
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(DataProgressMainTO to) throws SerException {
        DataProgressMain main = super.findById(to.getId());
        BeanTransform.copyProperties(to, main, true);
        main.setPlanCollectionTime(DateUtil.parseDate(to.getPlanCollectionStartTime() + "至" + to.getPlanCollectionEndTime()));
        main.setModifyTime(LocalDateTime.now());
        super.update(main);
        //根据主表id查询子表数据并删除
        DataProgressChildDTO dataProgressChildDTO = new DataProgressChildDTO();
        dataProgressChildDTO.getConditions().add(Restrict.eq("dataProgressMain.id", main.getId()));
        List<DataProgressChild> childList = dataProgressChildSer.findByCis(dataProgressChildDTO);
        if (childList != null && childList.size() > 0) {
            dataProgressChildSer.remove(childList);
        }
        //修改字表的数据
        List<DataProgressChildTO> dataProgressChildTOS = to.getDataProgressChildTOS();
        for(DataProgressChildTO childTO:dataProgressChildTOS){
            DataProgressChild child = BeanTransform.copyProperties(childTO,DataProgressChild.class,true);
            child.setModifyTime(LocalDateTime.now());
            dataProgressChildSer.update(child);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        DataProgressChild child = dataProgressChildSer.findById(id);
        if(child == null){
            throw new SerException("该对象不存在");
        }
        dataProgressChildSer.remove(id);
        List<DataProgressMain> mainList = super.findAll();
        Set<String> main = new HashSet<>();
        for(DataProgressChild child1:dataProgressChildSer.findAll()){
            main.add(child1.getDataProgressMain().getId());
        }
        for(DataProgressMain main1:mainList){
            if(!main.contains(main1.getId())){
                super.remove(main1);
            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void follow(DataProgressMainTO to) throws SerException {
        DataProgressMain main = super.findById(to.getId());
        BeanTransform.copyProperties(to,main,true);
        main.setModifyTime(LocalDateTime.now());
        super.update(main);
        DataProgressChildDTO dataProgressChildDTO = new DataProgressChildDTO();
        dataProgressChildDTO.getConditions().add(Restrict.eq("",main.getId()));
        List<DataProgressChild> childList = dataProgressChildSer.findByCis(dataProgressChildDTO);
        if(childList != null && childList.size()>0){
            dataProgressChildSer.remove(childList);
        }
        List<DataProgressChildTO> dataProgressChildTOS = to.getDataProgressChildTOS();
        for(DataProgressChildTO childTO:dataProgressChildTOS){
            DataProgressChild child = BeanTransform.copyProperties(childTO,DataProgressChild.class,true);
            child.setModifyTime(LocalDateTime.now());
            dataProgressChildSer.update(child);
        }

    }
}