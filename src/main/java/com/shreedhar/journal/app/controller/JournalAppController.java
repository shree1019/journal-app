package com.shreedhar.journal.app.controller;

import com.shreedhar.journal.app.entities.JournalEntry;
import com.shreedhar.journal.app.service.JournalApplicationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalAppController {

    @Autowired
    private JournalApplicationService journalApplicationService;

    @GetMapping
    public ResponseEntity<?> getAllEntries(){
        List<JournalEntry> journalEntries = journalApplicationService.findAll();
        if (journalEntries.isEmpty()) {
            return new ResponseEntity<>("No journal entries found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntries = journalApplicationService.findById(myId);
        if(journalEntries.isPresent()){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry){
        try {
            newEntry.setDate(LocalDateTime.now());
            journalApplicationService.saveEntry(newEntry);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        if (journalApplicationService.existsById(myId)) {
            journalApplicationService.deleteById(myId);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalApplicationService.findById(myId).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(oldEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): oldEntry.getTitle());
            oldEntry.setContent(oldEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent(): oldEntry.getContent());
            journalApplicationService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
