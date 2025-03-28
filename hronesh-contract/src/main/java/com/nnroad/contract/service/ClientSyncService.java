package com.nnroad.client.service;

import com.nnroad.client.domain.SysClient;

public interface ClientSyncService {

    void checkVendorSync(SysClient client, String tableType);

    void checkClientSync(SysClient client, String tableType);
}
