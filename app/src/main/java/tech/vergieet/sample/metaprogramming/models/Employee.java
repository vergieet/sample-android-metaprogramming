package tech.vergieet.sample.metaprogramming.models;


import tech.vergieet.sample.metaprogramming.annotations.FromApi;
import tech.vergieet.sample.metaprogramming.annotations.HideOnForm;

@FromApi(url = "/employees")
public class Employee {

    @HideOnForm
    private Integer id;

    private String name;

    //    @JsonProperty("position")
    private String position;

    public Employee() {
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
//
    public Employee(Integer id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
//                ", position='" + position + '\'' +
                '}';
    }
}
