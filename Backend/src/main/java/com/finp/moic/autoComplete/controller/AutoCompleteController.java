package com.finp.moic.autoComplete.controller;

import com.finp.moic.autoComplete.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoCompleteController {

    AutoCompleteService autoCompleteService;
    @Autowired
    public AutoCompleteController(AutoCompleteService autoCompleteService) {
        this.autoCompleteService = autoCompleteService;
    }
    @PostMapping("/autocomplete")
    public List<String> getAutoComplete(String word) {

        List<String> list = autoCompleteService.getAutoComplete(word);
        return list;
    }
}
