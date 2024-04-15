package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        return journalEntries.put(journalEntry.getId(), journalEntry);
    }

    @GetMapping("{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id) {
        return journalEntries.get(id);
    }

    @DeleteMapping("{id}")
    public JournalEntry deleteJournalById(@PathVariable ObjectId id) {
        return journalEntries.remove(id);
    }

    @PutMapping("{id}")
    public JournalEntry updateJournalById(@RequestBody JournalEntry journalEntry) {
        return journalEntries.put(journalEntry.getId(), journalEntry);
    }
}
