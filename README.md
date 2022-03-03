# Project Flash

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Project Flash is a study aid app that allows students to create and view public flashcard sets in a variety of subjects. Users can share, create and save flash card sets offline to their devices.

### App Evaluation
- **Category:** Education/Acadamic
- **Mobile:** This app is primarily meant for mobile but integrating a website is possible as the data is saved to a database. 
- **Story:** Allows user to view study sets as well as show them different categories for their desired subject. Users can also rate the quality of sets. 
- **Market:** The general market is towards students of any ages as well as anyone who wants to learn or memorize concepts.
- **Habit:** The app would be used relativley often for those who need to study for an exam or who need quick refference for a speech.
- **Scope:** We would start with the focus on user creating flash cards for their studies. Adding social aspects such as commenting, sharing the card sets could potentially evolve the app an academic online learning commnity. 



## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can view study sets while not signed in
* User can sign in for added functionality (saving sets, making sets, etc)
* A functioning flashcard set that is easy to use
* Home page with sets you made as well as ones you followed
* Rating system for sets
* Discover page with different categories of sets
* If logged in, user Profile displays information such as user description/bio, location, date joined, count of card sets.

**Optional Nice-to-have Stories**

* Having a social feature to share sets between users
* An algorithm that can recommend sets to a user
* Optional Shuffle Button (i.e. random flashcards)
* Chat for users to communicate (direct 1-on-1)
* Implemented the maximum amout of flash cards uesrs can have in a set.
* User can their cards offline to their devices either by caching or saving the flashcard set locally. 


### 2. Screen Archetypes

* Login
    * When a user is logged in, they can access the following
        * Home page with sets they are following/created. The user will see a Recycler View of the sets they followed or anything they made. 
        * Categories tab that organizes sets by type (by subject). User see's a RecyclerView with variety of subjects and when a subject is clicked on, they can scroll through the sets that have been made. They can click on the set and see the flashcard set screen.
        * Make a Card Set Tab allows the user to make a flashcard set by adding a name to the set, the category it belongs to, add flashcards, edit info within the flash cards, and publish the set.
        * Profile Tab With Customization and Sign Out Option allows the user to see their username, their made sets, and sign out. 
* App View When Not Logged In
    * User can acess the following tabs when not logged in:
        * Home page with all sets that can be viewed. These would be every set that is avaiable on the platform and users can scroll through them and explore.  
        * Categories tab that organizes sets by type. Ditto as the previous archetype for when signed in.
        * Sign In/Make Account tab. The user gets the ability to sign in to their account or make a new account.



### 3. Navigation

**Tab Navigation** (Tab to Screen)
## When Signed In
* Home Screen
* Categories
* Make a Set
* Profile
## When Signed Out 
* Home Screen
* Categories
* Create Account/Sign In

## Optional
* Chat Feature to Talk to Others

**Flow Navigation** (Screen to Screen)
* If not logged in, Home set page -> Categories tab

* If signed in, Home Page -> Categories -> Make a Set -> Profile

## Wireframes
<img src="https://i.imgur.com/YiQaj6K.jpg" width=800><br>

### Digital Wireframes & Mockups
<img src="https://i.imgur.com/hPd1Gp4.gif" height=200>
