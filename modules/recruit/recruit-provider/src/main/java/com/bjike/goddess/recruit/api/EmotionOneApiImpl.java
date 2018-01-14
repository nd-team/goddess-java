package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.EmotionOneBO;
import com.bjike.goddess.recruit.service.EmotionOneSer;
import com.bjike.goddess.recruit.to.EmotionOneTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 情感标签二级业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("emotionOneApiImpl")
public class EmotionOneApiImpl implements EmotionOneAPI {
    @Autowired
    private EmotionOneSer emotionOneSer;

    @Override
    public List<EmotionOneBO> list() throws SerException {
        return emotionOneSer.list();
    }

    @Override
    public void add(EmotionOneBO bo) throws SerException {
        emotionOneSer.add(bo);
    }

    @Override
    public void delete(String id) throws SerException {
        emotionOneSer.delete(id);
    }

    @Override
    public EmotionOneBO edit(String id) throws SerException {
        return emotionOneSer.edit(id);
    }
}