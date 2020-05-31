package com.zzgs.springbootdemo.Config;


import com.zzgs.springbootdemo.Realm.ShiroRealm;
import com.zzgs.springbootdemo.Bean.Jurisdiction;
import com.zzgs.springbootdemo.Service.JurisdictionService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:   Tang
 * Date:     2019/12/30 11:25
 * Description: Shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Autowired
    JurisdictionService jurisdictionService;


    /**
     * 配置资源的访问权限
     *
     * @param securityManager 管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilterFactoryBean")
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<Jurisdiction> jurisdictionList = jurisdictionService.findAll();
        for (Jurisdiction jurisdiction : jurisdictionList) {
            filterChainDefinitionMap.put(jurisdiction.getJurisdictionResources(), jurisdiction.getJurisdictionValue());
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 将Realm设置到SecurityManager中
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(shiroRealm());
        return defaultSecurityManager;
    }

    /**
     * 创建ShiroRealm
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 设置加密规则
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(10);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    /**
     * 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
