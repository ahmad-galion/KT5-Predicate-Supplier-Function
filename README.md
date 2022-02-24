# KT5-Predicate-Supplier-Function

Supplier
A Supplier is a simple interface which indicates that this implementation is a supplier of results. 
This interface, however, does not enforce any restrictions that supplier implementation needs to return a different result on each invocation.
The supplier has only one method get() and does not have any other default and static methods.


Predicate
A Predicate interface represents a boolean-valued-function of an argument. 
This is mainly used to filter data from a Java Stream. The filter method of a stream accepts a predicate to filter the data and return a new stream satisfying the predicate.
A predicate has a test() method which accepts an argument and returns a boolean value.


Function
A Function interface is more of a generic one that takes one argument and produces a result. 
This has a Single Abstract Method (SAM) apply which accepts an argument of a type T and produces a result of type R.
One of the common use cases of this interface is Stream.map method.

Consumer
A Consumer is a functional interface that accepts a single input and returns no output.
 as the name suggests the implementation of this interface consumes the input supplied to it. 
 Consumer interface has method void accept(T t);
