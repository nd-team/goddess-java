package com.bjike.goddess.abilitydisplay.config;

import com.bjike.goddess.common.jpa.boot.EntityToScan;
import com.bjike.goddess.common.jpa.boot.JpaComponents;
import org.springframework.stereotype.Component;

/**
 * @Author: [sunft]
 * @Date: [2016-11-23 15:47]
 * @Description: [entity扫描类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppComponents extends JpaComponents implements EntityToScan<AppRoot> {
}
