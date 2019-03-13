package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BFSExample {
    private List<UserNode> users = new ArrayList<>();
    private int distanceValue = 1;

    public void populateDB() {
        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();

        GraphPlotter graphPlotter = new GraphPlotter(users);
        
        System.out.println("Done!");
    }

    public Integer calculateDistanceBetweenTwoUsers(UserNode sourceUser, UserNode destinationUser) {
        if(checkIfSourceAndDestinationUserIsTheSame(sourceUser, destinationUser)) {
            return null;
        }

        if (sourceUser.getFriends().contains(destinationUser)) {
            return distanceValue;
        } else {
            LinkedList<UserNode> queue = new LinkedList<>();
            sourceUser.setDistance(0);
            queue.add(sourceUser);

            while (!queue.isEmpty()) {
                UserNode currentUser = queue.poll();
                Set<UserNode> friendsOfCurrentUser = currentUser.getFriends();
                for (UserNode friend : friendsOfCurrentUser
                ) {
                    if (friend.getDistance() == -1) {
                        friend.setDistance(currentUser.getDistance() + distanceValue);
                        if (friend.getId() == destinationUser.getId()) {
                            return friend.getDistance();
                        } else {
                            queue.add(friend);
                        }
                    }
                }
            }
    }
        return -1;
    }

    public Set<UserNode> selectFriendsOfFriendsINADistance(UserNode sourceUser, int distance) {
        Set<UserNode> friendsOfFriends = new HashSet<>();

        if(distance <= 0) {
            throw new IllegalArgumentException("The distance must be a positive number.");
        }

        setBackDistance();

        if(sourceUser.getFriends().size() == 0) {
            System.out.println("The source user has no friends.");
            return null;
        } else {
            LinkedList<UserNode> queue = new LinkedList<>();
            sourceUser.setDistance(0);
            queue.add(sourceUser);

            while (!queue.isEmpty()) {
                UserNode currentUser = queue.poll();
                Set<UserNode> friendsOfCurrentUser = currentUser.getFriends();
                for (UserNode friend : friendsOfCurrentUser
                ) {
                    if (friend.getDistance() == -1) {
                        friend.setDistance(currentUser.getDistance() + distanceValue);
                        if(friend.getDistance() <= distance) {
                            friendsOfFriends.add(friend);
                            queue.add(friend);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return friendsOfFriends;
    }

    public List<UserNode> showShortestPathBetweenTwoUsers(UserNode sourceUser, UserNode destinationUser) {
        LinkedList<UserNode> queue = new LinkedList<>();
        HashMap<Long, UserNode> visitedChildAndParentMap = new HashMap<>();

        if(checkIfSourceAndDestinationUserIsTheSame(sourceUser, destinationUser)) {
            return null;
        }

        setBackDistance();

        if(sourceUser.getFriends().contains(destinationUser)) {
            return Arrays.asList(sourceUser, destinationUser);
        }

        queue.add(sourceUser);
        sourceUser.setDistance(0);

        while (!queue.isEmpty()) {
            UserNode currentUser = queue.poll();
            Set<UserNode> friendsOfCurrentUser = currentUser.getFriends();
            for (UserNode friend: friendsOfCurrentUser
                 ) {
                if(friend.getDistance() == -1) {
                    queue.add(friend);
                    friend.setDistance(currentUser.getDistance() + distanceValue);
                    visitedChildAndParentMap.put(friend.getId(), currentUser);
                }

            }
        }

        return createPathList(destinationUser, visitedChildAndParentMap);

    }

    private List<UserNode> createPathList(UserNode destinationUser, HashMap<Long, UserNode> visitedChildAndParentMap) {
        LinkedList<UserNode> pathList = new LinkedList<>();

        pathList.add(destinationUser);
        UserNode child = destinationUser;

        for (int i = 0; i < visitedChildAndParentMap.size(); i++) {
            for (Long key : visitedChildAndParentMap.keySet()) {
                if (key == child.getId()) {
                    pathList.add(visitedChildAndParentMap.get(key));
                    child = visitedChildAndParentMap.get(key);
                }
            }
        }

        Collections.reverse(pathList);
        return pathList;
    }

    public boolean checkIfSourceAndDestinationUserIsTheSame(UserNode source, UserNode destination) {
        if(source.getId() == destination.getId()) {
            System.out.println("The source and the destination user is the same.");
            return true;
        }
        return false;
    }

    public List<UserNode> getUsers() {
        return users;
    }

    public void setBackDistance() {
        for (UserNode user: users
        ) {
            user.setDistance(-1);
        }
    }
}
