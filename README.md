# de.buehmann.fnirsi.dps

Unofficial Java library to communicate with FNIRSi DPS-150.

## Usage on Linux

### Required Access Rights

Start ```AddGroupOnLinux.sh``` to add your user to the user group
needed to get access to the serial device.

### Build Library

Execute

```
gradle build
```

### Run Example

Execute

```
cd build/distributions
unzip de.buehmann.fnirsi.dps-0.0.1.zip
./de.buehmann.fnirsi.dps-0.0.1/bin/de.buehmann.fnirsi.dps
```

Type h to get a list of possible functions provided by the example
application.



Inspired by https://github.com/cho45/fnirsi-dps-150/

