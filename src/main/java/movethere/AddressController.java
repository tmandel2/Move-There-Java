package movethere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class AddressController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address, HttpSession session) throws Exception{
//        User user = userRepository.findByUsername(session.getAttribute("username").toString());
////        COMMENTED OUT FOR TESTING
//        if(user == null){
//            throw new Exception("Log in");
//        }
//        addressRepository.save(address);
//        Set<Address> addresses = user.getAddresses();
//        addresses.add(address);
//        user.setAddresses(addresses);
//        userRepository.save(user);
//        return address;

        User user = userRepository.findByUsername(session.getAttribute("username").toString());
        if(user == null){
            throw new Exception("You must log in");
        }
        address.setUser(user);
        Address createdAddress = addressRepository.save(address);
        return createdAddress;
    }

    @GetMapping("/addresses")
    public Iterable<Address> getAddresses(){
        Iterable<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    @GetMapping("/addresses/{id}")
    public Address showAddress(@PathVariable Long id){
        Address foundAddress = addressRepository.findById(id).get();
        return foundAddress;
    }
//Edit route will need to rerun all the API calls on the front, so all these need to be updated
    @PutMapping("/addresses/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long id) throws Exception{
        Optional<Address> editedAddress = addressRepository.findById(id);
        if(editedAddress.isPresent()){
            Address addressToEdit = editedAddress.get();
            addressToEdit.setStreetNumber(address.getStreetNumber());
            addressToEdit.setStreetName(address.getStreetName());
            addressToEdit.setCity(address.getCity());
            addressToEdit.setState(address.getState());
            addressToEdit.setZipCode(address.getZipCode());
            addressToEdit.setLatitude(address.getLatitude());
            addressToEdit.setLongitude(address.getLongitude());
            addressToEdit.setWalkScore(address.getWalkScore());
            addressToEdit.setDiversity(address.getDiversity());
            addressToEdit.setMedianAge(address.getMedianAge());
            addressToEdit.setHouseValue(address.getHouseValue());
            return addressRepository.save(addressToEdit);
        } else {
            throw new Exception("address not here");
        }
    }

    @DeleteMapping("/addresses/{id}")
    public String deleteAddress(@PathVariable Long id){
        addressRepository.deleteById(id);
        return "address removed";
    }


    @GetMapping("/addresses/walkscore")
    public static String getWalkScore(@RequestHeader(value="url") String url) throws Exception{
        final String uri = url;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }


}