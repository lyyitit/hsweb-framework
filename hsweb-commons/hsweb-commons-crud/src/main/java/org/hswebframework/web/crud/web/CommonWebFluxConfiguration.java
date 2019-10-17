package org.hswebframework.web.crud.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class CommonWebFluxConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommonErrorControllerAdvice commonErrorControllerAdvice(){
        return new CommonErrorControllerAdvice();
    }


    @Bean
    @ConditionalOnProperty(prefix = "hsweb.webflux",name = "response-wrapper",havingValue = "enabled",matchIfMissing = true)
    public ResponseMessageWrapper responseMessageWrapper(ServerCodecConfigurer codecConfigurer,
                                                         RequestedContentTypeResolver resolver,
                                                         ReactiveAdapterRegistry registry){
        return new ResponseMessageWrapper(codecConfigurer.getWriters(),resolver,registry);
    }
}