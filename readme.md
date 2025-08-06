```
java -jar sherpa-onnx-java-server-1.0.0.jar --sherpa.pool.size=1
```

## linux

获取 ONNX Runtime 的 so 文件
Sherpa-ONNX 并不自带 ONNX Runtime，你需要从微软的 GitHub Releases 下载对应版本的 Linux 64 位二进制包：

```
wget https://github.com/microsoft/onnxruntime/releases/download/v1.17.1/onnxruntime-linux-x64-1.17.1.tgz
tar -xzf onnxruntime-linux-x64-1.17.1.tgz
```
把 so 文件放到系统库目录
将解压出来的 libonnxruntime.so 复制到 /usr/local/lib（或者其他系统会搜索的目录）：

```
sudo cp onnxruntime-linux-x64-1.17.1/lib/libonnxruntime.so* /usr/local/lib/
sudo ln -sf /usr/local/lib/libonnxruntime.so.1.17.1 /usr/local/lib/libonnxruntime.so
sudo ldconfig
ldconfig -p | grep onnxruntime
```

启动
```
java -jar sherpa-onnx-java-server-1.0.0.jar --sherpa.pool.size=1
```
浏览器打开测试
http://localhost/tts/test

## MacOS

获取 ONNX Runtime 的 dylib 文件
Sherpa-ONNX 并不自带 ONNX Runtime，你需要从微软的 GitHub Releases 下载对应版本的 MacOS 64 位二进制包：

```
wget https://github.com/microsoft/onnxruntime/releases/download/v1.17.1/onnxruntime-osx-arm64-1.17.1.tgz
tar -xzf onnxruntime-osx-arm64-1.17.1.tgz
sudo cp onnxruntime-osx-arm64-1.17.1/lib/libonnxruntime.1.17.1.dylib /usr/local/lib/
```

把 /usr/local/lib 加入 dyld 的搜索路径
```
export DYLD_LIBRARY_PATH=/usr/local/lib:$DYLD_LIBRARY_PATH
```
检查加载
```
otool -L /Users/ping/lib/darwin_arm64/libsherpa-onnx-jni.dylib
```