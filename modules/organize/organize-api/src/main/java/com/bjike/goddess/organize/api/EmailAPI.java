package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.EmailBO;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.to.EmailTO;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public interface EmailAPI {
    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    void add(EmailTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(EmailTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<EmailBO> list(EmailDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    EmailBO one(String id) throws SerException;

    /**
     * 定时发送邮件
     *
     * @throws SerException
     */
    void send() throws SerException;

    /**
     * 总条数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(EmailDTO dto) throws SerException;
}
