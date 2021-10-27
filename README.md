# call-list
Call List Rest API

## Setup

Just pull the pom into Eclipse or Intellij and everything should load up.

## Running

You should just be able to execute the CallListApplication class through your IDE. I used Intellij and it has some built in spring boot support.

To run it from the command line, run the below command at the root level of the project.

```sh
mvn spring-boot:run
```

The database is wiped and recreated with each restart, so there isn't a database file to pass along. If you wanted to persist the db, you could add the below to the __application.properties__ file instead of the jdbc url that is already there.

```properties
spring.datasource.url=jdbc:h2:file:~/call-list-db
```

## Short Cuts/Things I would do better

- Probably go back to my original design of having a unique combination of PhoneNumber and PhoneType but maybe add the Person Id into the key.
- Do some duplicate checking to make sure that people aren't being entered more than once.
- Make a user for the application to use, don't use the sa user.
- Use a database that doesn't live in memory.
- The mapping to and from db and dto objects made there be seams in the code where I didn't want there to be. Plus the overlap of the same PhoneType in both spots, but with the way I set everything up it would be dumb to duplicate it.
  - The mapping also made some weird incrementing happen with the Ids. They would skip numbers.
- Figure out the issue with the JPA query for finding by home phone so that I didn't need to right a manual query for it. Maybe I'm thinking there is more smarts built into it than there is, but I could have sworn that you could drill into sub-objects with underscores.
- Logging... I didn't add any logging, but would go back and include SLF4J for logging.
- Add in sorting by adding it directly to the custom query or passing in two chained sort objects.

### Testing

The testing is a bit of a mess. All the tests other than the enum are more integration/end to end tests because they are talking to the db and not testing layers in isolation. I felt like this was the best approach for a small application like this because test runtimes weren't terrible.
If this wasn't just a small project, I would go back and straighten the testing situation out so that Integration tests were properly labeled and they ran in the correct maven lifecycles. Also I would have written an end to end test.


# TODO
- [ ] Unique phone number to prevent duplicates
- [ ] Make email address a unique constraint?
- [ ] Don't store creds in a public repo
- [ ] Make a user for the app to use, don't use the sa account.
- [ ] I don't like that the PhoneType of the db and dto overlap, but don't see a reason to duplicate a class for no reason other than smell.
- [ ] The ids aren't sequential, they skip a few numbers each time. I believe this is because of the mapping back and forth and generating new Person objects.
- [ ] The contact service test is a bit odd since everything is using the same object. The update I wasn't able to test the phone number consistently, so I took that part of it out for now. Should probably look into scripting the object setup insted
