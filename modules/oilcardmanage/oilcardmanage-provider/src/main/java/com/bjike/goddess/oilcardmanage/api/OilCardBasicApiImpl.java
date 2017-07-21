package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.service.OilCardBasicSer;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 油卡基础信息对外发布接口实现类
 *
 * @Author: [Jason]
 * @Date: [17-3-13 下午5:05]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("oilCardBasicApiImpl")
public class OilCardBasicApiImpl implements OilCardBasicAPI {

    @Autowired
    private OilCardBasicSer oilCardBasicSer;

    /**
     * 新增油卡基本信息
     *
     * @param to 油卡基本信息
     * @return 油卡基本信息
     * @throws SerException 新增油卡业务异常
     */
    @Override
    public OilCardBasicBO saveOilCarBasic(OilCardBasicTO to) throws SerException {

        return oilCardBasicSer.saveOilCarBasic(to);
    }

    /**
     * 编辑油卡基本信息
     *
     * @param to 油卡基本信息
     * @return 油卡基本信息
     * @throws SerException 编辑油卡业务异常
     */
    @Override
    public OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException {

        return oilCardBasicSer.updateOilCardBasic(to);
    }

    /**
     * 冻结油卡基本信息记录
     *
     * @param id 油卡记录Id
     * @throws SerException 删除油卡业务异常
     */
    @Override
    public void freezeOilCardBasic(String id) throws SerException {
        oilCardBasicSer.freezeOilCardBasic(id);
    }

    /**
     * 解冻油卡基本信息记录
     *
     * @param id 油卡记录Id
     * @throws SerException 解冻油卡业务异常
     */
    @Override
    public void breakFreeze(String id) throws SerException {
        oilCardBasicSer.breakFreeze(id);
    }

    /**
     * 删除油卡基本信息记录
     *
     * @param id 油卡记录ID
     * @throws SerException 删除油卡记录异常
     */
    @Override
    public void deleteOilCardBasic(String id) throws SerException {
        oilCardBasicSer.remove(id);
    }

    /**
     * 油卡基础信息分页查询
     *
     * @param dto 查询条件
     * @return 油卡信息结果集
     * @throws SerException
     */
    @Override
    public List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException {
        return oilCardBasicSer.pageList(dto);
    }

    @Override
    public OilCardBasicBO findByCode(String oilCardCode) throws SerException {
        return oilCardBasicSer.findByCode(oilCardCode);
    }

    @Override
    public OilCardBasicBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(oilCardBasicSer.findById(id), OilCardBasicBO.class);
    }

    @Override
    public Long count(OilCardBasicDTO dto) throws SerException {
        return oilCardBasicSer.count(dto);
    }

    @Override
    public OilCardBasic find(String id) throws SerException {
        return oilCardBasicSer.find(id);
    }

    @Override
    public void updateOliCardBasic(OilCardBasic oilCardBasic) throws SerException {
        oilCardBasicSer.updateOliCardBasic(oilCardBasic);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return oilCardBasicSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return oilCardBasicSer.guidePermission(guidePermissionTO);
    }
}
