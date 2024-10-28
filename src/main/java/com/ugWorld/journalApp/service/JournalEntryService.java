package com.ugWorld.journalApp.service;

import com.ugWorld.journalApp.entity.JournalEntry;
import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    public JournalEntryRepository journalEntryRepository;
    @Autowired
    public UserService userService;
   @Transactional
   public void saveEntry(JournalEntry journalEntry, String userName) {
       try {
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveEntry(user);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
   public void saveEntry(JournalEntry journalEntry) {
       journalEntryRepository.save(journalEntry);
   }
//   public List<JournalEntry> getAll(){
//       return journalEntryRepository.findAll();
//}
    @Transactional
    public Boolean deleteEntryById(ObjectId id, String userName) {
        boolean removed=false;
       try {

           User user = userService.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }

       } catch (Exception e) {
           log.error("An error is come while deleting the entry",e);
       }
        return removed;
    }
    public void deleteEntry(){
        journalEntryRepository.deleteAll();}

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);}
       }
