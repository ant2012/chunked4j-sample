package ru.ant.chunked4j.sample;

import ru.ant.chunked4j.ChunkInputStream;
import ru.ant.chunked4j.ChunkStreamReaderFactory;

import java.io.File;

public class FileWriterFactory implements ChunkStreamReaderFactory<SimpleFileWriter> {
    private final String root = "/chunked/repo";

    FileWriterFactory() {
        boolean mkdirs = new File(root).mkdirs();
    }

    @Override
    public SimpleFileWriter create(ChunkInputStream stream) {
        return new SimpleFileWriter(stream, root);
    }
}
