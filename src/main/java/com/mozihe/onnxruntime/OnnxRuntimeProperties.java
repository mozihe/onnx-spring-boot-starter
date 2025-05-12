package com.mozihe.onnxruntime;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mozihe.onnxruntime")
public class OnnxRuntimeProperties {

    private String modelPath;

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
