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
    byte[] audio = ttsService.tts(input, platform, voice_id);
    if (audio != null) {
      Resps.bytesWithContentType(response, audio, "audio/wav");
      response.setHasGzipped(true);
      return response;
    } else {
      return response.error("Failed");
    }
  }
  
  public HttpResponse test(HttpRequest request) {
    HttpResponse response = TioRequestContext.getResponse();
    String input = "Today as always, men fall into two groups: slaves and free men. Whoever does not have two-thirds of his day for himself, is a slave, whatever he may be: a statesman, a businessman, an official, or a scholar.";
    String platform = request.getParam("platform");
    String voice_id = request.getParam("voice_id");
    byte[] audio = ttsService.tts(input, platform, voice_id);
    if (audio != null) {
      Resps.bytesWithContentType(response, audio, "audio/wav");
      response.setHasGzipped(true);
      return response;
    } else {
      return response.error("Failed");
    }
  }

  
  
}
