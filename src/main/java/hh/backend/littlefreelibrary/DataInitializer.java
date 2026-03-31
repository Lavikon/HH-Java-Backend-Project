package hh.backend.littlefreelibrary;

import hh.backend.littlefreelibrary.domain.Institution;
import hh.backend.littlefreelibrary.domain.Library;
import hh.backend.littlefreelibrary.domain.LibraryType;
import hh.backend.littlefreelibrary.domain.AppUser;
import hh.backend.littlefreelibrary.domain.Post;
import hh.backend.littlefreelibrary.domain.InstitutionRepository;
import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.LibraryTypeRepository;
import hh.backend.littlefreelibrary.domain.AppUserRepository;
import hh.backend.littlefreelibrary.domain.PostRepository;
import java.time.Instant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InstitutionRepository institutionRepository;
    private final LibraryTypeRepository libraryTypeRepository;
    private final LibraryRepository libraryRepository;
    private final AppUserRepository userRepository;
    private final PostRepository postRepository;

    public DataInitializer(
            InstitutionRepository institutionRepository,
            LibraryTypeRepository libraryTypeRepository,
            LibraryRepository libraryRepository,
            AppUserRepository userRepository,
            PostRepository postRepository) {
        this.institutionRepository = institutionRepository;
        this.libraryTypeRepository = libraryTypeRepository;
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return; // already initialized
        }

        // LibraryTypes
        LibraryType publicType = libraryTypeRepository.save(new LibraryType("Public"));
        LibraryType academicType = libraryTypeRepository.save(new LibraryType("Academic"));
        LibraryType specialType = libraryTypeRepository.save(new LibraryType("Special"));

        // Institutions
        Institution uniHelsinki = institutionRepository.save(new Institution("University of Helsinki"));
        institutionRepository.save(new Institution("Haaga-Helia University of Applied Sciences"));
        institutionRepository.save(new Institution("Helsinki City Library"));

        // Libraries
        Library kansalliskirjasto = libraryRepository.save(new Library(
                "Kansalliskirjasto",
                publicType,
                "Esplanadi 5",
                "kansalliskirjasto.fi",
                uniHelsinki,
                null,
                Instant.now()
        ));

        // Users
        AppUser alice = userRepository.save(new AppUser("Alice", "password", "alice@example.com", "admin", Instant.now()));
        AppUser bob = userRepository.save(new AppUser("Bob", "password", "bob@example.com", "moderator", Instant.now()));
        AppUser charlie = userRepository.save(new AppUser("Charlie", "password", "charlie@example.com", "moderator", Instant.now()));
        AppUser david = userRepository.save(new AppUser("David", "password", "david@example.com", "member", Instant.now()));

        // Posts
        postRepository.save(new Post("Welcome to the forum!", null, alice, null, kansalliskirjasto, 8, Instant.now()));
        postRepository.save(new Post("Guidelines", null, bob, null, kansalliskirjasto, 7, Instant.now()));
        postRepository.save(new Post("Best place ever!", null, charlie, null, kansalliskirjasto, 10, Instant.now()));
        postRepository.save(new Post("Hello all!", null, david, null, kansalliskirjasto, 3, Instant.now()));
    }
}
