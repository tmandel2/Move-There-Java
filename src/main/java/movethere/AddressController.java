package movethere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
public class AddressController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address, HttpSession session) throws Exception{
        User user = userRepository.findByUsername(session.getAttribute("username").toString());
//        COMMENTED OUT FOR TESTING
        if(user == null){
            throw new Exception("Log in");
        }
        addressRepository.save(address);
        Set<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        userRepository.save(user);
        return address;
    }

}