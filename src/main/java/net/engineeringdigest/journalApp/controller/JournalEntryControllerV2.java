package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id) {
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public boolean deleteJournalById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return true;
    }

    @PutMapping("{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry journalEntry = journalEntryService.findById(id).orElse(null);
        if(journalEntry!=null) {
            journalEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():journalEntry.getTitle());
            journalEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent() : journalEntry.getContent());
        }
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }
}
