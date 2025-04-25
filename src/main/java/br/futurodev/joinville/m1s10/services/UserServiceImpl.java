package br.futurodev.joinville.m1s10.services;

import br.futurodev.joinville.m1s10.dtos.users.UserRequestDto;
import br.futurodev.joinville.m1s10.dtos.users.UserResponseDto;
import br.futurodev.joinville.m1s10.entities.User;
import br.futurodev.joinville.m1s10.mappers.UserMapper;
import br.futurodev.joinville.m1s10.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public List<UserResponseDto> findAll() {
        List<User> users = repository.findAll();
        return UserMapper.toDtos(users);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return UserMapper.toDto(findEntityById(id));
    }

    @Override
    public UserResponseDto create(UserRequestDto dto ) {
        User user = new User();
        UserMapper.toEntity(user, dto);

        user.setPassword(encoder.encode(dto.getPassword()));

        user = repository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = findEntityById(id);
        UserMapper.toEntity(user, dto);

        user.setPassword(encoder.encode(dto.getPassword()));

        user = repository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = findEntityById(id);
        repository.delete(user);
    }

    private User findEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
