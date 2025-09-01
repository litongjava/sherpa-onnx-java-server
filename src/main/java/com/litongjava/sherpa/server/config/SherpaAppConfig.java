package com.litongjava.sherpa.server.config;

import java.io.File;

import com.litongjava.context.BootConfiguration;
import com.litongjava.sherpa.server.handler.TTSHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

public class SherpaAppConfig implements BootConfiguration {

  @Override
  public void config() throws Exception {
    new File("cache/audio").mkdirs();
    // 获取 HTTP 请求路由器
    TioBootServer server = TioBootServer.me();
    HttpRequestRouter r = server.getRequestRouter();

    if (r != null) {
      TTSHandler ttsHandler = new TTSHandler();
      r.add("/tts", ttsHandler::index);
      r.add("/tts/test", ttsHandler::testEn);
      r.add("/tts/testEn", ttsHandler::testEn);
      r.add("/tts/testCn", ttsHandler::testCn);
    }
  }
}
