package com.hanhuide.qingyun;


import com.hanhuide.qingyun.domain.model.QingCloudAppPayload;
import com.hanhuide.qingyun.ui.model.DescribeUsersRequest;
import com.hanhuide.qingyun.ui.model.DescribeUsersResponse;

import java.io.IOException;

/**
 * Created by zhangbohan on 15/8/19.
 */
public interface IQingCloudAppClient {
    public void setAccessToken(String accessToken);

    public QingCloudAppPayload extractPayload(String payload, String signature);

    public DescribeUsersResponse describeUsers(DescribeUsersRequest describeUsersRequest)
            throws QingCloudClientException, QingCloudServiceException, IOException;
}
