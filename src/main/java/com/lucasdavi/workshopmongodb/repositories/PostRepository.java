package com.lucasdavi.workshopmongodb.repositories;

import com.lucasdavi.workshopmongodb.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
