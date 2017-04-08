package com.bjike.goddess.businessinteraction;

import com.bjike.goddess.businessinteraction.service.InteractionRelationSer;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-28 21:11]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class InteractionRelationTest {

    @Autowired
    private InteractionRelationSer interactionRelationSer;

    @Test
    public void add() throws SerException {
        InteractionRelationTO inter = new InteractionRelationTO();
//        inter.set

    }
}
