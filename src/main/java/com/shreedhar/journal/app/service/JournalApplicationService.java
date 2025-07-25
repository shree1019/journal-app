package com.shreedhar.journal.app.service;

import com.shreedhar.journal.app.entities.JournalEntry;
import com.shreedhar.journal.app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalApplicationService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);
    }


    public boolean existsById(ObjectId myId) {
        return journalEntryRepository.existsById(myId);
    }
}
