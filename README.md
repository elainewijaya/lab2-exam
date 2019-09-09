# lab2-exam

This program creates an exam program where exam questions and answers can be added within the program, and then saved and read
in an XML file (created upon compiling). It takes in three types of questions - multiple choice, true or false, and short answer questions - which are all subclasses of the abstract class 'Question'. This is to ensure that each question has the essential functions "printQuestion" and "getAnswer". "Question" is kept abstract since it is meant to be subclassed, not initalised.

The three types of question classes are almost identical, since all exam questions and answers essentially consist of a question string and an answer string. The reason we distinguish between the three types so that the exam is able to parse the user's input accordingly during runtime (e.g MCQs can only take in the given options, True/False can only take in True or False inputs, etc). However, the classes MCQ, short answer and True/False themselves are kept as simple as possible to make storing these questionsas simple and intuitive as possible.

In a way, this is to mirror the actual process of creating and grading an exam. The answer key and questions are always printed and set in stone, however, the one doing the real work of checking how the answers match up is the person marking the exam (in this case, the takeExam() program that parses inputs and chooses to add to the score or not).
