package movethere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/auth/registration")
    public User createUser(@RequestBody User user, HttpSession session){
        User createdUser = userService.saveUser(user);
        session.setAttribute("username", createdUser.getUsername());
        session.setAttribute("userId", createdUser.getId());
        return createdUser;
    }

    @PostMapping("/auth/login")
    public User login(@RequestBody User login, HttpSession session) throws Exception{
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(login.getUsername());
        if(user ==  null){
            throw new Exception("No User");
        }
        boolean valid = bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword());
        if(valid){
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());
            return user;
        }else{
            throw new Exception("No Password");
        }
    }

    @GetMapping("/auth/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("userId");
        session.invalidate();
        return;
    }

    @GetMapping("/users/{id}")
    public HashMap show(@PathVariable Long id) throws Exception{
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            User user = foundUser.get();
            Set<Address> addresses = user.getAddresses();
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("user", user);
            result.put("addresses", addresses);
            return result;
        }else{
            throw new Exception("Bad User");
        }
    }
//NEED TO EDIT
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id, HttpSession session) throws Exception{
        Optional<User> editedUser = userRepository.findById(id);
        if(editedUser.isPresent()){
            User userToEdit = editedUser.get();
            userToEdit.setUsername(user.getUsername());
            userToEdit.setPassword(user.getPassword());
            userToEdit.setEmail(user.getEmail());
            userToEdit.setCurrentZip(user.getCurrentZip());
            userToEdit.setWalkabilityImportance(user.getWalkabilityImportance());
            userToEdit.setMedianDesiredAge(user.getMedianDesiredAge());
            userToEdit.setMedianAgeImportance(user.getMedianAgeImportance());
            userToEdit.setDiversityImportance(user.getDiversityImportance());
            userToEdit.setHouseValueImportance(user.getHouseValueImportance());
            userToEdit.setDesiredWeather(user.getDesiredWeather());
            userToEdit.setWeatherImportance(user.getWeatherImportance());
            userToEdit.setNearbyAmenities(user.getNearbyAmenities());
            userToEdit.setAmenitiesImportance(user.getAmenitiesImportance());
            session.setAttribute("username", userToEdit.getUsername());
            return userService.saveUser(userToEdit);
        } else {
            throw new Exception("not a user");
        }
    }

    @DeleteMapping("/users/{id}")
    public Optional<User> deleteUser(@PathVariable Long id, HttpSession session) {
        Optional<User> userToDelete = userRepository.findById(id);
        session.removeAttribute("username");
        session.removeAttribute("userId");
        session.invalidate();
        userRepository.deleteById(id);
        return userToDelete;
    }

    }