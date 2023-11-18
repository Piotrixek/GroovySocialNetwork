class Comment {
    String username
    String text

    Comment(String user, String comment) {
        username = user
        text = comment
    }
}

class User {
    String username
    List<String> posts = []
    List<Comment> comments = []
    Set<String> friends = []

    User(String name) {
        username = name
    }

    void post(String message) {
        posts << message
    }

    void addFriend(String friendName) {
        friends << friendName
    }

    void commentOnPost(String comment) {
        comments << new Comment(username, comment)
    }
}

class SocialNetwork {
    Map<String, User> users = [:]

    void addUser(String username) {
        if (!users.containsKey(username)) {
            User newUser = new User(username)
            users[username] = newUser
            println "User '$username' has been added to the social network."
        } else {
            println "User '$username' already exists in the social network."
        }
    }

    void postMessage(String username, String message) {
        User user = users[username]
        if (user) {
            user.post(message)
            println "Message posted by '$username': $message"
        } else {
            println "User '$username' does not exist in the social network."
        }
    }

    void printUserPosts(String username) {
        User user = users[username]
        if (user) {
            println "Posts by user '$username':"
            user.posts.each { post ->
                println "- $post"
            }
        } else {
            println "User '$username' does not exist in the social network."
        }
    }

    void addFriendship(String username, String friendName) {
        User user = users[username]
        User friend = users[friendName]
        if (user && friend) {
            user.addFriend(friendName)
            friend.addFriend(username)
            println "'$username' and '$friendName' are now friends."
        } else {
            println "Invalid usernames for adding friendship."
        }
    }

    void commentOnPost(String username, String targetUsername, String comment) {
        User user = users[username]
        User targetUser = users[targetUsername]
        if (user && targetUser) {
            if (targetUser.posts.size() > 0) {
                targetUser.commentOnPost(comment)
                println "Comment posted by '$username' on '$targetUsername' post: $comment"
            } else {
                println "User '$targetUsername' has no posts to comment on."
            }
        } else {
            println "Invalid usernames for posting a comment."
        }
    }

    void printUserFriends(String username) {
        User user = users[username]
        if (user) {
            println "Friends of user '$username':"
            user.friends.each { friendName ->
                println "- $friendName"
            }
        } else {
            println "User '$username' does not exist in the social network."
        }
    }
}

def socialNetwork = new SocialNetwork()

socialNetwork.addUser("Alice")
socialNetwork.addUser("Bob")
socialNetwork.addUser("Charlie")

socialNetwork.postMessage("Alice", "Hello, everyone!")
socialNetwork.postMessage("Bob", "Hey there!")

socialNetwork.addFriendship("Alice", "Bob")
socialNetwork.addFriendship("Alice", "Charlie")

socialNetwork.commentOnPost("Bob", "Alice", "Nice post!")
socialNetwork.commentOnPost("Charlie", "Alice", "I agree!")

socialNetwork.printUserFriends("Alice")
