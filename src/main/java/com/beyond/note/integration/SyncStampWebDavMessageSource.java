package com.beyond.note.integration;

import com.beyond.note.integration.entity.Account;
import com.beyond.sync.datasouce.webdav.support.DavClient;
import com.beyond.sync.datasouce.webdav.support.SardineDavClient;
import com.beyond.sync.entity.SyncStamp;
import com.beyond.sync.utils.OkWebDavUtil;

public class SyncStampWebDavMessageSource extends AbstractWebDavMessageSource<SyncStamp> {

    private Account account;

    public SyncStampWebDavMessageSource(Account account) {
        this.account = account;
    }

    @Override
    protected Class<SyncStamp> clazz() {
        return SyncStamp.class;
    }

    @Override
    protected DavClient getDavClient() {
        String username = account.getUsername();
        String password = account.getPassword();
        return new SardineDavClient(username,password);
    }

    @Override
    protected String url() {
        return OkWebDavUtil.concat(
                account.getServer(),
                "/NOTE/STAMP/LATEST_STAMP.stamp");
    }
}
