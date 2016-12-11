# Ski Resort Logger (v2)

**Current Priorities:**
- Better production setup.
  * Jenkins copies to archive. Should I be copying this somewhere else for exectuting? /var/lib/jenkins/.m2/repository/ca/kevinlowe/hello-world/1.0-SNAPSHOT/hello-world-1.0-SNAPSHOT.jar
  * Proper location to store a crontab script. How to install.
- Testing. Two examples:
  * Test that I parse all lift that I expect.
  * Test that application runs (eg. Fail after maven shade minimal).
  * Setting up InfluxDB testing database. What's a good strategy to ensure I don't interfere with production?
  
**Notes for the future:**
- Jackson XML/JSON parser
- Jetty web service

**Completed:**
1. Parsing multiple lifts.
2. InfluxDB publishing
3. Jenkins build server
4. Github > jenkins triger
5. Maven shade plugin
6. Log4J
7. Roughed out cron job.
