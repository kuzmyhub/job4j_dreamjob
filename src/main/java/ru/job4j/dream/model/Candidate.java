package ru.job4j.dream.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Candidate implements Serializable {

    private int id;

    private String name;

    private String desc;

    private LocalDateTime created;

    private boolean visible;

    private City city;

    private byte[] photo;

    private int cityId;

    public Candidate() {
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name,
                     String desc, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.photo = new byte[1];
    }

    public Candidate(int id, String name, String description,
                LocalDateTime created, boolean visible, byte[] photo, int cityId) {
        this.id = id;
        this.name = name;
        this.desc = description;
        this.created = created;
        this.visible = visible;
        this.photo = photo;
        this.cityId = cityId;
    }

    public Candidate(int id, String name, String desc,
                LocalDateTime created, boolean visible, byte[] photo, City city) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.visible = visible;
        this.photo = photo;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", visible=" + visible
                + ", city=" + city
                + ", photo=" + Arrays.toString(photo)
                + ", cityId=" + cityId
                + '}';
    }
}
