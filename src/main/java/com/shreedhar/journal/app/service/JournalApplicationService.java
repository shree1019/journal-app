package com.shreedhar.journal.app.service;

import com.shreedhar.journal.app.entities.JournalEntry;
import com.shreedhar.journal.app.entities.Users;
import com.shreedhar.journal.app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalApplicationService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        Users user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    public void deleteById(ObjectId Id, String userName){
        Users user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(Id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(Id);
    }


    public boolean existsById(ObjectId myId) {
        return journalEntryRepository.existsById(myId);
    }
}
