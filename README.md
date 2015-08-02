# Ignat Commons

*Ignat Commons* is common code that I've found myself reusing many times over the years:

1. `UnexpectedException`
   - Wrap checked exceptions that you cannot meaningfully handle
2. `UnexpectedCaseException`, `UnexpectedTypeException`
   - Give your conditional statements useful default behavior
     - Make them *fail-fast* if you forget to update them after their universe of values grows
3. `TypeSafeChain`
   - Type-safe extensions of *Apache Commons Chain* classes that enforce context compatibility
4. Utilities
   - Enhance *Apache Commons* and *Spring Framework* utility classes with conveniences like:
     - varargs parameters
     - standardized retrieval of classpath resource files
