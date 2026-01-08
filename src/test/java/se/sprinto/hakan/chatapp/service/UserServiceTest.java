package se.sprinto.hakan.chatapp.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        // Mockar repositoryt för att undvika riktig databas
        userRepository = mock(UserRepository.class);
        // Skapar service med mockad repository
        userService = new UserService(userRepository);
    }

    // ska returnera en User när inloggning lyckas
    @Test
    void shouldReturnUserWhenLoginIsSuccessful() {
        // Arrange
        User user = new User( "mimmi", "password");

       // Mockar repot så att användare hittas vid inloggning
        when(userRepository.findByUsernameAndPassword(
                user.getUsername(), user.getPassword()))
                .thenReturn(user);

        // Act - försöker logga in
        User result = userService.login(user.getUsername(),
                user.getPassword());

        // Assert - användaren returneras korrekt
        assertNotNull(result);
        assertEquals("mimmi", result.getUsername());

        // // Verifierar att metoden anropas exakt en gång
        verify(userRepository, times(1))
                .findByUsernameAndPassword(user.getUsername(), user.getPassword());

    }


    // Ska returnera null när inloggning misslyckas,
    // dvs när anv inte finns i db
    @Test
    void shouldReturnNullIfUserDoesNotExist() {
        // Arrange - anv som inte finns
        when(userRepository.findByUsernameAndPassword("soffan", "password"))
                .thenReturn(null);

        // Act - försöker logga in med en användare som inte finns
        User result = userService.login("soffan", "password");

        // Assert - ingen anv hittas
        assertNull(result);

        verify(userRepository, times(1))
                .findByUsernameAndPassword("soffan", "password");
    }


    // Ska spara och returnera en ny anv vid registering
    @Test
    void shouldSaveAndReturnUserWhenRegistering() {
       // Arrange
        User user = new User( "sam", "password");

        // Mockar repositoryt så att save returnerar användaren
        when(userRepository.save(user)).thenReturn(user);

        // Act - registrerar användaren
        User result = userService.register(user);

        // Assert - användaren returneras korrekt
        assertNotNull(result);
        assertEquals("sam", result.getUsername());

        verify(userRepository, times(1)).save(user);

    }






}
