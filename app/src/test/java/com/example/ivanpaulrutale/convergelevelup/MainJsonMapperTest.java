package com.example.ivanpaulrutale.convergelevelup;

import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;
import com.example.ivanpaulrutale.convergelevelup.model.MainJsonMapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainJsonMapperTest {

    MainJsonMapper mainJsonMapper = new MainJsonMapper();
    DeveloperDataMapper developer1 = new DeveloperDataMapper();
    DeveloperDataMapper developer2 = new DeveloperDataMapper();
    List<DeveloperDataMapper> developerDataMapperList = new ArrayList<>();
    String login = "loginname";
    String avatarUrl = "avatarurl";
    String url = "url";

    @Test
    public void MainJsonMapperReturnsSameData(){
        developer1.setLogin(login);
        developer1.setAvatarUrl(avatarUrl);
        developer1.setUrl(url);
        developer2.setLogin(login);
        developer2.setAvatarUrl(avatarUrl);
        developer2.setUrl(url);

        developerDataMapperList.add(developer1);
        developerDataMapperList.add(developer2);
        mainJsonMapper.setItems(developerDataMapperList);
        mainJsonMapper.setIncompleteResults(false);
        mainJsonMapper.setTotalCount(2);


        assertTrue(!(mainJsonMapper.getIncompleteResults()));
        assertEquals((long)2,(long)mainJsonMapper.getTotalCount());
        assertArrayEquals(new List[]{developerDataMapperList}, new List[]{mainJsonMapper.getItems()});

    }
}
