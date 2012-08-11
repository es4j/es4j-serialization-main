package org.es4j.serialization.dotnet;

import org.es4j.dotnet.streams.Stream;
import org.es4j.util.GenericType;


public class BinaryFormatter implements IFormatter {

    public BinaryFormatter() {
    }

    @Override
    public <T> void serialize(Stream output, T graph) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T deserialize(Stream input, GenericType<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
