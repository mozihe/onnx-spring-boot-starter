# onnxruntime-spring-boot-starter

一个轻量级的 Spring Boot 起步依赖，用于在 Java 应用中快速集成 ONNX Runtime，支持加载和推理 `.onnx` 模型。

> 🎯 作者：[mozihe](https://github.com/mozihe)

---

## ✨ 特性

- ✅ 开箱即用的 Spring Boot Starter 接入方式
- 🔧 自动配置 ONNX Runtime 环境
- 📦 支持 Java 17 与 Spring Boot 3.x
- 🚀 快速加载 ONNX 模型并运行推理

---

## 🧱 安装

**方式一：本地引入**

```bash
git clone https://github.com/mozihe/onnxruntime-spring-boot-starter.git  
cd onnxruntime-spring-boot-starter  
mvn install
```

**方式二：Maven 引用（等待后续发布）**

```xml
<dependency>  
    <groupId>com.mozihe</groupId>  
    <artifactId>onnx-spring-boot-starter</artifactId>  
    <version>1.0.0</version>  
</dependency>
```


---

## 🛠️ 使用示例

```java
package com.mozihe.onnxruntime;

import ai.onnxruntime.OrtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onnx")
public class OnnxModelController {

    private final OnnxModelService onnxModelService;

    @Autowired
    public OnnxModelController(OnnxModelService onnxModelService) {
        this.onnxModelService = onnxModelService;
    }

    @PostMapping("/predict")
    public List<Float> predict(@RequestBody List<Float> input) throws OrtException {
        return onnxModelService.runModel(input);
    }
}

```


---

## 📋 环境要求

- Java 17 及以上版本
- Spring Boot 3.0+
- ONNX Runtime Java 后端

---

## 🔮 未来计划

- [x] Spring Boot 自动配置
- [ ] ONNX 模型自动热加载
- [ ] GPU 支持配置入口
- [x] 发布到 Maven Central / OSSRH

---

## 📄 许可证

License: [MIT](./LICENSE)

本项目基于 MIT 协议开源，详见 LICENSE。

---

## 🙌 鸣谢

感谢 ONNX Runtime 团队与 Spring Boot 社区。
