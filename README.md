# de.buehmann.fnirsi.dps

Unofficial Java library to communicate with FNIRSi DPS-150.

## Required Access Rights

### Linux

Start `AddGroupOnLinux.sh` to add your user to the user group
needed to get access to the serial device.

### Windows

Install device driver.

## Build Library

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
unzip de.buehmann.fnirsi.dps-0.0.1.zip
./de.buehmann.fnirsi.dps-0.0.1/bin/de.buehmann.fnirsi.dps
```

### Windows

* Extract ZIP file `de.buehmann.fnirsi.dps-0.0.1.zip` from
  sub folder `build/distributions`.
* Start extracted file
  `de.buehmann.fnirsi.dps-0.0.1/bin/de.buehmann.fnirsi.dps.bat`

## Usage of Example

Type h to get a list of possible functions provided by the example
application.

## Usage of Library

See usage example in [Main.java](src/main/java/de/buehmann/fnirsi/dps/Main.java).

## Links

* Official manual: https://www.fnirsi.com/pages/support-and-downloads

Inspired by https://github.com/cho45/fnirsi-dps-150/

