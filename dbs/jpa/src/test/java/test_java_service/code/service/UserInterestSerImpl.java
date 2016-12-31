package test_java_service.code.service;

import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import org.springframework.stereotype.Service;
import test_java_service.code.dto.UserInterestDto;
import test_java_service.code.entity.UserInterest;

/**
 * Created by lgq on 16-10-13.
 */
@Service
public class UserInterestSerImpl extends ServiceImpl<UserInterest, UserInterestDto> implements IUserInterestSer {

}
