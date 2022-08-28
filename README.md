## Satellite Observation Tracker

One of the hobbies that I enjoyed during the relative isolation of the COVID-19 pandemic was spotting satellites in orbit. On a clear summer night, several brighter satellites can be seen for about an hour or two after sunset as they reflect the sunlight still shining on them, before the sky (space, really) above passes fully into the earth's shadow.

There are plenty of websites that will help you identify the satellites you have seen or show you where to look for a satellite as it passes overhead, but I am not aware of tools for recording observations and keeping track of them. I have just used a text file for that purpose and it has worked well for me. This application is the _start_ of a tool for recording and tracking observations. I worked on this application as part of my interviewing process when I was interviewing for a software engineering position at [Rhove](https://www.rhove.com/), where I would go on to work until August of 2021.

The web interface is a bit anemic -- it just shows a (poorly-formatted) list of recorded observations. I've never been particularly interested in web development. My aim was to demonstrate competence in backend development and the ability to work with the frameworks prescribed.

The REST API has more features. You can list observations, `POST` a new observation, and search for observations by satellite name or COSPAR ID.

### Running the App

You can start the app using `./gradlew bootRun`. This app uses MongoDB, so that will need to be running.

### What I Learned

This exercise gave me some quick exposure to a variety of different technologies, some of which I had not used in a while, and a couple which were completely new to me. Here are some of the highlights of what I've learned:
 * Using Spring Data's support for MongoDB, you can define a repository interface with minimal code and Spring will generate the implementation at runtime. You can even include methods like `findByArbitraryField(String value)` and Spring will generate a method that uses a query that will find documents in which the value of `"arbitraryField"` matches the given argument.
 * I learned how to use Spring's `MockMvc` to write integration tests that look like end-to-end tests accessing a REST API, but actually work without using the server or running "real" requests with inter-process communication. I was aware of this capability, but had never set it up myself.
 * I learned a little bit about Thymeleaf and some of the basics of writing templates.

### What's next
If this app were meant for production, these are some of the steps that I would want to take to make it ready:
 * Configure the app to use a different Mongo database instead of the default `test` database
 * Configure integration tests to use a separate Mongo database rather than using the same one that is used when the app runs
 * Store user information in MongoDB and remove `inMemoryAuthentication()` (and don't hardcode username and password in the source code!)
 * A better web interface -- the current one is woefully incomplete. Better handling of the observation data. And formatting to make it look presentable.
 * "Negative" tests for REST API requests
 * Add a collection for satellites with fields for name, COSPAR ID, catalog number, and possibly other information. Add REST API endpoints for saving and retrieving satellite records. Satellite observations could reference saved records for particular satellites.
 * Add validation for COSPAR ID (It fits a very specific format. See https://en.wikipedia.org/wiki/International_Designator)
 * Modify web interface to make it easy to search observations by name or COSPAR ID (this is already in the REST API)
