package hh.backend.littlefreelibrary;

import hh.backend.littlefreelibrary.domain.Institution;
import hh.backend.littlefreelibrary.domain.Library;
import hh.backend.littlefreelibrary.domain.LibraryType;
import hh.backend.littlefreelibrary.domain.LibraryUser;
import hh.backend.littlefreelibrary.domain.Post;
import hh.backend.littlefreelibrary.domain.InstitutionRepository;
import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.LibraryTypeRepository;
import hh.backend.littlefreelibrary.domain.LibraryUserRepository;
import hh.backend.littlefreelibrary.domain.PostRepository;
import java.time.Instant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InstitutionRepository institutionRepository;
    private final LibraryTypeRepository libraryTypeRepository;
    private final LibraryRepository libraryRepository;
    private final LibraryUserRepository userRepository;
    private final PostRepository postRepository;

    public DataInitializer(
            InstitutionRepository institutionRepository,
            LibraryTypeRepository libraryTypeRepository,
            LibraryRepository libraryRepository,
            LibraryUserRepository userRepository,
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
        LibraryType publicType = libraryTypeRepository.save(new LibraryType("public"));
        LibraryType academicType = libraryTypeRepository.save(new LibraryType("academic"));
        LibraryType specialType = libraryTypeRepository.save(new LibraryType("special"));

        // Institutions
        Institution helsinki = institutionRepository.save(new Institution("helsingin yliopisto"));
        institutionRepository.save(new Institution("lutpub"));
        institutionRepository.save(new Institution("helmet"));

        // Libraries
        Library kansalliskirjasto = libraryRepository.save(new Library(
                "kansalliskirjasto",
                publicType,
                "esplanadi 5",
                "kansalliskirjasto.fi",
                helsinki,
                null,
                Instant.now()
        ));

        // Users
        LibraryUser alice = userRepository.save(new LibraryUser("Alice", "password", "alice@example.com", "admin", Instant.now()));
        LibraryUser bob = userRepository.save(new LibraryUser("Bob", "password", "bob@example.com", "moderator", Instant.now()));
        userRepository.save(new LibraryUser("Candice", "password", "candice@example.com", "moderator", Instant.now()));
        LibraryUser david = userRepository.save(new LibraryUser("David", "password", "david@example.com", "member", Instant.now()));

        // Posts
        postRepository.save(new Post("Welcome to the forum!", null, alice, null, kansalliskirjasto, 4, Instant.now()));
        postRepository.save(new Post("Guidelines", null, bob, null, kansalliskirjasto, 3, Instant.now()));
        postRepository.save(new Post("Hello all!", null, david, null, kansalliskirjasto, 3, Instant.now()));
    }
}
