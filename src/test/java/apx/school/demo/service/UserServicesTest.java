package apx.school.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import apx.school.demo.Dto.UserDto;
import apx.school.demo.Entity.UserEntity;
import apx.school.demo.Repository.MongoDBRepository;
import apx.school.demo.Servicio.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServicesTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private MongoDBRepository userMongoRepository;

    public UserServicesTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buscarPorTodosDebeFuncionar() {
        UserEntity user1 = new UserEntity();
        user1.setId("1");
        user1.setName("John");
        user1.setEmail("john@example.com");

        UserEntity user2 = new UserEntity();
        user2.setId("2");
        user2.setName("Jane");
        user2.setEmail("jane@example.com");

        when(userMongoRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.getAll();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jane", users.get(1).getName());
    }

    @Test
    public void guardarDebeFuncionar() {
        UserEntity user1 = new UserEntity();
        user1.setId("1");
        user1.setName("John");
        user1.setEmail("john@example.com");

        UserEntity user2 = new UserEntity();
        user2.setId("2");
        user2.setName("Jane");
        user2.setEmail("jane@example.com");

        when(userMongoRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.getAll();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jane", users.get(1).getName());
    }



    @Test
    public void buscarPorIdDebeFuncionar() {
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");

        when(userMongoRepository.findById("1")).thenReturn(Optional.of(user));

        UserDto userDto = userService.getById("1");

        assertEquals("1", userDto.getId());
        assertEquals("John", userDto.getName());
        assertEquals("john@example.com", userDto.getEmail());
    }

    @Test
    public void actualizarDebeFuncionar() {
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");

        UserDto userDto = new UserDto();
        userDto.setId("1");
        userDto.setName("John");
        userDto.setEmail("john@example.com");

        when(userMongoRepository.findById("1")).thenReturn(Optional.of(user));
        when(userMongoRepository.save(any(UserEntity.class))).thenReturn(user);

        UserDto updatedUser = userService.update(userDto, "1");

        assertNotNull(updatedUser);
        assertEquals("1", updatedUser.getId());
    }

    @Test
    public void eliminarDebeFuncionar() {
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("John");
        user.setEmail("john@example.com");

        when(userMongoRepository.findById("1")).thenReturn(Optional.of(user));

        userService.delete("1");

        verify(userMongoRepository, times(1)).delete(user);
    }

}
