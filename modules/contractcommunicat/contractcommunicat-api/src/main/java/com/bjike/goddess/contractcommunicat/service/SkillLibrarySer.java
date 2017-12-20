package com.bjike.goddess.contractcommunicat.service;

<<<<<<< Updated upstream
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.HistoryAppraiseBO;
import com.bjike.goddess.contractcommunicat.bo.SkillLibraryBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.HistoryAppraise;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;

import java.util.List;
=======
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
>>>>>>> Stashed changes

/**
 * 谈判技巧库业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillLibrarySer extends Ser<SkillLibrary, SkillLibraryDTO> {
<<<<<<< Updated upstream
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 谈判技巧库列表总条数
     */
    default Long count(SkillLibraryDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个谈判技巧库
     *
     * @return class SkillLibraryTOBO
     */
    default SkillLibraryBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取谈判技巧库
     *
     * @param dto 谈判技巧库dto
     * @return class SkillLibraryBO
     * @throws SerException
     */
    default List<SkillLibraryBO> list(SkillLibraryDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加谈判技巧库
     *
     * @param to 谈判技巧库数据to
     * @return class SkillLibraryBO
     * @throws SerException
     */
    default SkillLibraryBO insert(SkillLibraryTO to) throws SerException {
        return null;
    }

    /**
     * 编辑谈判技巧库
     *
     * @param to 谈判技巧库数据to
     * @return class SkillLibraryBO
     * @throws SerException
     */
    default SkillLibraryBO edit(SkillLibraryTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除谈判技巧库
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 评价
     *
     * @param to to
     * @return class SkillLibraryBO
     * @throws SerException
     */
    default SkillLibraryBO appraise(SkillLibraryTO to) throws SerException {
        return null;
    }
    /**
     * 查看历史评价
     *
     * @param id id
     * @return class SkillLibraryBO
     * @throws SerException
     */
    default List<HistoryAppraiseBO> historyAppraise(String id) throws SerException {
        return null;
    }

=======
>>>>>>> Stashed changes

}