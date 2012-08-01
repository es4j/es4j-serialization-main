package org.es4j.serialization.dotnet;

import org.es4j.dotnet.Stream;
import org.es4j.serialization.IndisposableStream;


public class CryptoStream extends Stream {

    public CryptoStream(IndisposableStream wrappedOutput, ICryptoTransform encryptor, CryptoStreamMode cryptoStreamMode) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public CryptoStream(Stream input, ICryptoTransform decryptor, CryptoStreamMode cryptoStreamMode) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void flushFinalBlock() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
