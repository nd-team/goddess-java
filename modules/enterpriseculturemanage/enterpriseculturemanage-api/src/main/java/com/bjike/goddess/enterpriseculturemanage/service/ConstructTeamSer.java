package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.enterpriseculturemanage.bo.ConstructTeamBO;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;

import java.util.List;

/**
 * 建设小组业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConstructTeamSer extends Ser<ConstructTeam, ConstructTeamDTO> {

    /**
     * 新增建设小组
     *
     * @param to 建设小组
     * @return 建设小组
     */
    ConstructTeamBO insertModel(ConstructTeamTO to) throws SerException;

    /**
     * 编辑建设小组
     *
     * @param to 建设小组
     * @return 建设小组
     */
    ConstructTeamBO updateModel(ConstructTeamTO to) throws SerException;

    /**
     * 分页查询建设小组
     *
     * @param dto 分页条件
     * @return 建设结果集
     */
    List<ConstructTeamBO> pageList(ConstructTeamDTO dto) throws SerException;
}