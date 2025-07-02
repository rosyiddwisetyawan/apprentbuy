package nusatrip.userservice.service;

import nusatrip.userservice.model.User;
import nusatrip.userservice.model.response.ApiResponse;
import nusatrip.userservice.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse createUser(String name){
        Optional<User> userData = userRepository.findByName(name);

        if (userData.isEmpty()) {
            User newUser = new User();
            newUser.setName(name);
            User savedUser = userRepository.save(newUser);
            return new ApiResponse(true, savedUser);
        }

        return new ApiResponse(false, null);
    }

    public ApiResponse getUserById(Long id){
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return new ApiResponse(true, userData.get());
        }
        return new ApiResponse(false, null);

    }

    public ApiResponse getAllUsers(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> usersPage = userRepository.findAll(pageable);

        return new ApiResponse(
                true,
                usersPage.getContent()
        );
    }
}
