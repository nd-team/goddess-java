package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.entity.SurveyActualize;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 调研实施记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyActualizeSer extends Ser<SurveyActualize, SurveyActualizeDTO> {

    /**
     * 保存
     *
     * @param to 调研实施记录传输对象
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研实施记录传输对象
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研实施记录数据id
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 结束调研
     *
     * @param id 调研实施记录数据id
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO over(String id) throws SerException {
        return null;
    }

}