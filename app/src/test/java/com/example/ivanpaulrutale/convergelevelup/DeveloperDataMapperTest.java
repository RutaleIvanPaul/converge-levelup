package com.example.ivanpaulrutale.convergelevelup;

import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeveloperDataMapperTest {

    String login = "loginname";
    String avatarUrl = "avatarurl";
    String url = "url";

    DeveloperDataMapper developerDataMapper = new DeveloperDataMapper();

    @Test
    public void DeveloperDataMapperReturnsSameData(){
        developerDataMapper.setLogin(login);
        developerDataMapper.setAvatarUrl(avatarUrl);
        developerDataMapper.setUrl(url);
        assertEquals(login,developerDataMapper.getLogin());
        assertEquals(url,developerDataMapper.getUrl());
        assertEquals(avatarUrl,developerDataMapper.getAvatarUrl());
    }


}
