# Ignat Commons

*Ignat Commons* is some common code that I've found myself reusing many times over the years:

1. `UnexpectedException`
   - Three exception classes useful for:
     - wrapping checked exceptions that you cannot meaningfully handle
     - making your `if` and `switch` statements fail-fast when the universe of values that they operate on grows in the future
2. `TypeSafeChain`
   - Type-safe extensions of *Apache Commons Chain* classes that enforce context compatibility
3. Utility classes
   - A few small utility classes that enhance *Apache Commons Lang* utility classes by providing conveniences like varargs parameters and standardized retrieval of classpath resource files
