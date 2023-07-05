package dev.arif.blogbackend.S3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3;

    @Value("${bucketName}")
    private String bucketName;

    @Override
    public void putObject(String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build();
        s3.putObject(objectRequest, RequestBody.fromBytes(file));
    }
    @Override
    public byte[] getObject(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3.getObject(getObjectRequest);
        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
