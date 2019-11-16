package com.onebooming.community.community.dto;

/**
 * @author Onebooming
 */

public class GithubUserDTO {
    private String name;
    private Long id;
    private String bio;
    //"name": "Onebooming",
    //"id": 40877403,
    //"bio": "LOCK DREAM",

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
