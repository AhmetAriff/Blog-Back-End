package dev.arif.blogbackend.S3;

public interface S3Service {

    void putObject(String key,byte[] file);

    byte[] getObject(String key);
}
