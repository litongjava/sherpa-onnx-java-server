package com.litongjava.sherpa.server.handler;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.sherpa.server.service.TTSService;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

public class TTSHandler {
  TTSService ttsService = Aop.get(TTSService.class);

  public HttpResponse index(HttpRequest request) {
    HttpResponse response = TioRequestContext.getResponse();
    String input = request.getParam("input");
    String platform = request.getParam("platform");
    String voice_id = request.getParam("voice_id");
    Boolean useLocal = request.getBool("use_local");
    byte[] audio = ttsService.tts(input, platform, voice_id, useLocal);
    if (audio != null) {
      Resps.bytesWithContentType(response, audio, "audio/mp3");
      response.setSkipGzipped(true);
      return response;
    } else {
      return response.error("Failed");
    }
  }

  public HttpResponse testEn(HttpRequest request) {
    HttpResponse response = TioRequestContext.getResponse();
    String input = "Today as always, men fall into two groups: slaves and free men. Whoever does not have two-thirds of his day for himself, is a slave, whatever he may be: a statesman, a businessman, an official, or a scholar.";
    String platform = request.getParam("platform");
    String voice_id = request.getParam("voice_id");
    byte[] audio = ttsService.tts(input, platform, voice_id, true);
    if (audio != null) {
      Resps.bytesWithContentType(response, audio, "audio/mp3");
      response.setSkipGzipped(true);
      return response;
    } else {
      return response.error("Failed");
    }
  }

  public HttpResponse testCn(HttpRequest request) {
    HttpResponse response = TioRequestContext.getResponse();
    String input = "某某银行的副行长和一些行政领导表示，他们去过长江" + "和长白山; 经济不断增长。" + "2024年12月31号，拨打110或者18920240511。" + "123456块钱。";
    String platform = request.getParam("platform");
    String voice_id = request.getParam("voice_id");
    byte[] audio = ttsService.tts(input, platform, voice_id, true);
    if (audio != null) {
      Resps.bytesWithContentType(response, audio, "audio/mp3");
      response.setSkipGzipped(true);
      return response;
    } else {
      return response.error("Failed");
    }
  }

}
