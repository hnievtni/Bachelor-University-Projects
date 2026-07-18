Project description
Friends
Communication between users is characterized by a directed and weightless graph. Users can follow or unfollow each other. If a user posts, this post will be displayed in the panel of all his followers.
follow: if user x follows user y. We will have an edge from x to y. Suggestions
Suggestions among users are indicated by a directed and weighted graph. This proposal is defined by a proposal edge whose weight is its priority in the user graph.
suggest: if user y is suggested for user x, with priority z. We will have an edge with weight z from x to y. We define the users that are suggested to user x as follows:
Foreach User y in x.getFollowings() : x.suggest(y.getFollowings());
Based on the number of common followers that exist for each y, between (y.getFollowings), the priority of a user's proposal is determined.
If user y is recommended for x, and user x follows user y, y will be removed from his recommendation list.
cities
The distance between the cities is determined by an undirected and weighted graph. The weight of each edge indicates the distance between two cities. By default, the graph of cities is included in the Hard Code program and does not change.
Definitions of social network
Profile: The profile is specific to each user and can be viewed by each user. Includes username, bio, city, followers and followers, user posts
Home Page: It is a panel for a single user that the user can see after registering. Includes user posts, posts from his followers (let's say Feed), suggested users
Post: Freedom of expression is everyone's right! Every user can share something with the world. including title, text, author, number of likes and people who like
Features that your app should have: 1. Registration, Login, Logout: By registering, the user must choose their username, bio and city once and for all. 2. User search and profile viewing: Search for the user based on the desired user name and see his user profile. 3. Follow/Unfollow: You can follow a user or unfollow him if you don't like him! 4. Posting: tell about your daily life! This article will be displayed in the feed of all your followers.
5. Receive notification: When one of your followers posts an article, or someone likes one of your posts, you will receive a proper notification. Viewing notifications is from new to old and if you see a notification once, it will not be displayed again.
6. Viewing Feed posts in the panel: Every post that your followers put is placed in your special Feed section, and you can see all these posts in one of the following order:
• Time, ascending/descending • According to the alphabet of the article title, ascending/descending
7. Liking the post: You found a user's article interesting, support him! When viewing a post, you should be able to see the number and name of users who liked it, in addition to the title, content and author.
8. Viewing suggested users: Users are suggested to you based on their priority.
9. Suggestions of users near you: All users of the program are suggested to you based on the distance they have with you. When viewing this list, the distance of each user from you should be clear.
Features of the network admin: 1. Viewing the most popular users: View users in terms of popularity, from most to least.
Popularity: the sum of likes of all the posts of a user.
2. Viewing user activities: by searching for a user, view his recent activities from new to old.
Activity: including following, unfollowing, posting, liking
3. Identification of strongly connected components: observation of intimate teams in the network of users.
Strongly Connected Components: A strongly connected component is a subgraph of a directed graph that has a path from every vertex to every other vertex in that subgraph (it can only be defined in a directed graph).
Strongly connected components are subgraphs of that graph that are strongly connected. But that graph may not be strongly connected.
  Each post contains one or more hashtags, and the user can see all the posts with it by searching for a hashtag.
• The quality of private chat between people
  • Ability to create groups for users
All the required building data of the project must be implemented by you
The only data structure allowed is the simple array. ArrayList, Vector, etc. are not allowed and you have to implement them yourself to use them.