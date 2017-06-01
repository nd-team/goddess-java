package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.CustomerInfoAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场招待申请业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.058 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketServeApplySerCache")
@Service
public class MarketServeApplySerImpl extends ServiceImpl<MarketServeApply, MarketServeApplyDTO> implements MarketServeApplySer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private CustomerInfoSer customerInfoSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块权限
        Boolean permissionLevel = cusPermissionSer.busCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

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
        checkPermission();
        List<MarketServeApply> list = super.findByPage(dto);
        List<MarketServeApplyBO> listBO = BeanTransform.copyProperties(list, MarketServeApplyBO.class);
        return listBO;
    }

    /**
     * 保存市场招待申请
     *
     * @param to 市场招待申请to
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketServeApplyBO save(MarketServeApplyTO to) throws SerException {
        checkPermission();
        MarketServeApply marketServeApply = BeanTransform.copyProperties(to, MarketServeApply.class, true);
        marketServeApply = super.save(marketServeApply);
        MarketServeApplyBO bo = BeanTransform.copyProperties(marketServeApply, MarketServeApplyBO.class);
        return bo;
    }

    /**
     * 更新市场招待申请
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MarketServeApplyTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MarketServeApply model = super.findById(to.getId());
            if (model != null) {
                updateMarketServeApply(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新市场招待申请
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMarketServeApply(MarketServeApplyTO to, MarketServeApply model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加用户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        String marketServeId = to.getMarketServeId();
        List<String> clientInfoNos = to.getClientInfoNos();//客户信息编号
        List<String> clientNames = to.getClientNames();//客户姓名
        List<String> importanceLevels = to.getImportanceLevels();//重要性级别

        if ((clientInfoNos != null) && (clientInfoNos.size() > 0)) {
            int clientSize = clientInfoNos.size();
            for (int i = 0; i < clientSize; i ++) {
                CustomerInfoTO customerInfoTO = new CustomerInfoTO();
                customerInfoTO.setClientInfoNo(clientInfoNos.get(i));
                customerInfoTO.setClientName(clientNames.get(i));
                customerInfoTO.setImportanceLevel(importanceLevels.get(i));
                customerInfoTO.setMarketServeId(marketServeId);

                customerInfoSer.save(customerInfoTO);
            }
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param id 市场招待申请唯一标识
     * @param fundModuleOpinion 运营商务部资金模块意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException {
        checkPermission();
        MarketServeApply model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setFundModuleOpinion(fundModuleOpinion);
        super.update(model);
    }

    /**
     * 决策层意见
     *
     * @param id 市场招待申请唯一标识
     * @param executiveAuditOpinion 决策层意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException {
        checkPermission();
        MarketServeApply model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setExecutiveAuditOpinion(executiveAuditOpinion);
        super.update(model);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyBO checkDetails(String id) throws SerException {
        // TODO: 17-3-20
        return null;
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void editClientInfo(CustomerInfoTO to) throws SerException {
        checkPermission();
        String marketServeId = to.getMarketServeId();
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", marketServeId));
        List<CustomerInfo> list = customerInfoSer.findByCis(dto);
        customerInfoSer.remove(list);
        addClientInfo(to);
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }
}