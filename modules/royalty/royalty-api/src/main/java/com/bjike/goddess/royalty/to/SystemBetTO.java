package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.dto.SystemBetDTO;

import java.util.List;

/**
 * 体系间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetTO extends BaseTO {

    /**
     * 体系间对赌表A
     */
    private SystemBetATO systemBetATO;


    public SystemBetATO getSystemBetATO() {
        return systemBetATO;
    }

    public void setSystemBetATO(SystemBetATO systemBetATO) {
        this.systemBetATO = systemBetATO;
    }

}