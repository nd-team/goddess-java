package com.bjike.goddess.staffentry.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;

/**
 * 入职登记持久化接口, 继承基类可使用ｊｐａ命名查询
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:55]
 * @Description: [入职登记持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IEntryRegister extends JpaRep<EntryRegister ,EntryRegisterDTO> {
}
