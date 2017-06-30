package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyDetailBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.excel.MarketServeApplyExcel;
import com.bjike.goddess.marketactivitymanage.service.MarketServeApplySer;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyImprotTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待申请业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.056 ]
 * @Description: [ 市场招待申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketServeApplyApiImpl")
public class MarketServeApplyApiImpl implements MarketServeApplyAPI {

    @Autowired
    private MarketServeApplySer marketServeApplySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return marketServeApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketServeApplySer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO getOne(String id) throws SerException {
        return marketServeApplySer.getOne(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 市场招待申请dto
     * @throws SerException
     */
    @Override
    public Long count(MarketServeApplyDTO dto) throws SerException {
        return marketServeApplySer.count(dto);
    }

    /**
     * 分页查询市场招待申请
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public List<MarketServeApplyBO> list(MarketServeApplyDTO dto) throws SerException {
        return marketServeApplySer.list(dto);
    }

    /**
     * 保存市场招待申请
     *
     * @param to 市场招待申请to
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO save(MarketServeApplyTO to) throws SerException {
        return marketServeApplySer.save(to);
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        marketServeApplySer.remove(id);
    }

    /**
     * 更新市场招待申请
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    public void update(MarketServeApplyTO to) throws SerException {
        marketServeApplySer.update(to);
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        marketServeApplySer.addClientInfo(to);
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param id 市场招待申请唯一标识
     * @param fundModuleOpinion 运营商务部资金模块意见
     * @throws SerException
     */
    @Override
    public void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException {
        marketServeApplySer.fundModuleOpinion(id, fundModuleOpinion);
    }

    /**
     * 决策层意见
     *
     * @param id 市场招待申请唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws SerException
     */
    @Override
    public void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException {
        marketServeApplySer.executiveOpinion(id, executiveAuditOpinion);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyDetailBO checkDetails(String id) throws SerException {
        return marketServeApplySer.checkDetails(id);
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void editClientInfo(CustomerInfoTO to) throws SerException {
        marketServeApplySer.editClientInfo(to);
    }

    @Override
    public List<String> findAllProjectName() throws SerException {
        return marketServeApplySer.findAllProjectName();
    }

    @Override
    public byte[] exportExcel(String[] areas, String startTime, String endTime) throws SerException {
        return marketServeApplySer.exportExcel(areas,startTime,endTime);
    }

    @Override
    public void importExcel(List<MarketServeApplyImprotTO> marketServeApplyImprotTOS) throws SerException {
        marketServeApplySer.importExcel(marketServeApplyImprotTOS);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return marketServeApplySer.templateExport();
    }

    @Override
    public List<String> findAllAreas() throws SerException {
        return marketServeApplySer.findAllAreas();
    }
}