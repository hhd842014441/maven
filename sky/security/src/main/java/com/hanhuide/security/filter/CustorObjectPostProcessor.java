package com.hanhuide.security.filter;//package com.hanhuide.core.filter;
//
//import com.hanhuide.core.handler.CustomAccessDecisionManager;
//import com.hanhuide.core.handler.CustomFilterInvocationSecurityMetadataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
///**
// * @program: maven
// * @description:
// * @author: 韩惠德
// * @create: 2019-12-31 16:20
// * @version: 1.0
// **/
//public class CustorObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
//    @Autowired
//    private CustomFilterInvocationSecurityMetadataSource securityMetadataSource;
//    @Autowired
//    private CustomAccessDecisionManager decisionManager;
//
//    @Override
//    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//        o.setSecurityMetadataSource(securityMetadataSource); //动态获取url权限配置
//        o.setAccessDecisionManager(decisionManager); //权限判断
//        return o;
//    }
//}
