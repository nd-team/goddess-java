package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;
import com.bjike.goddess.oilcardmanage.service.OilCardReceiveSer;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 油卡领用对外发布实现类
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:34]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("oilCardReceiveApiImpl")
public class OilCardReceiveApiImpl implements OilCardReceiveAPI {

    @Autowired
    private OilCardReceiveSer oilCardReceiveSer;

    @Override
    public OilCardReceiveBO saveOilCardReceive(OilCardReceiveTO to) throws SerException {
        return oilCardReceiveSer.insertModel(to);
    }

    @Override
    public OilCardReceiveBO updateOilCardReceive(OilCardReceiveTO to) throws SerException {
        return oilCardReceiveSer.updateModel(to);
    }

    @Override
    public void auditOilCardReceive(String id, String auditSuggestion, OilCardReceiveResult oilCardReceiveResult) throws SerException {
        oilCardReceiveSer.audit(id, auditSuggestion, oilCardReceiveResult);
    }

    @Override
    public void deleteOilCardReceive(String id) throws SerException {
        oilCardReceiveSer.remove(id);
    }

    @Override
    public void returnOilCardReceive(String id) throws SerException {
        oilCardReceiveSer.returnOilCardReceive(id);
    }

    @Override
    public List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException {
        return oilCardReceiveSer.pageList(dto);
    }

    @Override
    public OilCardReceiveBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(oilCardReceiveSer.findById(id), OilCardReceiveBO.class);
    }

    @Override
    public Long count(OilCardReceiveDTO dto) throws SerException {
        return oilCardReceiveSer.count(dto);
    }
}
