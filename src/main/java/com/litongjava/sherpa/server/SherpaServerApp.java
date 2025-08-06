package com.litongjava.sherpa.server;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class SherpaServerApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SherpaAppConfig uniAiAppConfig = new SherpaAppConfig();
    TioApplication.run(SherpaServerApp.class, uniAiAppConfig, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}