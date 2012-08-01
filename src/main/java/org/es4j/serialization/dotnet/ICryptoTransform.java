package org.es4j.serialization.dotnet;

/**
 *
 * @author Esfand
 */
public interface ICryptoTransform extends AutoCloseable {

    public byte[] getIV();

}
