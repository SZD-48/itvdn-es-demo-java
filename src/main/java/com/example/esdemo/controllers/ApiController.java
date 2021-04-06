package com.example.esdemo.controllers;

import com.example.esdemo.services.EsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final EsService esService;

    public ApiController(EsService esService) {
        this.esService = esService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PutMapping("/articles")
    public String addArticle(@RequestParam("title") String title, @RequestParam("text") String text) throws Exception {
        String id = UUID.randomUUID().toString();
        esService.updateArticle(id, title, text);
        return id;
    }

    @GetMapping("/search")
    public List<EsService.Article> search(@RequestParam("query") String query) throws Exception {
        return esService.search(query);
    }

}
