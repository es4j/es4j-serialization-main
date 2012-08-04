package org.es4j.serialization;

import java.io.IOException;
import org.es4j.dotnet.Stream;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;
import org.es4j.serialization.api.ISerialize;
import org.es4j.serialization.dotnet.CompressionMode;
import org.es4j.serialization.dotnet.DeflateStream;
import org.es4j.util.GenericType;
//using System.IO;
//using System.IO.Compression;


public class GzipSerializer implements ISerialize {

    private static final ILog logger = LogFactory.buildLogger(GzipSerializer.class);
    private final ISerialize  inner;

    public GzipSerializer(ISerialize inner) {
        this.inner = inner;
    }

    @Override  // virtual
    public <T> void serialize(Stream output, T graph) {
        logger.verbose(Messages.SerializingGraph(), graph.getClass().getName());
        try/*using*/ (DeflateStream compress = new DeflateStream(output, CompressionMode.Compress, true)) {
            this.inner.serialize(compress, graph);
        }
        catch(IOException ex) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override  // virtual
    public <T> T deserialize(Stream input, GenericType<T> type) {
        T object = null;
        logger.verbose(Messages.DeserializingStream(), object.getClass().getName());
        try/*using*/ (DeflateStream decompress = new DeflateStream(input, CompressionMode.Decompress, true)) {
            return this.inner.deserialize(decompress, type);
        }
        catch(IOException ex) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}