package com.litongjava.sherpa.server.local;

import org.junit.Test;

import com.k2fsa.sherpa.onnx.GeneratedAudio;
import com.litongjava.tio.utils.environment.EnvUtils;

public class PooledNonStreamingTtsMatchaZhTest {

  @Test
  public void test() {
    EnvUtils.load();

    String text = "某某银行的副行长和一些行政领导表示，他们去过长江" + "和长白山; 经济不断增长。" + "2024年12月31号，拨打110或者18920240511。" + "123456块钱。";

    int sid = 0;
    float speed = 1.0f;
    long start = System.currentTimeMillis();
    GeneratedAudio audio = null;
    try {
      audio = PooledNonStreamingTtsMatchaZh.synthesize(text, sid, speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return;
    }
    long stop = System.currentTimeMillis();

    float timeElapsedSeconds = (stop - start) / 1000.0f;

    float audioDuration = audio.getSamples().length / (float) audio.getSampleRate();
    float real_time_factor = timeElapsedSeconds / audioDuration;

    String waveFilename = "tts-matcha-zh.wav";
    audio.save(waveFilename);
    System.out.printf("-- elapsed : %.3f seconds\n", timeElapsedSeconds);
    System.out.printf("-- audio duration: %.3f seconds\n", audioDuration);
    System.out.printf("-- real-time factor (RTF): %.3f\n", real_time_factor);
    System.out.printf("-- text: %s\n", text);
    System.out.printf("-- Saved to %s\n", waveFilename);

  }

}
