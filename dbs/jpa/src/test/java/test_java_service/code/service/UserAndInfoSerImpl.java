package test_java_service.code.service;

import com.bjike.goddess.dbs.jpa.service.NativeServiceImpl;
import org.springframework.stereotype.Service;
import test_java_service.code.entity.UserAndInfo;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-27 10:37]
 * @Description: [原生sql查询映射业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserAndInfoSerImpl extends NativeServiceImpl<UserAndInfo> implements IUserAndInfoService {

}
