# Project description
The project is implementation for a Bloom filter and solution for http://codekata.com/kata/kata05-bloom-filters/

## Maven commands
`mvn clean package` - builds the project, runs unit tests, and packages jar files of libraries

## Tests
### Unit tests
Each module contains a set of unit tests for implemented functionality.
Unit tests are disposed in the same for every module location `MODULE_NAME/src/test/java`

`mvn clean test` - builds the project and runs unit tests

## Modules
### bloomfilter
Contains implementation of a Bloom filter.
The tests set contains one integration test that is considered as a "smoke" test to check if 
Bloom filter with basic hash function works and returns "true" for indexed items and "false" for not indexed.

### spellchecker
Contains implementation of a Spellchecker.
There is one integration test here is for feeding a predefined dictionary in a test resources to verify how components
of a spellchecker read the dictionary and index the elements.

### utils
the module contains common classes that are used in other modules.

### try-out-spell-checker
The module contains usage of the spellchecker. The module is packaged in a JAR file with all required dependencies, and the resulting JAR can be found in `target` folder 
of the module.

#### Testing overall implementation of the spellchecker
File `com.alex538.sample.Dictionary` implements following test: reads a dictionary and loads the words into a spellchecker,
picks up random words from the dictionary, and verifies if the spellchecker contains these words.

The program can be launched with following command from a root directory of the project:
```shell
java -jar try-out-spell-checker/target/spellchecker-test-jar-with-dependencies.jar dictionary 5000 /usr/share/dict/words
```
Program arguments:
1. `dictionary` - name of test;
2. `5000` - number of random words picked up from a dictionary and then checked in the spellchecker
3. `/usr/share/dict/words` - path to a dictionary file

arguments 2 & 3 could be changed.

**sample output**
```text
alex538@alex538:~/sources/bloomfilter$ java -jar try-out-spell-checker/target/spellchecker-test-jar-with-dependencies.jar dictionary 5000 /usr/share/dict/words
23:06:23.053 [main] INFO com.alex538.sample.Dictionary - Random strings number: 5000
23:06:23.056 [main] INFO com.alex538.sample.Dictionary - Dictionary file: /usr/share/dict/words
23:06:23.424 [main] INFO com.alex538.sample.Dictionary - Collected 5000 random words from the dictionary
23:06:23.424 [main] INFO com.alex538.sample.Dictionary - Spell checker contains 101797 words
23:06:23.437 [main] INFO com.alex538.sample.Dictionary - All randomly picked words from the dictionary were found in the spell checker

```

#### Second optinal task in codekata
File `com.alex538.sample.RandomWord` implements optional second part of the task - calculates percent of false positive answers.
The logic inside is:
1. index OS dictionary by the spellchecker;
2. load OS dictionary in memory;
3. generate random string and check if the spellchecker returns false positive;
4. count false positives;
5. calculate a percent of false positives from the whole number of words.

Number of false positives is calculated by counting words on which `contains` method returns `true` and the word is not in the OS dictionary.
After that is simple calculation is applied: number of false positives / total random words = false positive rate.
Default false positive rate is `0.03` and calculated one is slightly more ~`0.0302`

The program can be launched with following command from a root directory of the project:
```shell
java -jar try-out-spell-checker/target/spellchecker-test-jar-with-dependencies.jar random 5000 /usr/share/dict/words
```
Program arguments:
1. `random` - name of test;
2. `5000` - number of random words to be generated
3. `/usr/share/dict/words` - path to a dictionary file

arguments 2 & 3 could be changed.

**sample output**
```text
alex538@alex538:~/sources/bloomfilter$ java -jar try-out-spell-checker/target/spellchecker-test-jar-with-dependencies.jar random 5000 /usr/share/dict/words
23:06:32.470 [main] INFO com.alex538.sample.RandomWord - Random strings number: 5000
23:06:32.473 [main] INFO com.alex538.sample.RandomWord - Dictionary file: /usr/share/dict/words
23:06:32.905 [main] INFO com.alex538.sample.RandomWord - Words were not found in dictionary, but were found in the spellchecker: 151
23:06:32.906 [main] INFO com.alex538.sample.RandomWord - Default false positive error rate is 0.03, calculated after the test run is: 0.0302
```

```text
alex538@alex538:~/sources/bloomfilter$ java -jar try-out-spell-checker/target/spellchecker-test-jar-with-dependencies.jar random 500000 /usr/share/dict/words
23:32:25.780 [main] INFO com.alex538.sample.RandomWord - Number of random words: 500000
23:32:25.782 [main] INFO com.alex538.sample.RandomWord - Dictionary file: /usr/share/dict/words
23:32:26.683 [main] INFO com.alex538.sample.RandomWord - Words were not found in dictionary, but were found in the spellchecker: 15490
23:32:26.684 [main] INFO com.alex538.sample.RandomWord - Default false positive error rate is 0.03, calculated after the test run is: 0.03098
```