package Weflo.backend.global.aws.s3;

import Weflo.backend.global.config.AmazonConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file) {
        System.out.println("keyName = " + keyName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        }catch (IOException e){
//            throw new ImageException(ErrorStatus.ERROR_AT_AMAZONS3_MANAGER_UPLOADFILE);
//            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }
        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

//    public String generatePartsKeyName(Uuid uuid) {
//        return amazonConfig.getPartsPath() + '/' + uuid.getUuid();
//    }

}
