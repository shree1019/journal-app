package com.shreedhar.journal.app.controller;

import com.shreedhar.journal.app.entities.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalAppController {
    private final HashMap<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("id/{myId}")
    public JournalEntry getById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId(),newEntry);
        return true;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable Long myId,@RequestBody JournalEntry updatedEntry){
        return journalEntries.put(myId,updatedEntry);
    }
}
