package com.mozihe.onnxruntime;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OnnxRuntimeProperties.class)
public class OnnxRuntimeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OrtEnvironment ortEnvironment() {
        return OrtEnvironment.getEnvironment();
    }

    @Bean
    @ConditionalOnMissingBean
    public OrtSession ortSession(OrtEnvironment ortEnvironment, OnnxRuntimeProperties properties) throws OrtException {
        String modelPath = properties.getModelPath();
        OrtSession.SessionOptions opts = new OrtSession.SessionOptions();
        return ortEnvironment.createSession(modelPath, opts);
    }

    @Bean
    @ConditionalOnMissingBean
    public OnnxModelService onnxModelService(OrtEnvironment ortEnvironment, OrtSession ortSession) {
        return new OnnxModelService(ortEnvironment, ortSession);
    }
}
