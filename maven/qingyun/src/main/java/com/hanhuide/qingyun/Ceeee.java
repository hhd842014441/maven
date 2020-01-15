//package com.hanhuide.qingyun;
//
//import com.qingcloud.sdk.config.EnvContext;
//
//import java.io.File;
//
//public class Ceeee {
//    public static void main(String[] args) {//pek3b
//        EnvContext context = new EnvContext("PUPBHLORHNUOUUZBWEHX", "ZXEvKvtsspzfLBwzyVl01bok8UE5eHrhoVM78jPT");
//        try {
//            // 第一步: 创建 EvnContext 并设置 zone 和 bucket
//            Bucket bucket = new Bucket(context, "zone 名称", "bucket 名称");
//
//            Bucket.PutObjectInput putObjectInput = new Bucket.PutObjectInput();
//            File file = new File("/文件路径/文件名");
//            putObjectInput.setBodyInputFile(file);
//            putObjectInput.setContentLength(file.length());
//
//            // bucket.putObject("对象名称", putObjectInput);
//
//            // 第二步：获取 RequestHandler，正常 bucket.putObject("对象名称", putObjectInput); 就完成操作
//            RequestHandler reqHandler = bucket.putObjectAsyncRequest(objectName, putObjectInput,
//                    new ResponseCallBack<PutObjectOutput>() {
//                        @Override
//                        public void onAPIResponse(PutObjectOutput output) {
//                            System.out.println("Message = " + output.getMessage());
//                            System.out.println("RequestId = " + output.getRequestId());
//                            System.out.println("Code = " + output.getCode());
//                            System.out.println("StatueCode = " + output.getStatueCode());
//                            System.out.println("Url = " + output.getUrl());
//                        }
//                    });
//
//            // 第三步：获取 strToSignature。将这个字符串发送到用户的 server 端。
//            String strToSignature = reqHandler.getStringToSignature();
//
//            // 第四步：serverAuthorization。server 端处理返回信息，服务端参考如下代码：
//            // String serverAuthorization = QSSignatureUtil.generateSignature("您的 secretKey", strToSignature);
//            String serverAuthorization = "您从服务端获取到的签名字符串";
//
//            // 第五步：将计算的签名设置到 request 中
//
//            // 因客户端跟服务端通讯可能有时间差，而签名计算结果跟时间密切相关，因此需要将服务端计算签名时所用的时间设置到 request 中
//            // 服务端代码示例：return QSSignatureUtil.formatGmtDate(new Date());
//            String gmtTime = "您从服务端获取的 GMT 时间";
//            reqHandler.getBuilder().setHeader(QSConstant.HEADER_PARAM_KEY_DATE, gmtTime);
//
//            reqHandler.setSignature("您的 accessKey", serverAuthorization);
//
//            // 第六步：发送请求。异步请求使用 sendAsync() 方法。同步请求使用 send() 方法。
//            reqHandler.sendAsync();
//
//        } catch (QSException e) {
//            e.printStackTrace();
//        }
//    }
//}
