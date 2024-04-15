package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        return journalEntries.put(journalEntry.getId(), journalEntry);
    }

    @GetMapping("{id}")
    public JournalEntry getJournalById(@PathVariable long id) {
        return journalEntries.get(id);
    }

    @DeleteMapping("{id}")
    public JournalEntry deleteJournalById(@PathVariable long id) {
        return journalEntries.remove(id);
    }

    @PutMapping("{id}")
    public JournalEntry updateJournalById(@RequestBody JournalEntry journalEntry) {
        return journalEntries.put(journalEntry.getId(), journalEntry);
    }
}
