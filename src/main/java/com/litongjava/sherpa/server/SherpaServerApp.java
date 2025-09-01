package com.litongjava.sherpa.server;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.sherpa.server.config.AppBootConfig;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class SherpaServerApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    AppBootConfig appBotConfig = new AppBootConfig();
    TioApplication.run(SherpaServerApp.class, appBotConfig, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}