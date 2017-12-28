package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.service.MarketServeRecordSer;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordImprotTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.099 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketServeRecordApiImpl")
public class MarketServeRecordApiImpl implements MarketServeRecordAPI {

    @Autowired
    private MarketServeRecordSer marketServeRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return marketServeRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketServeRecordSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO findById(String id) throws SerException {
        MarketServeRecord model = marketServeRecordSer.findById(id);
        return BeanTransform.copyProperties(model, MarketServeRecordBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 市场招待记录dto
     * @throws SerException
     */
    @Override
    public Long count(MarketServeRecordDTO dto) throws SerException {
        return marketServeRecordSer.count(dto);
    }

    /**
     * 分页查询市场招待记录
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public List<MarketServeRecordBO> list(MarketServeRecordDTO dto) throws SerException {
        return marketServeRecordSer.list(dto);
    }

    /**
     * 保存市场招待记录
     *
     * @param to 市场招待记录to
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO save(MarketServeRecordTO to) throws SerException {
        return marketServeRecordSer.save(to);
    }

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        marketServeRecordSer.remove(id);
    }

    /**
     * 更新市场招待记录
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    public void update(MarketServeRecordTO to) throws SerException {
        marketServeRecordSer.update(to);
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param id 市场招待记录唯一标识
     * @param fundModuleOpinion 运营商务部资金模块
     * @throws SerException
     */
    @Override
    public void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException {
        marketServeRecordSer.fundModuleOpinion(id, fundModuleOpinion);
    }

    /**
     * 决策层审核意见
     *
     * @param id 市场招待记录唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws SerException
     */
    @Override
    public void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException {
        marketServeRecordSer.executiveOpinion(id, executiveAuditOpinion);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    public MarketServeRecordBO checkDetails(String id) throws SerException {
        return marketServeRecordSer.checkDetails(id);
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        marketServeRecordSer.addClientInfo(to);
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void editClientInfo(CustomerInfoTO to) throws SerException {
        marketServeRecordSer.editClientInfo(to);
    }

    /**
     * 查看所有的项目名
     *
     * @throws SerException
     */
    @Override
    public List<String> findAllProjectName() throws SerException {
        return marketServeRecordSer.findAllProjectName();
    }

    public MarketServeRecordSer getMarketServeRecordSer() {
        return marketServeRecordSer;
    }

    @Override
    public byte[] exportExcel(String[] areas, String startTime, String endTime) throws SerException {
        return marketServeRecordSer.exportExcel(areas,startTime,endTime);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return marketServeRecordSer.templateExport();
    }

    @Override
    public void importExcel(List<MarketServeRecordImprotTO> marketServeRecordImprotTOS) throws SerException {
        marketServeRecordSer.importExcel(marketServeRecordImprotTOS);
    }

    @Override
    public List<String> findAllAreas() throws SerException {
        return marketServeRecordSer.findAllAreas();
    }
}