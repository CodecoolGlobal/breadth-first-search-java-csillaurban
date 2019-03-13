package com.codecool.bfsexample.model;

import java.util.HashSet;
import java.util.Set;

public class UserNode {

    // feel free to add new properties or methods here

    private long id;
    private static long idCounter = 0;
    private String firstName;
    private String lastName;
    private Set<UserNode> friends = new HashSet<>();
    private int distance = -1;

    public UserNode(String firstName, String lastName) {
        this.id = idCounter;
        idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public Set<UserNode> getFriends() {return friends;}

    public void addFriend(UserNode friend) {
        friends.add(friend);
        friend.friends.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + "_" + lastName + " (" + id + ")" + "friends: " + friends.size();
    }

    public String printFullName() {
        return firstName + " " + lastName;
    }
}
