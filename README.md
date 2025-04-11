Hello, welcome back! I am Fernando Flores (FXF220008). This is my submission for project 2.

I developed my program using IntelliJ, and used the Java JDK 23 to compile and run this program.
There shouldn't be any issues using any other JDK versions, as it runs just fine on OnlineGDB.

The program uses command line arguments to enter an input and outfile. On OnlineGDB, there is an
empty box that says "Command line arguments". Here, you need to write <input.file> <output.file>
(input.txt output.txt) otherwise the program will not display output.
The link to the program on OnlineGDB is here: https://onlinegdb.com/uzFDFk2nHJ

My project consists of 2 Java files:

    - Main.java
    - LazyBinarySearchTree.java

You can run the program without any IDE by going to OnlineGDB and pressing "Run". You may need to
add the command line arguments at the bottom. If you want to use IntelliJ, import the Java files
and input/output files, and add the command line arguments that correspond to the appropriate
file names. Then, with Main class open, click on run. If you get an error message in the console,
it means you did not provide the arguments or do not have the files. Otherwise, the program will
print to the output file.

The program supports the following built in commands:

    1. Physical tree node insertion
    2. "Undeleting" tree nodes when inserting
    3. "Deleting" of tree nodes
    4. Finding the minimum/maximum nodes that are not "deleted"
    5. Finding if the tree contains a desired node
    6. Printing of the tree and nodes that are marked as deleted with an asterisk (*)
    7. Return the height of the tree
    8. Return the size of the tree

The program also supports various error handling and input validation checks, such as:

    - Integer range input verification [1-99]
    - Case-sensitive command syntax validation
    - Accessing empty/null tree error handling (will return either true/false or -1)
    - Command line argument input validation

Here is a sample run. When you start the program, assuming the command line arguments and input/output
files are present, the console will say this:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Arguments were successfully provided. Check output file for the results.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Then, open the output file. Below is an example of both files opened side by side.
You are welcome to put in your own commands!

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    INPUT.TXT:                  OUTPUT.TXT:
    FindMin                     -1
    FindMax                     -1
    Height                      -1
    Size                        0
    Insert:50                   true
    Insert:53                   true
    Insert:42                   true
    Insert:35                   true
    Height                      2
    Size                        4
    FindMin                     35
    FindMax                     53
    PrintTree                   50 42 35 53
    Delete:42                   true
    PrintTree                   50 *42 35 53
    Height                      2
    Size                        4
    FindMin                     35
    FindMax                     53
    hi                          Error in Line: hi

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If you did not provide command arguments, or are missing those files, the console will say this:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    Warning: It looks like there is no arguments provided. Please check your arguments.
    Usage: <input_file> <output_file>

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

That's pretty much it for the readme. Thanks again for reading if you made it down here! :)
