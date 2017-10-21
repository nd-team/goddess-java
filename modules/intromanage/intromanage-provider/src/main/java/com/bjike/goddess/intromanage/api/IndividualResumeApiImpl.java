package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.bo.SummationBO;
import com.bjike.goddess.intromanage.dto.IndividualResumeDTO;
import com.bjike.goddess.intromanage.entity.IndividualResume;
import com.bjike.goddess.intromanage.excel.SonPermissionObject;
import com.bjike.goddess.intromanage.service.IndividualResumeSer;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
import com.bjike.goddess.intromanage.to.IndividualDisplayFieldTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人简介业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("individualResumeApiImpl")
public class IndividualResumeApiImpl implements IndividualResumeAPI {

    @Autowired
    private IndividualResumeSer individualResumeSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return individualResumeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return individualResumeSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询个人简介
     *
     * @param id 个人简介唯一标识
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public IndividualResumeBO findResumeById(String id) throws SerException {
        return  individualResumeSer.findResumeById(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 个人简介dto
     * @throws SerException
     */
    @Override
    public Long count(IndividualResumeDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }
        return individualResumeSer.count(dto);
    }

    /**
     * 分页查询个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public List<IndividualResumeBO> list(IndividualResumeDTO dto) throws SerException {
        return individualResumeSer.list(dto);
    }

    /**
     * 保存个人简介
     *
     * @param to 个人简介to
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public IndividualResumeBO save(IndividualResumeTO to) throws SerException {
        return individualResumeSer.save(to);
    }

    /**
     * 根据id删除个人简介
     *
     * @param id 个人简介唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        individualResumeSer.remove(id);
    }

    /**
     * 更新个人简介
     *
     * @param to 个人简介to
     * @throws SerException
     */
    @Override
    public void update(IndividualResumeTO to) throws SerException {
        individualResumeSer.update(to);
    }

    /**
     * 设置个人简介显示字段
     *
     * @param username 用户名称数组
     * @param to       个人简介显示字段
     * @throws SerException
     */
    @Override
    public void setIndividualDisplayField(String[] username, IndividualDisplayFieldTO to) throws SerException {
        individualResumeSer.setIndividualDisplayField(username, to);
    }

    @Override
    public void congealFirmin(String id) throws SerException {
        individualResumeSer.congealFirmin(id);
    }

    @Override
    public void thawFirmin(String id) throws SerException {
        individualResumeSer.thawFirmin(id);
    }

    @Override
    public SummationBO summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return individualResumeSer.summaWeek(year,month,week);
    }

    @Override
    public SummationBO summaMonth(Integer year, Integer month) throws SerException {
        return individualResumeSer.summaMonth(year,month);
    }

    @Override
    public SummationBO summaTotal(String endDate) throws SerException {
        return individualResumeSer.summaTotal(endDate);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return individualResumeSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return individualResumeSer.templateExport();
    }

}