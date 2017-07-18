package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 体系间对赌表业务传输对象
 * @Author:			[ xiazhili ]
 * @Date:			[  2017-07-11 11:31 ]
 * @Description:	[ 体系间对赌表业务传输对象 ]
 * @Version:		[ v1.0.0 ]
 * @Copy:   		[ com.bjike ]
 */
public class SystemBetVO {
    /**
     * 体系间对赌表A
     */
    private SystemBetAVO systemBetAVO;
    /**
     * 体系间对赌表B
     */
    private List<SystemBetBVO> systemBetBVOS;
    /**
     * 体系间对赌表C
     */
    private List<SystemBetCVO> systemBetCVOS;
    /**
     * 体系间对赌表D
     */
    private List<SystemBetDVO> systemBetDVOS;

    public SystemBetAVO getSystemBetAVO() {
        return systemBetAVO;
    }

    public void setSystemBetAVO(SystemBetAVO systemBetAVO) {
        this.systemBetAVO = systemBetAVO;
    }

    public List<SystemBetBVO> getSystemBetBVOS() {
        return systemBetBVOS;
    }

    public void setSystemBetBVOS(List<SystemBetBVO> systemBetBVOS) {
        this.systemBetBVOS = systemBetBVOS;
    }

    public List<SystemBetCVO> getSystemBetCVOS() {
        return systemBetCVOS;
    }

    public void setSystemBetCVOS(List<SystemBetCVO> systemBetCVOS) {
        this.systemBetCVOS = systemBetCVOS;
    }

    public List<SystemBetDVO> getSystemBetDVOS() {
        return systemBetDVOS;
    }

    public void setSystemBetDVOS(List<SystemBetDVO> systemBetDVOS) {
        this.systemBetDVOS = systemBetDVOS;
    }
}
