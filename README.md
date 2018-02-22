# Crawler example

## Google Docs instructions
https://docs.google.com/document/d/1COHVMmcklIs8SdHw3T1sujVeUducoMzMpixHzBWD7f8/edit?usp=sharing

## Docker run
To compile your code using Docker (and a default local cache), run the following: 

`./scripts/01-build`

To actually run this script using the default Main class (`com.yacht.VacatureCrawler`), run:

`./scripts/02-run`

You don't need the "Maven setup" steps below, as the Docker scripts do this for you

## Maven run

If you're not into the whole Docker thing, that's fine. Here's the command we use inside the container.

Make sure to folow the "Maven setup" below.

`mvn clean compile 
     exec:java -Dexec.mainClass="com.yacht.VacatureCrawler"`

Your IDE can probably run that file for you as well.

## Maven setup
Add the following to your ~/.m2/settings.xml file:
```
<profile>
    <id>jasper-nexus</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <repositories>
        <repository>
            <id>jasper-nexus</id>
            <name>Jasper's Nexus</name>
            <url>https://nexus.jasperroel.nl/repository/maven-public/</url>
        </repository>
    </repositories>
</profile>
```
If you have never done this before, see https://maven.apache.org/guides/mini/guide-multiple-repositories.html for more
information.

There is also an fully usable example in `settings.xml`. Copy that to your `~/.m2` folder and you're done!