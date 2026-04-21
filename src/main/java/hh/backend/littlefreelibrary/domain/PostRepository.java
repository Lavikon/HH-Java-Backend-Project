package hh.backend.littlefreelibrary.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // Custom query to find the 5 most recent posts
    List<Post> findTop5ByOrderByCreatedAtDesc();    
}