package ru.ant.chunked4j.sample;

import java.io.Serializable;

class SimpleFileResult implements Serializable {
    private String filePath;

    SimpleFileResult() {
    }

    String getFilePath() {
        return filePath;
    }
    void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
