package com.ugWorld.journalApp.controllers;

import com.ugWorld.journalApp.entity.JournalEntry;
import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.service.JournalEntryService;
import com.ugWorld.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntityControllerV2 {

    @Autowired
    private UserService userService;
    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUsers() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> all= user.getJournalEntries();
        if(all !=null ){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
         try {
             Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
             String userName= authentication.getName();
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
         }
        catch (Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntry> collect= user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entry = journalEntryService.getById(myId);
            if (entry.isPresent()) {
                return new ResponseEntity<>(entry, HttpStatus.OK);
            }
        }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        Boolean removed= journalEntryService.deleteEntryById(id,userName);
        if (removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
//    @DeleteMapping
//    public ResponseEntity<?> deleteEntryById() {
//        journalEntryService.deleteEntry();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id,
                                             @RequestBody JournalEntry newEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntry> collect= user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entry = journalEntryService.getById(id);
            if (entry.isPresent()) {
                JournalEntry old = entry.get();

                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old,HttpStatus.OK);


            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}