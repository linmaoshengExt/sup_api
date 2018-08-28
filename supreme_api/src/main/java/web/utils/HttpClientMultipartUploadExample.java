package web.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientMultipartUploadExample {
	private static String uri = "http://localhost:8888/zhph_commonServices/webservice/email/sendEmail";//post请求url
    private static ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));//参数的文本类型编码
    public static void main(String[] args) throws IOException {
        //1：创建HttpClient 对象（创建客户端）
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2：创建请求方式（web中的请求方式method）
        HttpPost post = new HttpPost(uri);
        //3：创建MultipartEntityBuilder对象。MultipartEntityBuilder：多部件实体构建
        MultipartEntityBuilder builder;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式
        //4：包装要发送的数据（文件）
        //4.1：获取本地的文件
        List<File> fileList = new ArrayList<File>();
        File file1 = new File("d:\\1.jpg");
        File file2 = new File("d:\\2.jpg");
        fileList.add(file1);
        fileList.add(file2);
        for (int i = 0; i < fileList.size(); i++) {
            builder.addBinaryBody("files", fileList.get(i), contentType, fileList.get(i).getName());
        }
        //4.2：绑定需要发送的文本参数
        builder.addTextBody("toWho", "18263047573@163.com");
        builder.addTextBody("subject", "主题", contentType);
//        builder.addTextBody("content", "发送内容", contentType);
        builder.addTextBody("login_code", "loanaudit");
        builder.addTextBody("login_pw", "6dc37220542bd0cea5614a7855e72118");
        //5： 生成 HTTP POST 实体
        HttpEntity entity = builder.build();
        //6：设置请求的实体(参数)
        post.setEntity(entity);
        //7：让客户端执行请求（带有数据的请求），得到的是CloseableHttpResponse对象（响应对象）
        CloseableHttpResponse response = httpClient.execute(post);
        //8：通过响应对象获取响应码
        int code = response.getStatusLine().getStatusCode();
        //9：如果响应码为200（成功响应码），则获取服务器返回的数据
        if (code == 200) {
            try {
                //9.1：获取HttpEntity对象（通过响应来获取）
                HttpEntity entity2 = response.getEntity();
                //10：使用EntityUtils工具类，将获取到的数据（实体）转换为字节数组形式，任何文件都可以以字节的形式保存
                byte[] b = EntityUtils.toByteArray(entity2);
                //输出内容
                System.out.println(new String(b, "utf-8"));
                //消耗掉response
                EntityUtils.consume(entity2);
            } finally {
                if (null != response) {
                    response.close();
                }
            }
        } else {
            System.err.println("返回值:\t" + code);
            if (null != response) {
                response.close();
            }
        }
    }
}
