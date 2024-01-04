package com.lucasdavi.workshopmongodb.config;

import com.lucasdavi.workshopmongodb.models.Post;
import com.lucasdavi.workshopmongodb.models.User;
import com.lucasdavi.workshopmongodb.repositories.PostRepository;
import com.lucasdavi.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        List<User> users = Arrays.asList(maria, alex, bob);

        Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem", "Vou viajar", maria);
        Post post2 = new Post(null, sdf.parse("15/04/2024"), "Bom dia", "Acordei, não queria, mas acordei", maria);

        List<Post> posts = Arrays.asList(post1, post2);

        postRepository.saveAll(posts);
        userRepository.saveAll(users);
    }
}
