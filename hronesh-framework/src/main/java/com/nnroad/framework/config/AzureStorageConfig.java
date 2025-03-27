package com.nnroad.framework.config;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageConfig {
    @Value("${azure.fileShare.sasToken}")
    private String sasToken;
    @Value("${azure.fileShare.accountName}")
    private String accountName;
    @Value("${azure.fileShare.shareName}")
    private String shareName;

    @Bean
    public ShareClient shareClient() {
        String shareURL = String.format("https://%s.file.core.chinacloudapi.cn", accountName);
        return new ShareClientBuilder().endpoint(shareURL)
                .sasToken(sasToken).shareName(shareName).buildClient();
    }
}

