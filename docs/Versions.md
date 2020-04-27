# Version History

## 2.0.0

### Apache Maven

```maven-pom
<dependency>
    <groupId>name.ignat</groupId>
    <artifactId>ignat-commons</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Gradle

#### Groovy DSL

```gradle
implementation 'name.ignat:ignat-commons:2.0.0'
```

#### Kotlin DSL

```gradle
implementation("name.ignat:ignat-commons:2.0.0")
```

### Changelog

- Made dependencies use version ranges and be as loose as possible (ee9ed9b)
    - To maximize the library's usability in other projects
- Added more utilities (0728ae4)
    - `Lists`, `Files`, `Arrays`, `Doubles`, `Floats`, `Strings`
- Added `serialVersionUID` to `UnexpectedException` classes (4f651ca)
- Renamed classes:
    - `EnumUtils` -> `Enums` (c72149a)
    - `ObjectUtils` -> `Objects` (c72149a)
    - `NumberUtils` -> `BigDecimals` (76c85bd, 668d610)
    - `IoUtils` -> `Resources` (20cd52e)
- Moved utility classes to better-named packages (70c58cc, 54a4513, 8dcb1b4)
    - `.utils` -> `.collect`, `.io`, `.lang`
- Renamed, cleaned up, and added several utility methods (b562bca)
    - `Resources`
        - `getClassPathResource()` -> `getResource()`
        - `getClassPathResourceFile()` -> `getResourceFile()`
        - `getClassPathResourceAsString()` -> `getResourceText()`
        - Added a `*Cautiously()` version of each method that doesn't wrap with `UnexpectedException`
    - `BigDecimals`
        - `normalizeScale()` -> `normalizeCurrency()`
        - Added `normalize()` that can take a `scale` argument
        - Added `toSimpleString()`
    - `Objects`
        - Added `toLinesString()`
- Removed `CollectionUtils` (bb02e1b)
    - Its only method has since been added to `org.apache.commons.collections4.CollectionUtils`
- Removed `name.ignat.commons.chain` classes (840019c)
    - The Apache Commons Chain library seems very old and unmaintained (last release was in 2008), and I haven't found
    myself using it as much in recent years, instead preferring situation-specific collaborating classes
    - If I discover new use cases for it, and there isn't a better alternative out there somewhere, I will revive this
    type-safe implementation into a separate ignat-chain library and modernize it
- Drastically improved unit tests for quality and comprehensiveness
- Drastically improved Javadocs for quality and comprehensiveness

## 1.0.0

### Apache Maven

```maven-pom
<dependency>
    <groupId>name.ignat</groupId>
    <artifactId>ignat-commons</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

#### Groovy DSL

```gradle
implementation 'name.ignat:ignat-commons:1.0.0'
```

#### Kotlin DSL

```gradle
implementation("name.ignat:ignat-commons:1.0.0")
```

### Changelog

- Initial release (a4623c4)
