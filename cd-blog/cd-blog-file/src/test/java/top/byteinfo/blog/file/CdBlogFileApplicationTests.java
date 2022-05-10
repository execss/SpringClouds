package top.byteinfo.blog.file;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.file.config.OssConfig;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class CdBlogFileApplicationTests {

    @Test
    void contextLoads() {
        String s = "/";
        boolean equals = s.equals("//");
        System.out.println(equals);

        boolean b = "/" == "//";
//        "/" == "/"


    }


    @Test
    void TestAliYunOSSUPString() throws Exception {
        // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
        // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
        // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
        // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
        //    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
        // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
        // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
        String accessKeyId = "LTAI5tJCKM3fKrh1Na8YBTLF";
        String accessKeySecret = "J3ZK4c3jlQEexyXhXwc304cCE0kHKc";
        // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
        // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
        String bucketName = "oss--files";
        String objectName = "exampledir/exampleobject1111.txt";

        List<String> stringList = Files.lines(Paths.get("C:\\Intel\\AccessKey.csv"))
                .collect(Collectors.toList());

        String[] strings = stringList.get(1).split(",");
        log.info("\n" + stringList.get(0) + "\n" + stringList.get(1));
        log.info("\n" + strings[0] + "\n" + strings[1]);
        OSS ossClient = new OSSClientBuilder().build(endpoint, strings[0], strings[1]);


        try {
            String content = "Hello OSS1";
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
//            if (ossClient.doesBucketExist(bucketName)) {
//                System.out.println("您已经创建Bucket：" + bucketName + "。");
//            } else {
//                System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
//                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
//                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
//                ossClient.createBucket(bucketName);
//            }


            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));


            // 上传字符串。
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);


            //上传Byte数组
//            byte[] content = "Hello OSS".getBytes();
//            // 创建PutObject请求。
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            //上传网络流
//            InputStream inputStream = new URL(url).openStream();
//            // 创建PutObject请求。
//            ossClient.putObject(bucketName, objectName, inputStream);

            //文件流
//            InputStream inputStream = new FileInputStream(filePath);
//            // 创建PutObject请求。
//            ossClient.putObject(bucketName, objectName, inputStream);

            //            // 上传文件。
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
//            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//            // ObjectMetadata metadata = new ObjectMetadata();
//            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//            // metadata.setObjectAcl(CannedAccessControlList.Private);
//            // putObjectRequest.setMetadata(metadata);
//            ossClient.putObject(putObjectRequest);


            // 读取上传回调返回的消息内容。
//            byte[] buffer = new byte[1024];
//            String eTag = putObjectResult.getETag();
//            String versionId = putObjectResult.getVersionId();
//
//
//
//            putObjectResult.getResponse().getContent().read(buffer);
//            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
//            putObjectResult.getResponse().getContent().close();
        } catch (
                OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

//        BucketInfo bucketInfo = ossClient.getBucketInfo(bucketName);
//        System.out.println("Bucket " + bucketName + "的信息如下：");
//        System.out.println("\t数据中心：" + bucketInfo.getBucket().getLocation());
//        System.out.println("\t创建时间：" + bucketInfo.getBucket().getCreationDate());
//        System.out.println("\t用户标志：" + bucketInfo.getBucket().getOwner());

    }


    @Test
    void TestAliYunOSSUP() {

    }

    @Autowired
    OssConfig ossConfig;

    @Test
    void csvIO()  {

        String objectName = "MultipartFile/" + "name.pdf";

        try (
                BufferedInputStream bufferedInputStream =
                        new BufferedInputStream(
                                Files.newInputStream(new File("C:\\Intel\\name.pdf").toPath())
                        );
        ) {
            OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), objectName, bufferedInputStream);
            ossClient.putObject(putObjectRequest);
        }catch (Exception e){
            throw new RuntimeException();
        }


    }

}
