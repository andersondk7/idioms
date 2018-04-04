# idioms
## Purpose
This is a simple how-to for some scala idioms that needed some further explaination and examples.  
The examples are drawn from:
* the excellent set of blog posts from _Daniel Westheide_'s blog [neophytes Guide to Scala](http://danielwestheide.com/scala/neophytes.html) or [book](https://leanpub.com/theneophytesguidetoscala). 
* [Functional Programming in Scala](https://www.manning.com/books/functional-programming-in-scala)
* other web sites
* personal examples

I have added _**excessive**_ comments to the code with the intent that when I come back to these concepts weeks/months later I don't have to re-read the book, blog, etc. but can hopefully remember the concepts.

## Samples
### High Order Functions
These are functions that take values and functions and return other functions.  
The example is in the ```org.aea.highorderfunctions``` package and centers around the examples expanded and commented from the sections in [Part 10](http://danielwestheide.com/blog/2013/01/23/the-neophytes-guide-to-scala-part-10-staying-dry-with-higher-order-functions.html) and [Part 11](http://danielwestheide.com/blog/2013/01/30/the-neophytes-guide-to-scala-part-11-currying-and-partially-applied-functions.html).  They are based on the ```Email``` case class and 2 objects that have many high order functions, ```EamilFilter``` and ```EmailTransformation```.  
