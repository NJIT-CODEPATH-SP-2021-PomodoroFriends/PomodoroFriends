Unit 8: Group Milestone
===

# PomodoroFriends

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Allow users to define and execute Pomodoro timers. Timers are broadcast into a feed to implement a social function. Could potentially used in tandem with music, sharing for peer pressure or team building.

### App Evaluation
- **Category:** Productivity / Social Networking
- **Mobile:** This app would be primarily developed for Android.
- **Story:** Allows user to set parameters of a Pomodoro timer for their use. Executes timer for their productivity. Shares timer into a feed.
- **Market:** Any individual who wants to implement Pomodoro for task management could use this app. This app's social feature can also augment the pomodoro method for those that are socially motivated.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how they want to manage their productivity.
- **Scope:** First we would start with users of the pomodoro method and broadcasting their timers into a global pool, then perhaps this could evolve into customized friendlist or subscriber feeds. Further implementation of music served from youtube or spotify could enable a "study with me" live-type feature.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User logs in to access previously defined Pomodoro Timers
- [x] User can create new Pomodoro Timers based on "productivity time", "break time", and number of repetitions.
- [x] Users can view a global feed of all currently pomodoro timers in use, the timer's name, and a user graphic.
- [ ] User can manage a stored list of 5 timers.

**Optional Nice-to-have Stories**

- [ ] A customized feed of pomodoro friends more specific than the global feed.
- [ ] The ability to sync with another user's pomodoro timer
- [ ] Friendslist
- [ ] Implement music or sounds into the pomodoro timer.

**App Walkthrough UNIT 10**

<img src="https://github.com/NJIT-CODEPATH-SP-2021-PomodoroFriends/PomodoroFriends/blob/main/Images/demo.gif" width=200><br>

### 2. Screen Archetypes

* Login
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with another person.
   * ...
* Select Pomodoro Screen
   * Allows user to view saved pomodoro timers and select them to enter Timer Screen
* Setup Screen
   * Allows user to define a new Pomodoro Timer
* Timer Screen.
   * Allows user view their executed pomodoro timer including time left, periods left, and estimated times.
* Feed Screen.
      * Allows user view global list of ongoing pomodoro timers.
* Settings Screen
   * Lets user change profile image.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Feed
* Setup
* Current Timer
* Pomodoro Basket (Store of timers)
* Settings

Optional:
* Music/Encounter Queue
* Discover (Top Choices)

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Pomodoro Selection (Or Feed if Optional)
* Setup -> Define a new pomodoro timer to store for user.
* Settings -> Profile Image to be modified.

## Wireframes
<img src="https://github.com/NJIT-CODEPATH-SP-2021-PomodoroFriends/PomodoroFriends/blob/main/Images/wireframe.png" width=800><br>

### [BONUS] Digital Wireframes & Mockups
N/A

### [BONUS] Interactive Prototype
N/A

## Schema

### Models
#### Post
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| timer author |
   | caption       | String   | timer caption/description by author |
   | createdAt     | DateTime | date when timer is created (default field) |
   | timerLengthA     | int | length of time in seconds for 'A' period of Pomodoro Timer |
   | timerLengthB     | int | length of time in seconds for 'B' period of Pomodoro Timer |
   | period     | int | how many times to repeat the AB pattern |
   | active     | boolean | indicates whether or not this pomodoro is in use |
   | startedAt     | DateTime | date when timer is activated. used to calclate current period and end of timer. |



### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all timers from global authors
   - Create Post Screen
      - (Create/POST) Create a new pomodoro post
   - Profile Screen
      - (Read/GET) Query logged in user pomodoro timers
      - (Update/PUT) Update user profile image