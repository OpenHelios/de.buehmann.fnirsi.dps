# io.github.openhelios.fnirsi.dps

Unofficial Java library to communicate with FNIRSi DPS-150.

## Required Access Rights

### Linux

Start `AddGroupOnLinux.sh` to add your user to the user group
needed to get access to the serial device.

### Windows

Install device driver.

## Build Library

### Build with Maven

1. Install `mvn`, if not already installed.
2. Execute

```
mvn install
```

### Build with Gradle

1. Install `gradle`, if not already installed.
2. Execute

```
gradle build
```

## Run Example

Plug-in USB cable.

### Linux

Execute

```
cd build/distributions
unzip io.github.openhelios.fnirsi.dps-1.0.0.zip
./io.github.openhelios.fnirsi.dps-1.0.0/bin/io.github.openhelios.fnirsi.dps
```

### Windows

* Extract ZIP file `io.github.openhelios.fnirsi.dps-1.0.0.zip` from
  sub folder `build/distributions`.
* Start extracted file
  `io.github.openhelios.fnirsi.dps-1.0.0/bin/io.github.openhelios.fnirsi.dps.bat`

## Usage of Example

Type h to get a list of possible functions provided by the example
application.

## Usage of Library

Choose one of the following Maven, Gradle or manual dependency.

### Add Maven Dependency

Add the following Maven dependency to your pom.xml file:

```
<dependency>
	<groupId>io.github.openhelios</groupId>
	<artifactId>fnirsi.dps</artifactId>
	<version>1.0.0</version>
</dependency>
```

### Add Gradle Dependency

Add the following Gradle dependency to your build.gradle file:

```
implementation 'io.github.openhelios:fnirsi.dps:1.0.0'
```

### Download Dependency Manually

Download the [JAR file](https://repo1.maven.org/maven2/io/github/openhelios/fnirsi.dps/1.0.0/fnirsi.dps-1.0.0.jar) manually, if you are using no build tool.

### Usage Example

See usage example in [Main.java](src/main/java/io/github/openhelios/fnirsi/dps/Main.java).


## Links

* Official manual: [https://www.fnirsi.com/pages/support-and-downloads](https://www.fnirsi.com/pages/support-and-downloads)

Inspired by [https://github.com/cho45/fnirsi-dps-150/](https://github.com/cho45/fnirsi-dps-150/)

