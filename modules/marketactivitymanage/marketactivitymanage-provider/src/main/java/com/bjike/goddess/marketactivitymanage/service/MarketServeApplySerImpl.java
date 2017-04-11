package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.CustomerInfoAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
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
    private CustomerInfoAPI customerInfoAPI;

    /**
     * 分页查询市场招待申请
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public List<MarketServeApplyBO> list(MarketServeApplyDTO dto) throws SerException {
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
    @Transactional
    public MarketServeApplyBO save(MarketServeApplyTO to) throws SerException {
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
    @Transactional
    public void update(MarketServeApplyTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
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
    @Transactional
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

                customerInfoAPI.save(customerInfoTO);
            }
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    @Transactional
    public void fundModuleOpinion(MarketServeApplyTO to) throws SerException {
        this.update(to);
    }

    /**
     * 决策层意见
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    @Transactional
    public void executiveOpinion(MarketServeApplyTO to) throws SerException {
        this.update(to);
    }

    /**
     * 上传附件
     *
     * @param inputStream 目标路径
     * @param targetPath 文件输入流
     * @throws SerException
     */
    @Override
    public void uploadAttachment(InputStream inputStream, String targetPath) throws SerException {
        // TODO: 17-3-20
    }

    /**
     * 导入文件
     *
     * @param inputStream 目标路径
     * @param targetPath 文件输入流
     * @throws SerException
     */
    @Override
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
    public OutputStream exportFile(String filePath) throws SerException {
        // TODO: 17-3-20
        return null;
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
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}