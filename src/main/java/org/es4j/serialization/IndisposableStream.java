package org.es4j.serialization;

import org.es4j.dotnet.SeekOrigin;
import org.es4j.dotnet.Stream;

//using System.IO;

/// <summary>
/// Helper class stolen from Jonathan Oliver
/// </summary>
public class IndisposableStream extends Stream {

    private final Stream stream;

    public IndisposableStream(Stream stream) {
        this.stream = stream;
    }

    @Override
    protected void dispose(boolean disposing) {
        // no-op
    }

    @Override
    public void close() {
        // no-op
    }

    @Override
    public boolean canRead() {
        return this.stream.canRead();
    }

    @Override
    public boolean canSeek() {
        return this.stream.canSeek();
    }

    @Override
    public boolean canWrite() {
        return this.stream.canWrite();
    }

    @Override
    public long length() {
        return this.stream.length();
    }

    @Override
    public long getPosition() {
        return this.stream.getPosition();
    }

    @Override
    public void setPosition(long position) {
        this.stream.setPosition(position);
    }

    @Override
    public void flush() {
        this.stream.flush();
    }

    @Override
    public long seek(long offset, SeekOrigin origin) {
        return this.stream.seek(offset, origin);
    }

    @Override
    public void setLength(long value) {
        this.stream.setLength(value);
    }

    @Override
    public void read(byte[] buffer, int offset, int count) {
        this.stream.read(buffer, offset, count);
    }

    @Override
    public void write(byte[] buffer, int offset, int count) {
        this.stream.write(buffer, offset, count);
    }
}