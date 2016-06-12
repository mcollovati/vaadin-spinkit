# SpinKit Add-on for Vaadin 7

SpinKit for Vaadin is an UI component add-on for [Vaadin 7](http://vaadin.com) 
that integrates [SpinKit](http://tobiasahlin.com/spinkit/) CSS spinners.

A live demo is available at http://vaadindemo-mbf.rhcloud.com/spinkit/.
   
## Usage

Add this dependency to your pom

```xml   
    <dependency>
        <groupId>org.vaadin</groupId>
        <artifactId>spinkit</artifactId>
        <version>ENTER LATEST VERSION</version>
    </dependency>
```

then recompile the widgetset and use the component
   
```java
Spinner spinner = new Spinner(SpinnerType.ROTATING_PLANE);
spinner.setSize(SpinnerSize.SM);
addComponent(spinner);
  
Spinner spinner2 = new Spinner(SpinnerType.CIRCLE).large();
addComponent(spinner2);

Spinner spinner3 = new Spinner(SpinnerType.FOLDING_CUBE)
    .small().withStyle("green");
addComponent(spinner3);   
```   

## API

SpinKit for Vaadin JavaDoc is available online at https://vaadindemo-mbf.rhcloud.com/docs/spinkit/api/

## Building and running demo

To start the demo:

```
git clone https://github.com/mcollovati/vaadin-spinkit.git
mvn clean install
cd spinkit-demo
mvn jetty:run
```

To see the demo, navigate to http://localhost:8080/

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated.


## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.

