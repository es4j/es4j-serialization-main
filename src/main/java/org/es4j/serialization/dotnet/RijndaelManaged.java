package org.es4j.serialization.dotnet;

public class RijndaelManaged implements AutoCloseable {

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setKey(byte[] encryptionKey) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setMode(CipherMode cipherMode) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void generateIV() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public ICryptoTransform createEncryptor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public byte[] getIV() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public ICryptoTransform createDecryptor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setIV(byte[] initVectorFromStream) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
