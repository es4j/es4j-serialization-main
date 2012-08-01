package org.es4j.serialization;

import org.es4j.serialization.api.IDocumentSerializer;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;


public class DocumentObjectSerializer implements IDocumentSerializer {

    private static final ILog logger = LogFactory.buildLogger(DocumentObjectSerializer.class);

    @Override
    public <T> Object serialize(T graph) {
        logger.verbose(Messages.SerializingGraph(), graph.getClass().getName());
        return graph;
    }

    @Override
    public <T> T deserialize(Object document) {
        T object = null;
        logger.verbose(Messages.DeserializingStream(), object.getClass().getName());
        object = (T) document;
        return object;
    }
}
