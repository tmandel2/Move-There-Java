package movethere;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    private String streetNumber;

    private String streetName;

    private String city;

    private String state;

    private Integer zipCode;
//Get these two from geocode on first enter
    private Double latitude;

    private Double longitude;
//Get the walk score from API on first load
    private Short walkScore;

    private Double medianAge;
//    Diversity is the percentage of population majority
    private Double diversity;
//Median house cost divided by median income
    private Double houseValue;


//If I want a social aspect, add some additional things such as positive votes, and negative votes


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Short getWalkScore() {
        return walkScore;
    }

    public void setWalkScore(Short walkScore) {
        this.walkScore = walkScore;
    }

    public Double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(Double medianAge) {
        this.medianAge = medianAge;
    }

    public Double getDiversity() {
        return diversity;
    }

    public void setDiversity(Double diversity) {
        this.diversity = diversity;
    }

    public Double getHouseValue() {
        return houseValue;
    }

    public void setHouseValue(Double houseValue) {
        this.houseValue = houseValue;
    }
}
