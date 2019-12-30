package com.hanhuide.qingyun.config;

import com.qingcloud.sdk.config.EnvContext;
import com.qingcloud.sdk.exception.QCException;
import com.qingcloud.sdk.service.InstanceService;
import com.qingcloud.sdk.service.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-30 11:53
 * @version: 1.0
 **/
public class QingCloudClient {
    private static final Logger logger = LoggerFactory.getLogger(QingCloudClient.class);

    public static void main(String[] args) {
        QingCloudClient qingCloudClient=new QingCloudClient();
        qingCloudClient.describeInstances();
    }
    public void describeInstances() {
        EnvContext context = new EnvContext("SXCTNTFHJDUHKDDEUPTJ", "dLCQENlt26P68aA9TEoRYoNpwOsZbLDbhXItrTqz");
        context.setProtocol("https");
        context.setHost("10.8.149.8");
        context.setPort("80");
        context.setZone("TEST_HHD");
        context.setApiLang("zh-cn"); // optional, set return message i18n, default to us-en
        InstanceService service = new InstanceService(context);

        InstanceService.DescribeInstancesInput input = new InstanceService.DescribeInstancesInput();
        input.setLimit(1);

        try {
            InstanceService.DescribeInstancesOutput output = service.describeInstances(input);
            for (Types.InstanceModel model : output.getInstanceSet()) {
                System.out.println("==================");
                System.out.println(model.getInstanceID());
                System.out.println(model.getInstanceName());
                System.out.println(model.getImage().getImageID());
                for (Types.NICVxNetModel vxNetModel : model.getVxNets()) {
                    System.out.println("==================");
                    System.out.println(vxNetModel.getVxNetID());
                    System.out.println(vxNetModel.getVxNetType());
                }
            }
        } catch (QCException e) {
            e.printStackTrace();
        }
    }
}
