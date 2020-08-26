package com.somesoftwareteam.graphql.datasources.auth0;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.UUID;

/**
 * https://www.baeldung.com/jackson-annotations
 */
public class Auth0ExtGroup {

    @JsonAlias("_id")
    private UUID id;
    private String name;
    private String description;
    private String[] members;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }
}