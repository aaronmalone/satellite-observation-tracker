## Satellite Observation Tracker

One of the fun hobbies that I've started this year is spotting satellites in low earth orbit. On a clear night, several brighter satellites can be seen for about an hour or two after sunset.

There are plenty of sites that will help you identify the satellites you have seen or show you where to look for a satellite as it passes overhead, but I am not aware of any tools for recording observations and keeping track of them. Up until now, I have used a text file for this purpose. This application is the _start_ of a better tool for recording and tracking observations.

The web interface is a bit anemic -- it just shows a list of recorded observations.

The REST API has more features. You can list observations, POST a new observation, and search for observations by satellite name or COSPAR ID.

### Running the App

You can start the app using `./gradlew bootRun`. This app uses MongoDB, so that will need to be running.

### What I Learned

This exercise gave me some quick exposure to a variety of different technologies, some of which I had not used in a while, and a couple which were completely new to me. Here are some of the highlights of what I've learned:
 * Using Spring Data's support for MongoDB, you can define a repository interface with minimal code and Spring will generate the implementation at runtime. You can even include methods like `findByArbitraryField(String value)` (for example) and Spring will generate a method that uses a query that will find documents where `arbitraryField` matches the given value.
 * I learned how to use Spring's `MockMvc` to write integration tests that look like end-to-end tests accessing a REST API but actually work without using the server or running "real" requests with inter-process communication. I was aware of this capability, but had never set it up myself.
 * I learned a little bit about Thymeleaf and some of the basics of writing templates.