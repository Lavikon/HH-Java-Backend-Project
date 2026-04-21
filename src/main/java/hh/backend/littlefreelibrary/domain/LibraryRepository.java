package hh.backend.littlefreelibrary.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    // Custom query to find the 10 oldest libraries
    // List<Library> findTop10ByOrderByIdAsc();
    List<Library> findTop10By();
}