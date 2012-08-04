package org.es4j.serialization;

import org.es4j.serialization.api.IDocumentSerializer;
import org.es4j.serialization.api.ISerialize;
import org.es4j.serialization.api.SerializationExtensions;
import org.es4j.serialization.dotnet.Convert;
import org.es4j.serialization.dotnet.FormatException;
import org.es4j.util.GenericType;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;


public class ByteStreamDocumentSerializer implements IDocumentSerializer {

    private static final ILog       logger = LogFactory.buildLogger(ByteStreamDocumentSerializer.class);
    private final        ISerialize serializer;

    public ByteStreamDocumentSerializer(ISerialize serializer) {
        this.serializer = serializer;
    }

    @Override
    public <T> Object serialize(T graph) {
        logger.verbose(Messages.SerializingGraph(), graph.getClass().getName());
        return SerializationExtensions.serialize(this.serializer, graph);
    }

    @Override
    public <T> T deserialize(Object document, GenericType<T> type) {
        logger.verbose(Messages.DeserializingStream(), type.getClass().getName());

        byte[] bytes = null;
        if(document instanceof String) {
            bytes = fromBase64((String)document);
        }
        else if (document instanceof byte[]) {
            bytes = (byte[])document;
        }
        return SerializationExtensions.deserialize(this.serializer, type, bytes);
    }

    private static byte[] fromBase64(String value) {
        if (value == null || "".equals(value)) {
            return null;
        }

        logger.verbose(Messages.InspectingTextStream());

        try {
            return Convert.fromBase64String(value);
        }
        catch (FormatException ex) {
            return null;
        }
    }
}
