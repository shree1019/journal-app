package com.shreedhar.journal.app.service;

import com.shreedhar.journal.app.entities.JournalEntry;
import com.shreedhar.journal.app.entities.Users;
import com.shreedhar.journal.app.repository.JournalEntryRepository;
import com.shreedhar.journal.app.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(Users user){
        userRepository.save(user);
    }

    public List<Users> findAll(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void deleteById(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public boolean existsById(ObjectId myId) {
        return userRepository.existsById(myId);
    }

    public Users findByUserName(String name){return userRepository.findByName(name); }
}
