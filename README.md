# BETest

#### Things that can be improved : Add javadoc, better package naming and class distribution.

#### Liberties taken : 

1. The Credit Limit column will always be right aligned irrespective of the source.
2. For simplicity's sake I have used Apache CSV parsers to parse the CSV file.
3. Have used Apache commons-io for file related operations and Apache commons for String related operations.

#### Constraints: 

1. This program has been written in Eclipse. Hence the build instructions for exclusively for that. 
2. Uses gradle for building the project. If your system does not have gradle you might need to install that.

## Instructions to import and execute csvprn2html program.

1. Clone the repository from `https://github.com/MarktplaatsShowAndTell/BeTestAjaiKuruppath.git` to a location in your computer.
2. Go to the directory where you have clone the project via command-line. `cd` into the `csvprn2html` directory.

#### For Unix based systems make use of *gradlew* script and for Windows make use of *gradlew.bat*. This guide is primarily for Unix based systems.

#### Set things up

3. Check if the `gradlew` and `gradlew.bat` files are executable. If not make them executable.

4. For Unix systems (linux, mac) run:
+ `./gradlew clEclEcl EclCl assemble`
This will generate the class-paths for Eclipse IDE.

+ `./gradlew clean build`
This will run all the tests and also build the project

+ `./gradlew clean test`
This will re-run all the tests.


#### In order to import the project into Eclipse

1. Import the project into eclipse.

2. Sometimes it can happen that Eclipse does not refresh the project and dependencies fully. So right click on the project and click on `Refresh`.

#### In order to run the project from Eclipse

1. To run the program from Eclipse, open the Main.java file. Right click on the file and select `Run As -> Run Configurations`

2. Select `Arguments` tab.
3. In the `Program Arguments` enter the `input- location` and the `output-location`.
+ Example usage: `/home/input .`
In this the `input-location` is `/home/input` and the `output-location` is the current working directory represented by `.`.
4. Click `Apply` and `Run`.

#### In order to run the program from command-line
1. Run command `./gradlew shadowJar`. This command will
   generate an executable jar with all the required dependencies in the `build/libs`  directory that lies relative to project checkout directory. The jar is identifiable with its name `csvprn2html-1.0-all.jar`.

2. You can choose to run the jar from `build/libs` or
   or any other directory of your choice.
3. Invoke the jar as `java -jar csvprn2html-1.0-all.jar <input-location> <output-location>` from whichever directory you have the jar.
4. The `input-location` can be an absolute path or anything relative to the directory from where you are executing the `csvprn2html-1.0-all.jar` from.
5. As the name suggests the `input-location` is the location where the program will look for `*.csv` or `*.prn` files.
Only these 2 file types are supported. The `output- location` is where the generated `html` file(s) will be dumped to.
6. Example usage: `java -jar csvprn2html-1.0-all.jar /home/input .`
On invoking this command the program will look for `*.csv` and/or `*.prn` file(s) in the location `/home/input`. If it finds the files it will generate the corresponding `html` file(s) and dump them into the current working directory.

#### Naming conventions used for the generated HTML files

The generated HTML files are named in such a way to make it easier to understand the source from where they were generated. Also to simplify things
the instant at the time of generation is used in the name so as to distinguish between files having the same name prefix.
The naming convention used is as follows : `<name-without-extension>_<extension>_<current time instant>_.html`
+ eg: The HTML file generated somewhere around 21:43 hours on the 7th of February 2020 from the contents of `Workbook2.csv`  would be `Workbook2_csv_2020-02-07T21:43:44.701618Z.html`.

## Why?

We are interested in your skills as a developer. As part of our assessment, we want to see your code.

## Instructions

In this repo, you'll find two files, Workbook2.csv and Workbook2.prn. These files need to be converted to a HTML format by the code you deliver. Please consider your work as a proof of concept for a system that can keep track of credit limits from several sources.

This repository is created specially for you, so you can push anything you like. Please update this README to provide instructions, notes and/or comments for us.

## The Constraints

Please complete the test within 5 business days. Use any language / libs / tools you like.

## Questions?

If you have any questions please send an email to DL-eCG-NL-assessment@ebay.com.

## Finished?

Please send an email to DL-eCG-NL-assessment@ebay.com let us know you're done.

Good Luck!


Copyright (C) 2001 - 2020 by Marktplaats BV an Ebay company. All rights reserved.
