package com.bjike.goddess.task.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import com.bjike.goddess.task.config.AppRoot;
import org.springframework.stereotype.Component;

/**
 * 扫描entity
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-11 17:15]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppEntityToScan extends EntityToScanImpl<AppRoot> {
}
