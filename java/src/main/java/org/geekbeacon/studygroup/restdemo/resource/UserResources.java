package org.geekbeacon.studygroup.restdemo.resource;

import lombok.NonNull;
import org.geekbeacon.studygroup.restdemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Defensive Programming
@RestController
public class UserResources {
    private final List<User> users = new ArrayList<>();

    @Autowired
    public UserResources() {
        users.add(new User(1L, "Bob", true));
    }


    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> getUsers() {
        return users;
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    User createUser(@RequestBody User u) {
        System.out.println(u.getName());
        users.add(u);
        return getUser(u.getId());

    }

    @GetMapping(value = "/users/{id}")
    User getUser(@NonNull @PathVariable("id") Long id) {
        Optional<User> search = users.stream().filter(t -> t.getId().equals(id)).findFirst();
        return search.orElse(null);

    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateUser(@PathVariable("id") long id, @RequestBody User u) {
        Optional<User> search = users.stream().filter(t -> t.getId() == id).findFirst();
        if (search.isPresent()) {
            search.get().setId(id);
        }

    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") long id) {
        Optional<User> search = users.stream().filter(t -> t.getId() == id).findFirst();
        search.ifPresent(users::remove);

    }


}
