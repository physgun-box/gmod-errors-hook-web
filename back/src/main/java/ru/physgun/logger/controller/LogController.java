package ru.physgun.logger.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.physgun.logger.model.Log;
import ru.physgun.logger.repository.LogRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/log")
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping
    public void createLog(
            @RequestParam(name = "server") String serverTagName,
            @Valid @ModelAttribute Log log
    ) {
        log.setServerTagName(serverTagName);
        Log savedLog = this.logRepository.save(log);
        ResponseEntity.ok(savedLog);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Log>> getLogList(
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10); // Страница начинается с 0
        Page<Log> logs = this.logRepository.findAll(pageable);
        return ResponseEntity.ok(logs);
    }
}
