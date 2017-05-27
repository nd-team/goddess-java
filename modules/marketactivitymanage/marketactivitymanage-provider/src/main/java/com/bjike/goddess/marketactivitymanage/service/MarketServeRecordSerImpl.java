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

import java.io.InputStream;
import java.io.OutputStream;
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
       /* String marketServeId = to.getMarketServeId();
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

                customerInfoAPI.save(customerInfoTO);
            }
        }*/
    }

    /**
     * 资金模块意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fundModuleOpinion(MarketServeRecordTO to) throws SerException {
        checkPermission();
        String id = to.getId();
        MarketServeRecord entity = super.findById(id);
        String yyFundModule = to.getYyFundModule();//运营商务部资金模块意见
        String fundModuleOpinion = to.getFundModuleOpinion();//资金模块意见
        entity.setYyFundModule(yyFundModule);
        entity.setFundModuleOpinion(fundModuleOpinion);
        update(entity);//更新资金模块意见
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void executiveOpinion(MarketServeRecordTO to) throws SerException {
        checkPermission();
        String id = to.getId();//获取id
        MarketServeRecord entity = super.findById(id);
        String decisionLevel = to.getDecisionLevel();//获取决策层
        AuditType executiveAuditOpinion = to.getExecutiveAuditOpinion();//决策层审核意见
        entity.setDecisionLevel(decisionLevel);
        entity.setExecutiveAuditOpinion(executiveAuditOpinion);
        update(entity);
    }

    /**
     * 导入文件
     *
     * @param inputStream 目标路径
     * @param targetPath  文件输入流
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importFile(InputStream inputStream, String targetPath) throws SerException {
        // TODO: 17-3-20
    }

    /**
     * 导出文件
     *
     * @param filePath 需要导出的文件的路径
     * @return class OutputStream
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public OutputStream exportFile(String filePath) throws SerException {
        // TODO: 17-3-20
        return null;
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