package com.beyond.note.integration.sync;

import com.beyond.note.integration.entity.Account;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties("webdav")
@Component
public class WebDavProperty {

    private Map<String, Account> accountMap;

    public Map<String, Account> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, Account> accountMap) {
        this.accountMap = accountMap;
    }
}
