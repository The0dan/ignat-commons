<figure>
    <img src="images/IgnatCommons-900x400.jpg" alt="Central Park looking south, Manhattan, New York City.  (Photo by Ingus Kruklitis)">
    <figcaption><sup><em>Central Park looking south, Manhattan, New York City.  (Photo by Ingus Kruklitis)</em></sup></figcaption>
</figure>

# Ignat Commons

Ignat Commons is Core Java common code that I've found myself reusing many times over the years.

## Versions

The latest release is [2.0.0](https://search.maven.org/artifact/name.ignat/ignat-commons/2.0.0/jar), available in the Maven Central artifact repository, retrievable via all Java dependency managers.

See [Versions](docs/Versions.md) for a release history.

## Dependencies

Currently the only dependencies are on *Google Guava* and *Apache Commons Lang3*, both `implementation`-scoped.  These two libraries are ubiquitous, and most projects likely already use them.  Ignat Commons has been tested against both recent and the earliest versions of these two libraries, and has left their version ranges wide open, so as to defer version resolution to client projects per their specific needs.

Care has been taken to avoid other dependencies, such as Spring, as not all client projects may want them on their classpath.

## Exceptions

### `UnexpectedException`

Throw this in situations that would be code defects, or use it to wrap checked exceptions that are fatal and/or you
cannot meaningfully handle.

```java
try
{
    myInputStream.read(myByteArray);
}
catch (IOException e)
{
    throw new UnexpectedException(e);
}
```

### `UnexpectedCaseException`, `UnexpectedTypeException`

Throw these from `else` and `default` statements to give your conditionals useful default behavior.  This makes them
fail-fast if you forget to update them after their universe of values grows.

```java
if (value == 1)
{
    ...
}
else if (value == 2)
{
    ...
}
else
{
    throw new UnexpectedCaseException(value);
}
```

```java
switch (value)
{
    case 1:
        ...
        break;
    case 2:
        ...
        break;
    default:
        throw new UnexpectedCaseException(value);
}
```

```java
if (shape instanceof Circle)
{
    ...
}
else if (shape instanceof Rectangle)
{
    ...
}
else
{
    throw new UnexpectedTypeException(shape);
}
```

## Utilities

General utilities, as well as some enhancements to utilities in the JDK, *Google Guava*, and *Apache Commons*.

### Lists

```java
<T> T getFirst(@Nonnull List<T> list)
<T> T getLast(@Nonnull List<T> list)
```

### Files

```java
FileOutputStream openNewOutputFile(File file)
FileOutputStream openNewOutputFile(File file, boolean safely)
```

### Resources

```java
InputStream getResource(String resourcePath)
File getResourceFile(String resourcePath)
String getResourceText(String resourcePath)
```

### Arrays

```java
<T> T[] of(T... values)
```

### BigDecimals

```java
BigDecimal normalizeCurrency(BigDecimal bigDecimal)
BigDecimal normalize(BigDecimal bigDecimal, int scale)
String toSimpleString(BigDecimal bigDecimal)
```

### Doubles

```java
String toCompactString(double d)
```

### Enums

```java
<E extends Enum<E>> boolean equalsAny(String name, E... candidates)
```

### Floats

```java
String toCompactString(float f)
```

### Objects

```java
<T> boolean equalsAny(T object, T... candidates)
<T> String toLinesString(Iterable<T> objects)
```

### Strings

```java
boolean containsAnyIgnoreCase(CharSequence string, CharSequence... candidates)
List<String> findAllMatches(String patternString, CharSequence input)
List<String> findAllMatches(Pattern pattern, CharSequence input)
```
