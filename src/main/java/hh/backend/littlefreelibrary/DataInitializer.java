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

        // Institutions - Create all unique institutions from CSV (AI generated from CSV data) 
        java.util.Map<String, Institution> institutions = new java.util.HashMap<>();
        String[] institutionNames = {
            "University of Helsinki",
            "Haaga-Helia University of Applied Sciences",
            "Helsinki City Library",
            "Metropolia University of Applied Sciences",
            "Arcada University of Applied Sciences",
            "Little Free Library",
            "Diaconia University of Applied Sciences",
            "City of Helsinki",
            "Hanken School of Economics",
            "Humak University of Applied Sciences",
            "UniArts Helsinki"
        };

        for (String name : institutionNames) {
            if (name != null && !name.trim().isEmpty()) {
                institutions.put(name, institutionRepository.save(new Institution(name)));
            }
        }

        // Libraries from CSV data (AI generated from CSV data)
        Library[] libraries = {
            libraryRepository.save(new Library("Arabianranta Library", academicType, "Hämeentie 135 A, 00560 Helsinki, Finland", "https://libguides.metropolia.fi/opening_hours/arabia", institutions.get("Metropolia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Arcada Library", academicType, "Jan-Magnus Janssonin aukio 1, 00550 Helsinki, Finland", "https://www.arcada.fi/en/cooperation-and-services/services/library", institutions.get("Arcada University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Brontë Waddoups #48259", specialType, "Selkämerenkatu 11, 00180 Helsinki, Finland", "https://littlefreelibrary.org/", institutions.get("Little Free Library"), "Books, Book donations")),
            libraryRepository.save(new Library("Diaconia UAS Library", academicType, "Kyläsaarenkatu 2, 00580 Helsinki, Finland", "https://www.diak.fi/en/diak/library-and-information-services/", institutions.get("Diaconia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Etelä-Haaga Library", publicType, "Isonnevantie 16 b, 00320 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/EtelaHaagan_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("German Library", specialType, "Pohjoinen Makasiinikatu 7, 00130 Helsinki, Finland", "https://deutschebibliothekhelsinki.net/", null, null)),
            libraryRepository.save(new Library("Haaga-Helia Library - Pasila Campus", academicType, "Ratapihantie 13, 00520 Helsinki, Finland", "http://www.haaga-helia.fi/fi/palvelut/kirjasto", institutions.get("Haaga-Helia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Haaga-Helia library - Haaga Campus", academicType, "Pajuniityntie 11, 00320 Helsinki", "https://libguides.haaga-helia.fi/opening-hours/haaga", institutions.get("Haaga-Helia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Haaga-Helia library - Malmi Campus", academicType, "Hietakummuntie 1a, 00700 Helsinki", "https://libguides.haaga-helia.fi/opening-hours/malmi", institutions.get("Haaga-Helia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Hanken Library", academicType, "Arkadiankatu 22, 00100 Helsinki, Finland", "https://www.hanken.fi/en/library", institutions.get("Hanken School of Economics"), null)),
            libraryRepository.save(new Library("Kaisa-talo Library", academicType, "Fabianinkatu 30, 00014 Helsinki, Finland", "https://www.helsinki.fi/fi/helsingin-yliopiston-kirjasto/asioi-kirjastossa/toimipaikat-ja-tilat/paakirjasto-kaisa-talo", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Helsinki Central Library Oodi", publicType, "Töölönlahdenkatu 4, 00100 Helsinki, Finland", "http://www.oodihelsinki.fi/", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Herttoniemi Public Library", publicType, "Linnanrakentajantie 2, 00880 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Herttoniemen_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Humak Library", academicType, "Ilkantie 4, 00400 Helsinki", "https://www.humak.fi/en/library/", institutions.get("Humak University of Applied Sciences"), null)),
            libraryRepository.save(new Library("Institut français de Finlande", specialType, "Tehtaankatu 27-29D, Sisäpiha 4. kerros, 00150 Helsinki, Finland", "https://www.france.fi/", null, null)),
            libraryRepository.save(new Library("Itäkeskus Library", publicType, "Turunlinnantie 1, 00900 Helsinki, Finland", "https://helmet.finna.fi/OrganisationInfo/Home#84927", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Jakomäki Library", publicType, "Jakomäenpolku 3, 00770 Helsinki, Finland", "http://www.helmet.fi/jakomaenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Jätkäsaari Library", publicType, "Tyynenmerenkatu 1, 00220 Helsinki, Finland", "https://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Jatkasaaren_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Kalasataman kirjasto", publicType, "Hermannin rantatie 5, 00580 Helsinki, Finland", "https://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Kalasataman_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Kallio Library", publicType, "Viides linja 11, 00530 Helsinki, Finland", "http://www.helmet.fi/kallionkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Kannelmäki library", publicType, "Klaneettitie 5, 00420 Helsinki, Finland", "http://www.helmet.fi/kannelmaenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Käpylä Library", publicType, "Väinölänkatu 5, 00610 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Kapylan_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Pikkuhuopalahti Library", publicType, "Tilkankatu 19, 00300 Helsinki, Finland", "http://www.helmet.fi/pikkuhuopalahdenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Kontula Library", publicType, "Ostostie 4, 00940 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Kontulan_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Kumpula Campus Library", academicType, "Gustaf Hällströmin katu 2, 00560 Helsinki, Finland", "https://www.helsinki.fi/fi/helsingin-yliopiston-kirjasto/asioi-kirjastossa/toimipaikat-ja-tilat/kumpulan-kampuskirjasto", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Kumpulan Pikkukirjasto", specialType, "Isonniitynkatu 5, 00520 Helsinki, Finland", "https://littlefreelibrary.org/", institutions.get("Little Free Library"), "Books, Book donations")),
            libraryRepository.save(new Library("Laajasalo Library", publicType, "Yliskylän puistokatu 4, 00840 Helsinki, Finland", "https://helmet.finna.fi/OrganisationInfo/Home?lng=sv#84848", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Lauttasaaren kirjasto", publicType, "Pajalahdentie 10 A, 00200 Helsinki, Finland", "https://www.helmet.fi/lauttasaarenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Learning Centre Aleksandria", academicType, "Fabianinkatu 28, 00014 Helsinki, Finland", "https://www.helsinki.fi/fi/helsingin-yliopiston-kirjasto/asioi-kirjastossa/toimipaikat-ja-tilat/oppimiskeskus-aleksandria", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Library of Parliament", publicType, "Aurorankatu 6, 00100 Helsinki, Finland", "https://www.eduskunta.fi/FI/naineduskuntatoimii/kirjasto/Sivut/default.aspx", null, null)),
            libraryRepository.save(new Library("Library of the Labour Movement", specialType, "Sörnäisten rantatie 25, 00500 Helsinki, Finland", "https://www.tyovaenperinne.fi/", null, null)),
            libraryRepository.save(new Library("Malmi Library", publicType, "Ala-Malmin tori 1, 00700 Helsinki, Finland", "http://www.helmet.fi/malminkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Malminkartano Library", publicType, "Puustellintie 6, 00410 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Malminkartanon_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Maunula Library", publicType, "Metsäpurontie 4, 00630 Helsinki, Finland", "https://helmet.finna.fi/OrganisationInfo/Home#84857", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Meilahti Campus Library", academicType, "Haartmaninkatu 4, 00014 Helsinki, Finland", "https://www.helsinki.fi/fi/helsingin-yliopiston-kirjasto/asioi-kirjastossa/toimipaikat-ja-tilat/meilahden-kampuskirjasto", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Multilingual Library (part of Pasila Library)", publicType, "Kellosilta 9, 00520 Helsinki, Finland", "https://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Monikielinen_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Munkkiniemi Library", publicType, "Riihitie 22, 00330 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Munkkiniemen_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Myllypuro Library", academicType, "Kiviparintie 2, 00920 Helsinki, Finland", "https://www.helmet.fi/myllypuronkirjasto", institutions.get("Metropolia University of Applied Sciences"), null)),
            libraryRepository.save(new Library("National Library of Finland", publicType, "Unioninkatu 36, 00170 Helsinki, Finland", "https://www.testikirjasto.fi/", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Oulunkylä Library", publicType, "Kylänvanhimmantie 27, 00640 Helsinki, Finland", "http://www.helmet.fi/oulunkylankirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Paloheinän kirjasto", publicType, "Paloheinäntie 22, 00670 Helsinki, Finland", "https://helmet.finna.fi/OrganisationInfo/Home#84852", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Pasila Library", publicType, "Kellosilta 9, 00520 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Pasilan_kirjasto/yhteystiedot", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Pitäjänmäki Library", publicType, "Jousipolku 1, 00370 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Pitajanmaen_kirjasto/Aukioloajat", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Pohjois-Haagan kirjasto", publicType, "Kaupintie 4, 00440 Helsinki, Finland", "http://www.helmet.fi/pohjoishaagankirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Puistolan kirjasto", publicType, "Nurkkatie 2, 00760 Helsinki, Finland", "http://www.helmet.fi/puistolankirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Pukinmäki Library", publicType, "Kenttäkuja 12, 00720 Helsinki, Finland", "https://helmet.fi/pukinmaenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Rikhardinkatu Library", publicType, "Rikhardinkatu 3, 00130 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Rikhardinkadun_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Roihuvuori Public Library", publicType, "Roihuvuorentie 2, 00820 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Roihuvuoren_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Sörnäinen Campus Library", academicType, "Haapaniemenkatu 6, 00530 Helsinki, Finland", "https://www.uniarts.fi/en/locations/sornainen-campus-library-of-uniarts-helsinki/", institutions.get("UniArts Helsinki"), null)),
            libraryRepository.save(new Library("Studium Catholicum", specialType, "Ritarikatu 3b A 4, 00170 Helsinki, Finland", "http://www.studium.fi/", null, null)),
            libraryRepository.save(new Library("Suomenlinna Public Library", publicType, "Suomenlinna C 31, 00190 Helsinki, Finland", "https://www.suomenlinna.fi/sv/besokare/ovriga-tjanster/bibliotek/", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Suutarila Library", publicType, "Vaskiniitynkuja 5, 00740 Helsinki, Finland", "https://www.helmet.fi/suutarilankirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Tapanilan kirjasto", publicType, "Hiidenkiventie 21, 00730 Helsinki, Finland", "http://www.helmet.fi/tapanilankirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Tapulikaupungin kirjasto", publicType, "Ajurinaukio 5, 00750 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Tapulikaupungin_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Töölö campus library", academicType, "Mannerheimintie 13a, 00100 Helsinki, Finland", "https://www.uniarts.fi/en/locations/toolo-campus-library-of-uniarts-helsinki/", institutions.get("UniArts Helsinki"), null)),
            libraryRepository.save(new Library("Töölö Library", publicType, "Topeliuksenkatu 6, 00250 Helsinki, Finland", "http://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Toolon_kirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Vallila Library", publicType, "Päijänteentie 5, 00550 Helsinki, Finland", "https://helmet.finna.fi/OrganisationInfo/Home#84870", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Viikki Library", academicType, "Helsingin yliopisto, Viikinkaari 11, 00014 Helsinki, Finland", "https://www.helsinki.fi/fi/helsingin-yliopiston-kirjasto/asioi-kirjastossa/toimipaikat-ja-tilat/viikin-kampuskirjasto", institutions.get("University of Helsinki"), null)),
            libraryRepository.save(new Library("Vuosaaren kirjasto", publicType, "Mosaiikkitori 2, 00980 Helsinki, Finland", "https://www.helmet.fi/vuosaarenkirjasto", institutions.get("City of Helsinki"), null)),
            libraryRepository.save(new Library("Vuotalo Library", publicType, "Vuotie 5, 00980 Helsinki, Finland", "https://www.helmet.fi/fi-FI/Kirjastot_ja_palvelut/Vuotalon_kirjasto", institutions.get("City of Helsinki"), null))
        };

        // Use the first library for posts 
        Library testikirjasto = libraries[0]; // Index 0 is "Arabianranta Library" with id 1 in the database

        // Users
        AppUser alice = userRepository.save(new AppUser("Alice", "password", "alice@example.com", "admin"));
        AppUser bob = userRepository.save(new AppUser("Bob", "password", "bob@example.com", "moderator"));
        AppUser charlie = userRepository.save(new AppUser("Charlie", "password", "charlie@example.com", "moderator"));
        AppUser david = userRepository.save(new AppUser("David", "password", "david@example.com", "member"));

        // Posts
        postRepository.save(new Post("Welcome to the forum!", null, alice, null, testikirjasto, 8));
        postRepository.save(new Post("Guidelines", null, bob, null, testikirjasto, 7));
        postRepository.save(new Post("Best place ever!", null, charlie, null, testikirjasto, 10));
        postRepository.save(new Post("Hello all!", null, david, null, testikirjasto, 3));
    }
}
