package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Image image = new Image();
        image.loadImage("src/main/resources/task9.jpg");
        image.increaseBrightness(50);
        image.brightnessWithThreads(50);
        image.brightnessWithThreadsExec(50);
        image.saveImage("src/main/resources/output.jpg");
    }
}
