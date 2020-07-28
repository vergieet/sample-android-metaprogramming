package tech.vergieet.sample.metaprogramming.models;


import tech.vergieet.sample.metaprogramming.annotations.FromApi;

@FromApi(url = "/departments")
public class Department {

    private String code;

    private String name;

    private String notes;

    public Department() {

    }

    public Department(String code, String name, String notes) {
        this.code = code;
        this.name = name;
        this.notes = notes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
