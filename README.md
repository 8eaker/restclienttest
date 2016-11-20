Building the app
=================
cd to the project root direct - TideSocial
mvn clean install
All tests will be run and jar will be build containing all required dependencies

Running the App
===============
cd to TideSocial/TideSocialApp/target
there will be social-rank-app-1.0-SNAPSHOT-jar-with-dependencies.jar
java -jar social-rank-app-1.0-SNAPSHOT-jar-with-dependencies.jar
The service should now be running

Testing the app
================
There are 3 mocked stories in the app with ids 1,2 & 3
Using a Rest tool such as postman hit the following URLs

Get Story rank example
http://localhost:8090/story?id=3

Like a story
http://localhost:8090/story?id=3/like

Dislike a story
http://localhost:8090/story?id=3/dislike

Explicitly set a story rank
http://localhost:8090/story?id=1&rank=99

Thoughts
========
Initial thoughts as to how to achieve this app immediately made me think Spring, however as the spec suggests to not use any framework, that was an initial curve ball!
I could have used a simpler architecture to achieve the results, however as this is meant to demonstrate future develop etc, I chose a more complex implementation. Maybe in hindsight it was a little ambitious.
Overall, I probably spent about 5-6 hours on the test, however, it was a bit here and bit there so wasted some time getting back into after leaving for a number of days after starting due to some real life distractions!

