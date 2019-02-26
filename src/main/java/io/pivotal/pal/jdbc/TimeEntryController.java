package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private JdbcTimeEntryRepository timeEntriesRepo;

    @Autowired
    public TimeEntryController(JdbcTimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list")
    public List<TimeEntry> list() {
        List<TimeEntry> entries = timeEntriesRepo.list();
        return entries;
    }

    @GetMapping("test")
    public ResponseEntity<String> test() {

            return new ResponseEntity<>("Hello", HttpStatus.OK);

    }
}
