# Project 0: Person201

## Obtaining Project Code and Project Details

For details and faqs, see [this document](docs/details.md) that has information on git, Java, the classes here, and more. This current file you're reading has project and coding details, but the linked document has more Java and git details, particularly about starting to code. That linked document includes information about project workflow including submitting code and analysis for grading via _Gradescope_. *Be sure to check [this document](https://coursework.cs.duke.edu/201fall23/resources-201/-/blob/main/projectWorkflow.md) for how to use Git to obtain the project files, and workarounds if Git doesn't work yet for you. You'll need to refer to this P0 document when forking and cloning.

Recall that according to class policy you should use Zoom to record the first 20 minutes you start coding -- not when you start reading, but when you're ready to code. You'll submit a URL for a cloud Zoom recording [using this link](https://bit.ly/201-p0-video).

## Goals

* To modify a simple Java class, modify two driver programs, and create a new class to demonstrate understanding of classes, methods, and instance variables.
* To learn about running Java programs using the VSCode IDE
* To learn about using Git for project management.
* To learn about CompSci201 workflow for assignments: Git, Gradescope, helper hours.


## Developing the classes in Project P0: Person201

When you fork and clone the project, you'll be working primarily within the src folder with .java files beginning with `Person201`. Your goal is to modify three programs/classes (`Person201.java`, `Person201Demo.java`, and `Person201NearbyDemo.java`) and create a new program (`Person201Farthest.java`) to generate the desired output. 


### Run `Person201Demo.java` and change `Person201.java`

First run the main method in `Person201Demo.java`, the output will be:

```
037.80N,122.27W claire courons
001.29S,036.82E ricardo harambee
040.71N,073.96W julie help
000.00N,000.00E null null
names: claire ricardo julie null 
```

Make changes to `Person201.java` by modifying the default constructor so the output will be as shown. Use the comments for the constructor 
to help understand how to do this, see the [help-doc/faq](docs/details.md) for more details.

```
037.80N,122.27W claire courons
001.29S,036.82E ricardo harambee
040.71N,073.96W julie help
035.93N,079.01W Owen woto
names: claire ricardo julie Owen
```

Now that you've done this, change the `main` method in file `Person201Demo.java` by creating a new `Person201` variable named `s` (short for Sam) with the value shown:

`Person201 s = new Person201("Sam", 44.9978, -93.2650, "hello");`

Add variable `s` after variable `c` (and before the call of `new`) in the definition of array `people` and run the program. The
output should be as shown here:

```
037.80N,122.27W claire courons
001.29S,036.82E ricardo harambee
040.71N,073.96W julie help
045.00N,093.27W Sam hello
035.93N,079.01W Owen woto
names: claire ricardo julie Sam Owen 
```

### Running Person201NearbyDemo, Modify Person201.java, and changing Data Source

Run the program `Person201NearbyDemo`. It reads a data file and finds all the people in the data file who are within 50 miles of a person named Ricardo who lives near Seattle. The program prints there are no people because the method `Person201.distanceFrom` returns the value _100_ instead of the correct distance to the parameter `other`. Modify the body of `Person201.distanceFrom` so that it calls
`Person201Utilities.distance` and passes the correct parameters to get the distance between `this` object and the parameter `other`. Then rerun
`Person201NearbyDemo` -- you should see data from 16 people who are within 50 miles of Ricardo.

Lines 20-22 specify three data sources: a regular text file named `large` in the `data` folder, an encrypted file in the same `data` folder (line 21), and an encrypted URL (line 22). Verify by commenting out/uncommenting each line that those sources all produce the same results, e.g., 16 people within 50 miles of Ricardo. After verifying the results are the same, write a new line that reads data from the URL specified by the
variable `largeURL`. You'll need to call the appropriate method in `Person201Utilities` to read a URL for a plain-text file. Verify that you get the same results since that URL references the same data as the file `large` in the `data` folder. You'll answer questions about these
runs in the Analysis section. 


### Create and Run a New Java Class: **Person201Farthest.java**

In the `src` folder create a new Java class named `Person201Farthest` (name the file `Person201Farthest.java`) that has only a `public static void main method` that allows the program to run. When run, the method should read the file `large.txt` in the `data` folder and determine the two `Person201` objects that are the farthest apart. You may find the code in `Person201NearbyDemo.java` useful in reasoning about the code you write. The program should print the objects that are farthest apart among all the objects created and returned when `PersonUtilities.readFile` is called from the code you write.

The `main` method you write *must* use the code below 
```
public static void main(String[] args) throws Exception {
        String file = "data/large.txt";
        double max = 0;
        Person201 a = null;
        Person201 b = null;
        // write code here 
        System.out.printf("farthest distance is %3.2f between %s and %s\n",max,a.getName(),b.getName());
    }
```


## Checklist

Before you submit to Gradescope, check that you've done each of the following:

- Modify `Person201.java` by changing code in two places.
- Verify that running `Person201Demo.java` matches the expected output after modifying `Person201.java`
- Add a new `Person201` object in the `Person201Demo.java` program and verify that running `Person201Demo.java` matches the expected output.
- Run `Person201NearbyDemo.java` after modifying the method `distanceFrom` in  `Person201`, and verify the results hold reading the same data 
four different ways: a text file, an encrypted text file, and the same via URLs.
- Create a new class `Person201Farthest` with a `main` method to find the people maximally distant from each other.

## Submission

You'll submit by pushing your code to Git and using Gradescope. Details can be found in [the details/faq](docs/details.md).

## Analysis

Answer all the questions here. As outlined in [this document](docs/details.md) you'll submit a PDF with your answers to Gradescope.

1. Look at the `.equals` method in `Person201.java`. How many instance variables are used to determine if two `Person201` objects are equal?
2. Suppose you're asked to write a method `sameName` in the `Person201` class with this method signature `public boolean sameName(Person201 other)`. This method should return `true` if and only if the `Person201` object `other` has the same name as this `Person201` object (on which the method is called.) Write the method and submit it as the answer to this question.
3. You ran `Person201NearbyDemo` with four data sources: a file, an encrypted file, a URL, and encrypted URL. Why do you think some lines were commented out rather than simply retyping a single line (e.g., suppose the only definition of `Person201[] people` was what's on line 20, and that line was replaced for each run).
4. Look at the code in `Person201Utilities` and the methods `readFile`, `readEncryptedfile`, and `readURL`. Explain in a few sentences the similarities and differences in these methods. In your answer reference the private method `readFromScanner`.
5. The online data for this project read via URL is encrypted. In a few sentences, why is it reasonable to have the online data encrypted?
6. Read/Skim (this description of AES](https://techjury.net/blog/what-is-aes/) the encryption algorithm used in this project, e.g., see the code in `FileEncryptor.java`. What are two things you learned from this article that you think are relevant to your understanding of Computer Science?
7. You must submit data using [this form](https://forms.office.com/r/wSdMU6NrKE).** You don't have to use your real name, though you can** . Ideally
you'll use a latitude, longitude that's relevant to you, e.g., where you grew up, went to high school, or anything. You'll need
to use the data from this file *after submitting and grading* as a final check on your program. In your analysis file, include the data you submitted via this form.
8. Course policy indicates you should submit a link to a video of you working at the beginning of this project. You should submit using [this form](https://bit.ly/201-p0-video). In your analysis document include as an answer to question 8 an affirmation "yes, I submitted the URL for a zoom recording" -- if, in fact, you did.


## Grading

Your submission will be graded by the following chart:

| Project Part | Points |
| ------ | ------ |
| Modify Person201 | 4 |
| Modify Person201Demo | 4 |
| Create Person201Farthest | 4 |
| Submitting personal data | 2 |
| Analysis | 8 |
| Post submission run| 4 |
| Zoom recording | 2 |

