package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {
    private static Random rand = new Random();
    public static void main(String[] args) {

        BFSExample bfs = new BFSExample();

        bfs.populateDB();
        List<UserNode> users = bfs.getUsers();
        UserNode sourceUser = users.get(rand.nextInt(users.size()));
        UserNode destinationUser = users.get(rand.nextInt(users.size()));

        System.out.println("================Calculate the distance between two users======================");
        System.out.println("Source user: " + sourceUser.printFullName());
        System.out.println("Destination user: " + destinationUser.printFullName());
        int distance = bfs.calculateDistanceBetweenTwoUsers(sourceUser, destinationUser);
        System.out.println("The distance is: " + distance);
        System.out.println("\n");
        System.out.println("=================Select the friends of friends of a user in a given distance================");
        System.out.println("We are looking for the friends of " + sourceUser.printFullName());
        int givenDistance = 2;
        System.out.println("Distance: " + givenDistance);
        Set<UserNode> friends = bfs.selectFriendsOfFriendsINADistance(sourceUser, givenDistance);
        System.out.println("The friends are: ");
        for (UserNode friend : friends
        ) {
            System.out.println(friend.printFullName());
        }
        System.out.println("\n");
        System.out.println("====================Show the shortest path between two users========================");
        System.out.println("The shortest path between " + sourceUser.printFullName() + " and " + destinationUser.printFullName() + " :");
        List<UserNode> path = bfs.showShortestPathBetweenTwoUsers(sourceUser, destinationUser);
        System.out.println("Size: " + path.size());
        for (UserNode user: path
        ) {
            System.out.println(user.printFullName());
        }
    }
}
