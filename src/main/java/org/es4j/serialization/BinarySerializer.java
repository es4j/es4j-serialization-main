package org.es4j.serialization;

import org.es4j.dotnet.streams.Stream;
import org.es4j.serialization.api.ISerialize;
import org.es4j.serialization.dotnet.BinaryFormatter;
import org.es4j.serialization.dotnet.IFormatter;
import org.es4j.util.GenericType;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;

//using System.IO;
//using System.Runtime.Serialization;
//using System.Runtime.Serialization.Formatters.Binary;


public class BinarySerializer implements ISerialize {

    private static final ILog       logger    = LogFactory.buildLogger(BinarySerializer.class);
    private        final IFormatter formatter = new BinaryFormatter();

    // virtusl
    @Override
    public <T> void serialize(Stream output, T graph) {
        logger.verbose(Messages.SerializingGraph(), graph.getClass().getName());
        this.formatter.serialize(output, graph);
    }

    @Override
    public <T> T deserialize(Stream input, GenericType<T> type) {
        T object = null;
        logger.verbose(Messages.DeserializingStream(), object.getClass().getName());
        object = this.formatter.deserialize(input, type);
        return object;
    }
}
