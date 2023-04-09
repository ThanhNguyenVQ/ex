package com.mycompany.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRespository repo;

    public List<User> ListAll(){
        return (List<User>) repo.findAll(); //ep kieu
    }

//    public void save(User user) {
//        repo.save(user);
//    }
    public void save(User user) {
    if (user.getFirstname() == null || user.getFirstname().isEmpty()) {
        throw new IllegalArgumentException("First name cannot be null or empty");
    }
//    service.save(user);
        repo.save(user);
}
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID "+id);
    }
}
