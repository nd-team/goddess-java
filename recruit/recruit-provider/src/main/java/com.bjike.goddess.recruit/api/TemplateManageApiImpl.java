package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.TemplateManageBO;
import com.bjike.goddess.recruit.to.TemplateManageTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("templateManageApiImpl")
public class TemplateManageApiImpl implements TemplateManageAPI {
    @Override
    public List<TemplateManageBO> list(TemplateManageTO templateManageTO) throws SerException {
        return null;
    }

    @Override
    public TemplateManageBO save(TemplateManageTO templateManageTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(TemplateManageTO templateManageTO) throws SerException {

    }
}
