package com.litongjava.sherpa.server.service;

import java.io.File;

import com.k2fsa.sherpa.onnx.GeneratedAudio;
import com.litongjava.sherpa.server.local.PooledNonStreamingTtsKokoroEn;
import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TTSService {

  public byte[] tts(String input, String provider, String voice_id) {

    log.info("input: {}, provider: {}, voice_id: {}", input, provider, voice_id);

    long id = SnowflakeIdUtils.id();
    String wavCacheFilePath = "cache" + File.separator + "audio" + File.separator + id +".wav";
    try {
      GeneratedAudio synthesize = PooledNonStreamingTtsKokoroEn.synthesize(input, 3, 1.0f);
      synthesize.save(wavCacheFilePath);
      return FileUtil.readBytes(new File(wavCacheFilePath));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}