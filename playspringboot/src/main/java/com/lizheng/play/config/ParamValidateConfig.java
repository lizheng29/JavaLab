package com.lizheng.play.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 李政
 * 2017/12/13
 */
@Configuration
public class ParamValidateConfig {

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(60);

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }


    /**
     * 加这个bean是为了验证方法里单个的参数
     * @return MethodValidationPostProcessor
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        // 这里是为了自定义ValidationMessages文件的路径等配置，如果想使用默认的话，可以不设置
        methodValidationPostProcessor.setValidator(localValidatorFactoryBean().getValidator());
        return methodValidationPostProcessor;
    }

}
