# call-list
Call List Rest API

# TODO
- [ ] Unique phone number to prevent duplicates
- [ ] Make email address a unique constraint?
- [ ] Don't store creds in a public repo
- [ ] Make a user for the app to use, don't use the sa account.
- [ ] I don't like that the PhoneType of the db and dto overlap, but don't see a reason to duplicate a class for no reason other than smell.
- [ ] The ids aren't sequential, they skip a few numbers each time. I believe this is because of the mapping back and forth and generating new Person objects.
- [ ] The contact service test is a bit odd since everything is using the same object. The update I wasn't able to test the phone number consistently, so I took that part of it out for now. Should probably look into scripting the object setup insted
