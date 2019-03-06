package movethere;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private Short currentZip;

    private Short walkabilityImportance;

    private Short medianDesiredAge;

    private Short medianAgeImportance;

    private Short diversityImportance;

    private Short houseValueImportance;
//Have it assigned 1 for warmer, 2 for cooler, 3 for the same
    private Short desiredWeather;

    private Short weatherImportance;

    private String nearbyAmenities;

    private Short amenitiesImportance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    private Set<Address> adresses;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getCurrentZip() {
        return currentZip;
    }

    public void setCurrentZip(Short currentZip) {
        this.currentZip = currentZip;
    }

    public Short getWalkabilityImportance() {
        return walkabilityImportance;
    }

    public void setWalkabilityImportance(Short walkabilityImportance) {
        this.walkabilityImportance = walkabilityImportance;
    }

    public Short getMedianDesiredAge() {
        return medianDesiredAge;
    }

    public void setMedianDesiredAge(Short medianDesiredAge) {
        this.medianDesiredAge = medianDesiredAge;
    }

    public Short getMedianAgeImportance() {
        return medianAgeImportance;
    }

    public void setMedianAgeImportance(Short medianAgeImportance) {
        this.medianAgeImportance = medianAgeImportance;
    }

    public Short getDiversityImportance() {
        return diversityImportance;
    }

    public void setDiversityImportance(Short diversityImportance) {
        this.diversityImportance = diversityImportance;
    }

    public Short getHouseValueImportance() {
        return houseValueImportance;
    }

    public void setHouseValueImportance(Short houseValueImportance) {
        this.houseValueImportance = houseValueImportance;
    }

    public Short getDesiredWeather() {
        return desiredWeather;
    }

    public void setDesiredWeather(Short desiredWeather) {
        this.desiredWeather = desiredWeather;
    }

    public Short getWeatherImportance() {
        return weatherImportance;
    }

    public void setWeatherImportance(Short weatherImportance) {
        this.weatherImportance = weatherImportance;
    }

    public String getNearbyAmenities() {
        return nearbyAmenities;
    }

    public void setNearbyAmenities(String nearbyAmenities) {
        this.nearbyAmenities = nearbyAmenities;
    }

    public Short getAmenitiesImportance() {
        return amenitiesImportance;
    }

    public void setAmenitiesImportance(Short amenitiesImportance) {
        this.amenitiesImportance = amenitiesImportance;
    }

    public Set<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Address> adresses) {
        this.adresses = adresses;
    }
}