package ru.ant.chunked4j.sample;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ant.chunked4j.ChunkInputStream;
import ru.ant.chunked4j.ChunkStreamReader;
import ru.ant.chunked4j.ChunkUploadResult;

import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleFileWriter extends ChunkStreamReader<SimpleFileResult> {

    private final Logger log = LogManager.getLogger(getClass());
    private final String root;

    SimpleFileWriter(ChunkInputStream stream, String root) {
        super(stream);
        this.root = root;
    }

    @Override
    public void run() {
        try {
            log.info(String.format("%1$s write started", stream.getFileName()));
            FileOutputStream out = new FileOutputStream(root + "/" + stream.getFileName());
            IOUtils.copyLarge(stream, out);

            stream.close();

            out.flush();
            out.close();
            log.info(String.format("%1$s write finished", stream.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChunkUploadResult<SimpleFileResult> getResult() {
        SimpleFileResult result = new SimpleFileResult();
        result.setFilePath(root + "/" + stream.getFileName());
        return new ChunkUploadResult<>(result);
    }
}
