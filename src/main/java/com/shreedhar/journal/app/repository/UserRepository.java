package com.shreedhar.journal.app.repository;

import com.shreedhar.journal.app.entities.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    public Users findByName(String name);
}
