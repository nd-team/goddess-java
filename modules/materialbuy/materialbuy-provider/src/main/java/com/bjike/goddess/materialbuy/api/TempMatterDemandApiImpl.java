package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.TempMatterDemandBO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import com.bjike.goddess.materialbuy.service.TempMatterDemandSer;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;
import com.bjike.goddess.materialbuy.to.TempMatterDemandTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 临时物资需求业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tempMatterDemandApiImpl")
public class TempMatterDemandApiImpl implements TempMatterDemandAPI {

    @Autowired
    private TempMatterDemandSer tempMatterDemandSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return tempMatterDemandSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return tempMatterDemandSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO findById(String id) throws SerException {
        TempMatterDemand model = tempMatterDemandSer.findById(id);
        return BeanTransform.copyProperties(model, TempMatterDemandBO.class);
    }

    /**
     * 分页查询临时物资需求
     *
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public List<TempMatterDemandBO> list(TempMatterDemandDTO dto) throws SerException {
        return tempMatterDemandSer.list(dto);
    }

    /**
     * 保存临时物资需求
     *
     * @param to 临时物资需求to
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO save(TempMatterDemandTO to) throws SerException {
        return tempMatterDemandSer.save(to);
    }

    /**
     * 根据id删除临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        tempMatterDemandSer.remove(id);
    }

    /**
     * 更新临时物资需求
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    @Override
    public void update(TempMatterDemandTO to) throws SerException {
        tempMatterDemandSer.update(to);
    }

    /**
     * 审核
     *
     * @param to 临时物资需求
     * @throws SerException
     */
    @Override
    public void audit(TempMatterDemandTO to) throws SerException {
        tempMatterDemandSer.update(to);
    }

    /**
     * 查看详情
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO checkDetail(String id) throws SerException {
        return tempMatterDemandSer.checkDetail(id);
    }
}