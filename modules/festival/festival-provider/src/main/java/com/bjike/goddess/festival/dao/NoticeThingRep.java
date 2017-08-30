package com.bjike.goddess.festival.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.entity.NoticeThing;

/**
 * 注意事项持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NoticeThingRep extends JpaRep<NoticeThing, NoticeThingDTO> {

}