package com.example.integrador.service;

import com.example.integrador.dto.UserDto;
import com.example.integrador.entity.UserMongoEntity;
import com.example.integrador.exception.UserNotFoundException;
import com.example.integrador.repository.UserMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceMapTest {

    @Mock
    private UserMongoRepository userMongoRepository;

    @InjectMocks
    private UsersServiceMap usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAll() {
        UserMongoEntity user = new UserMongoEntity();
        user.setId("10");
        user.setName("Messi");
        user.setEmail("messi@example.com");

        when(userMongoRepository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = usersService.all();
        assertEquals(1, result.size());
        assertEquals("Messi", result.get(0).getName());
        assertEquals("messi@example.com", result.get(0).getEmail());
    }

    @Test
    void testFindById() {
        UserMongoEntity user = new UserMongoEntity();
        user.setId("10");
        user.setName("Messi");
        user.setEmail("messi@example.com");

        when(userMongoRepository.findById("10")).thenReturn(Optional.of(user));

        Optional<UserDto> result = usersService.findById("10");
        assertTrue(result.isPresent());
        assertEquals("Messi", result.get().getName());
        assertEquals("messi@example.com", result.get().getEmail());
    }

    @Test
    void testFindByIdNotFound() {
        when(userMongoRepository.findById("10")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> usersService.findById("10"));
    }

    @Test
    void testSave() {
        UserDto userDto = new UserDto();
        userDto.setName("Messi");
        userDto.setEmail("messi@example.com");

        UserMongoEntity user = new UserMongoEntity();
        user.setName("Messi");
        user.setEmail("messi@example.com");

        when(userMongoRepository.save(any(UserMongoEntity.class))).thenReturn(user);

        UserDto result = usersService.save(userDto);
        assertEquals("Messi", result.getName());
        assertEquals("messi@example.com", result.getEmail());
    }

    @Test
    void testUpdate() {
        UserDto userDto = new UserDto();
        userDto.setName("Messi Updated");
        userDto.setEmail("messi.updated@example.com");

        UserMongoEntity user = new UserMongoEntity();
        user.setId("10");
        user.setName("Messi");
        user.setEmail("messi@example.com");

        when(userMongoRepository.findById("10")).thenReturn(Optional.of(user));
        when(userMongoRepository.save(any(UserMongoEntity.class))).thenReturn(user);

        UserDto result = usersService.update(userDto, "10");
        assertEquals("Messi Updated", result.getName());
        assertEquals("messi.updated@example.com", result.getEmail());
    }

    @Test
    void testDeleteById() {
        UserMongoEntity user = new UserMongoEntity();
        user.setId("10");
        user.setName("Messi");
        user.setEmail("messi@example.com");

        when(userMongoRepository.findById("10")).thenReturn(Optional.of(user));

        usersService.deleteById("10");

        verify(userMongoRepository, times(1)).delete(user);
    }
}
