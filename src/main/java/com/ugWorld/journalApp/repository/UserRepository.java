package com.ugWorld.journalApp.repository;
import com.ugWorld.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
//    void deleteByName(String userName);
}
