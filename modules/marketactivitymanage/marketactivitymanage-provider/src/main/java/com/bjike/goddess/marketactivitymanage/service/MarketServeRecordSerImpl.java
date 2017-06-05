package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场招待记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.101 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketServeRecordSerCache")
@Service
public class MarketServeRecordSerImpl extends ServiceImpl<MarketServeRecord, MarketServeRecordDTO> implements MarketServeRecordSer {

    @Autowired
    private CustomerInfoSer customerInfoSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            throw new SerException("您不是相应模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

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
        checkPermission();
        List<MarketServeRecord> list = super.findByPage(dto);
        List<MarketServeRecordBO> listBO = BeanTransform.copyProperties(list, MarketServeRecordBO.class);
        return listBO;
    }

    /**
     * 保存市场招待记录
     *
     * @param to 保存市场招待记录to
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketServeRecordBO save(MarketServeRecordTO to) throws SerException {
        checkPermission();
        MarketServeRecord marketServeRecord = BeanTransform.copyProperties(to, MarketServeRecord.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MarketServeRecordBO bo = BeanTransform.copyProperties(marketServeRecord, MarketServeRecordBO.class);
        return bo;
    }

    /**
     * 更新市场招待记录
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MarketServeRecordTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MarketServeRecord model = super.findById(to.getId());
            if (model != null) {
                updateMarketServeRecord(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新市场招待记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMarketServeRecord(MarketServeRecordTO to, MarketServeRecord model) throws SerException {
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
            for (int i = 0; i < clientSize; i++) {
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
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
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
     * 资金模块意见
     *
     * @param id                市场招待记录唯一标识
     * @param fundModuleOpinion 资金模块意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException {
        checkPermission();
        MarketServeRecord model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setFundModuleOpinion(fundModuleOpinion);
        super.update(model);
    }

    /**
     * 决策层审核意见
     *
     * @param id                    市场招待记录唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException {
        checkPermission();
        MarketServeRecord model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setExecutiveAuditOpinion(executiveAuditOpinion);
        super.update(model);
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
        checkPermission();
        MarketServeRecord entity = super.findById(id);
        MarketServeRecordBO bo = BeanTransform.copyProperties(entity, MarketServeRecordBO.class);
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", id));
        List<CustomerInfo> customerInfoList = customerInfoSer.findByCis(dto);
        List<CustomerInfoBO> customerInfoBOList = BeanTransform.copyProperties(customerInfoList, CustomerInfoBO.class);
        bo.setCustomerInfoBOList(customerInfoBOList);
        return bo;
    }

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }
}