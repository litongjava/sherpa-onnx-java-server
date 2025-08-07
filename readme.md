# sherpa-onnx-java-server

## Sherpa-ONNX Java 服务安装与使用指南

本文档介绍如何在不同操作系统上安装 ONNX Runtime、下载模型，并启动 Sherpa-ONNX Java 服务进行测试。

---

### 一、安装 ONNX Runtime

#### 1. Windows 10

Windows 10 系统自带 ONNX Runtime，无需额外安装。

#### 2. Linux

Sherpa-ONNX 并不包含 ONNX Runtime，需要手动下载并配置：

1. 从微软官方 GitHub Releases 下载 Linux 64 位二进制包：

   ```bash
   wget https://github.com/microsoft/onnxruntime/releases/download/v1.17.1/onnxruntime-linux-x64-1.17.1.tgz
   tar -xzf onnxruntime-linux-x64-1.17.1.tgz
   ```
2. 将解压后的 `libonnxruntime.so` 文件复制到系统库目录，并创建软链接：

   ```bash
   sudo cp onnxruntime-linux-x64-1.17.1/lib/libonnxruntime.so* /usr/local/lib/
   sudo ln -sf /usr/local/lib/libonnxruntime.so.1.17.1 /usr/local/lib/libonnxruntime.so
   ```
3. 更新共享库缓存并验证安装：

   ```bash
   sudo ldconfig
   ldconfig -p | grep onnxruntime
   ```

#### 3. macOS

Sherpa-ONNX 同样不包含 ONNX Runtime，需要从官方获取并配置：

1. 下载 macOS ARM64 版本二进制包：

   ```bash
   wget https://github.com/microsoft/onnxruntime/releases/download/v1.17.1/onnxruntime-osx-arm64-1.17.1.tgz
   tar -xzf onnxruntime-osx-arm64-1.17.1.tgz
   ```
2. 将 `libonnxruntime.1.17.1.dylib` 复制到 `/usr/local/lib`：

   ```bash
   sudo cp onnxruntime-osx-arm64-1.17.1/lib/libonnxruntime.1.17.1.dylib /usr/local/lib/
   ```
3. 将 `/usr/local/lib` 添加到 `dyld` 的搜索路径：

   ```bash
   export DYLD_LIBRARY_PATH=/usr/local/lib:$DYLD_LIBRARY_PATH
   ```
4. 使用 `otool` 验证：

   ```bash
   otool -L /Users/ping/lib/darwin_arm64/libsherpa-onnx-jni.dylib
   ```
---

### 二、下载模型

以英文 Kokoro v0.19 模型为例，下载并解压至本地：

1. 在浏览器中打开模型页面：

[https://k2-fsa.github.io/sherpa/onnx/tts/pretrained\_models/kokoro.html](https://k2-fsa.github.io/sherpa/onnx/tts/pretrained_models/kokoro.html)

2. 从 GitHub Releases 获取模型包：  
   ```bash
   wget https://github.com/k2-fsa/sherpa-onnx/releases/download/tts-models/kokoro-en-v0_19.tar.bz2
   ```

3. 解压至指定目录（如 `~/models/`）：

   ```bash
   tar -xjf kokoro-en-v0_19.tar.bz2 -C ~/models/
   ```
4. 检查目录结构，确保包含以下文件：

   ```plain
   LICENSE
   README.md
   espeak-ng-data/        # 语音数据目录
   model.onnx            # TTS 模型
   tokens.txt           # token 映射
   voices.bin           # voice embedding
   ```
5. 在 Java 程序中，将上述路径配置为模型文件和依赖目录的位置，支持相对或绝对路径。

---

### 三、启动测试

1. 使用以下命令启动 Java 服务：

   ```bash
   java -jar sherpa-onnx-java-server-1.0.0.jar --sherpa.pool.size=1
   ```
2. 在浏览器中访问测试页面：
[http://localhost/tts/test](http://localhost/tts/test)


3. 根据页面提示输入文本，验证 TTS 生成效果。

---

至此，您已完成 Sherpa-ONNX Java 服务的安装、模型下载及测试启动。如有任何问题，请参考官方文档或在社区讨论。
