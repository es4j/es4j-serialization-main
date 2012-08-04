package org.es4j.serialization;

import org.es4j.dotnet.Stream;
import org.es4j.exceptions.ArgumentException;
import org.es4j.serialization.api.ISerialize;
import org.es4j.serialization.dotnet.*;
import org.es4j.util.GenericType;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;

//using System.IO;
//using System.Security.Cryptography;


public class RijndaelSerializer implements ISerialize {

    private static final ILog logger = LogFactory.buildLogger(RijndaelSerializer.class);
    private static final int  keyLength = 16; // bytes
    private final ISerialize  inner;
    private final byte[]      encryptionKey;

    public RijndaelSerializer(ISerialize inner, byte[] encryptionKey) {
        if (!keyIsValid(encryptionKey, keyLength)) {
            throw new ArgumentException(Messages.InvalidKeyLength(), "encryptionKey");
        }
        this.encryptionKey = encryptionKey;
        this.inner = inner;
    }

    private static boolean keyIsValid(byte[]/*Collection*/ key, int length) {
        return key != null && key.length == length;
    }

    @Override // virtual
    public <T> void serialize(Stream output, T graph) {
        logger.verbose(Messages.SerializingGraph(), graph.getClass().getName());

        try/*using*/ (RijndaelManaged rijndael = new RijndaelManaged()) {
            rijndael.setKey(this.encryptionKey);
            rijndael.setMode(CipherMode.CBC);
            rijndael.generateIV();

            try/*using*/ (ICryptoTransform encryptor = rijndael.createEncryptor()) {
                try/*using*/ (IndisposableStream wrappedOutput = new IndisposableStream(output)) {
                    try/*using*/ (CryptoStream encryptionStream = new CryptoStream(wrappedOutput, encryptor, CryptoStreamMode.Write)) {
                        wrappedOutput.write(rijndael.getIV(), 0, rijndael.getIV().length);
                        this.inner.serialize(encryptionStream, graph);
                        encryptionStream.flush();
                        encryptionStream.flushFinalBlock();
                    }
                    catch(Exception ex) {
                        throw new UnsupportedOperationException("Not yet implemented");
                    }
                }
                catch(Exception ex) {
                    throw new UnsupportedOperationException("Not yet implemented");
                }
            }
            catch(Exception ex) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        }
        catch(Exception ex) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

    }


    @Override // virtual
    public <T> T deserialize(Stream input, GenericType<T> type) {
        logger.verbose(Messages.DeserializingStream(), type.getClass().getName());

        try/*using*/ (RijndaelManaged rijndael = new RijndaelManaged()) {
            rijndael.setKey(this.encryptionKey);
            rijndael.setIV(getInitVectorFromStream(input, rijndael.getIV().length));
            rijndael.setMode(CipherMode.CBC);

            try/*using*/ (ICryptoTransform decryptor = rijndael.createDecryptor()) {
                try/*using*/ (CryptoStream decryptedStream = new CryptoStream(input, decryptor, CryptoStreamMode.Read)) {
                    return this.inner.deserialize/*<T>*/(decryptedStream, type);
                }
            }
        }
        catch(Exception ex) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    private static byte[] getInitVectorFromStream(Stream encrypted, int initVectorSizeInBytes) {
        byte[] buffer = new byte[initVectorSizeInBytes];
        encrypted.read(buffer, 0, buffer.length);
        return buffer;
    }
}
