# Crawler example

## Google Docs instructions
https://docs.google.com/document/d/1COHVMmcklIs8SdHw3T1sujVeUducoMzMpixHzBWD7f8/edit?usp=sharing

## Jasper TODO
Done crawling, 52 links visited. (crosscheck: 52)
Dat zijn er vast meer...

## Docker run
To compile your code using Docker (and a default local cache), run the following: 

`./scripts/01-build`

To actually run this script using the default Main class (`com.yacht.BasicCrawler`), run:

`./scripts/02-run`


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