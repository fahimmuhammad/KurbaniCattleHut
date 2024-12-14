package Fahim;

public class Cattle {
    private String id;
    private String breed;
    private int age;
    private double weight;
    private String healthStatus;

    public Cattle(String id, String breed, int age, double weight, String healthStatus) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.healthStatus = healthStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
}
