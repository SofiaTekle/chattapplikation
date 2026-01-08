package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.repository.MessageRepository;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    private MessageRepository messageRepository;
    private MessageService messageService;

    @BeforeEach
    public void setUp()   {

        messageRepository = mock(MessageRepository.class);

        messageService = new MessageService(messageRepository);
    }


    // Verifierar att MessageService.save() vidarebefordrar anropet till repositoryt.
    @Test
    void shouldSaveMessage() {
        Message message = new Message(null,"Hej", LocalDateTime.now());

        messageService.save(message);

        verify(messageRepository,times(1)).save(message);
    }


    // ska returnera en lista med medd när anv har sparade medd
    @Test
    void shouldReturnMessagesWhenUserHasMessages() {
        // Arrange - användare med sparade medd
        Long userId = 1L;

        Message message1 = new Message(null,"Hej 1", LocalDateTime.now());
        Message message2 = new Message(null,"Hej 2",LocalDateTime.now());

        // Mockar repository-anropet så att det returnerar en lista med två medd för användaren
        when(messageRepository.findByUserId(userId)).thenReturn(List.of(message1,message2));

        // Act - anropar service metoden som ska testas
        List<Message> result = messageService.getMessages(userId);

        // Assert - verifierar att rätt antal meddelanden returneras
        assertEquals(2, result.size());

        // Verifierar att repo-metoden anropas exakt 1 gång
        verify(messageRepository,times(1)).findByUserId(userId);

    }


    // ska returnera en tom lista när anv inte har sparade medd
    @Test
    void shouldReturnEmptyListIfNoMessagesFound() {
        // Arrange - användare utan sparade medd
        Long userId = 2L;

        // Mockar repot så att en tom lista returneras
        when(messageRepository.findByUserId(userId)).thenReturn(List.of());

        // Act - hämtar medd för användaren
        List<Message> result = messageService.getMessages(userId);

        // Assert - verifierar att resultatet är en tom lista
        assertTrue(result.isEmpty());

        verify(messageRepository,times(1)).findByUserId(userId);
    }



}
