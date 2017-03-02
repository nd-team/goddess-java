package com.bjike.goddess.card.action;

import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.service.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 远程调用demo
 * 调用前先开启远程模块
 */
@RestController
@RequestMapping("demo/user")
public class UserAction {

    @Autowired
    private CardAPI cardAPI;

    @Autowired
    private UserAPI userAPI;

    /**
     * 本模块业务依赖用户模块
     * @return
     * @throws ActException
     */
    @GetMapping("nickname/{nickname}")
    public ActResult findUserNickname(@PathVariable String nickname) throws ActException {
        try {
            return ActResult.initialize(cardAPI.finUserNickname(nickname));
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 依赖用户模块直接调用该api
     * @return
     * @throws ActException
     */
    @GetMapping("phone/{phone}")
    public ActResult queryPhone(@PathVariable String phone) throws ActException {
        try {
            return ActResult.initialize(userAPI.findByPhone(phone));
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
