package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.dto.ComCertificateDTO;
import com.bjike.goddess.abilitydisplay.entity.ComCertificate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 公司证书业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 02:45 ]
 * @Description: [ 公司证书业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class ComCertificateSerImpl extends ServiceImpl<ComCertificate, ComCertificateDTO> implements ComCertificateSer {


}