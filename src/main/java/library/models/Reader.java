package library.models;

import library.builders.ReaderBuilder;

public class Reader extends BaseModel {
    public Integer id;
    public String name;
    public String email;
    public String phone;

    public Reader() {
        this.table = "readers";
    }

    public static ReaderBuilder query() {
        return new ReaderBuilder();
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
