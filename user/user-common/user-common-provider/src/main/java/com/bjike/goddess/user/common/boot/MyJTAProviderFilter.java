package com.bjike.goddess.user.common.boot;

import com.dounine.corgi.jta.impl.JTAProviderFilterImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 2016/12/19.
 */
@Component
@Primary
public class MyJTAProviderFilter extends JTAProviderFilterImpl {
}
