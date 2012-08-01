package org.es4j.serialization.dotnet;

import java.io.IOException;
import org.es4j.dotnet.Stream;


public class DeflateStream extends Stream {

    public DeflateStream(Stream input, CompressionMode mode, boolean bool) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
