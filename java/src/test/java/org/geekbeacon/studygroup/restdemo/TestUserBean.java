package org.geekbeacon.studygroup.restdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geekbeacon.studygroup.restdemo.models.User;
import org.junit.jupiter.api.Test;

public class TestUserBean {

    @Test
    public void testUser() throws JsonProcessingException {
        User u = new User(1L, "user", true);
        ObjectMapper m = new ObjectMapper();
        String data = m.writeValueAsString(u);
        User v = m.readValue(data, User.class);
        assert(u.getId().equals(v.getId()));
        assert(u.getName().equals(v.getName()));

    }
}
