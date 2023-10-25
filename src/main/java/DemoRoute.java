// camel-k: language=java
// camel-k: env=TZ=Asia/Shanghai
// camel-k: property=file:../resources/application.properties
// camel-k: dependency=camel-servlet
// camel-k: dependency=camel-http
// camel-k: dependency=mvn:com.sun.xml.ws:jaxws-rt:2.3.3
// camel-k: dependency=mvn:com.sun.xml.ws:rt:2.3.3
// camel-k: dependency=camel-soap
// camel-k: dependency=camel-direct
// camel-k: build-property=quarkus.camel.servlet.url-patterns=/*
// camel-k: resource=file:../../../target/camel-soap-demo.jar
// camel-k: trait=container.name=camel-soap-demo
// camel-k: trait=jvm.classpath=/etc/camel/resources/camel-soap-demo.jar


import net.webservicex.GeoIPServiceSoap;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapDataFormat;
import org.apache.camel.dataformat.soap.name.ServiceInterfaceStrategy;

public class DemoRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        // Soap Convert
        SoapDataFormat soapDataFormat = new SoapDataFormat();
        soapDataFormat.setContextPath("net.webservicex");

        soapDataFormat.setElementNameStrategy(new ServiceInterfaceStrategy(GeoIPServiceSoap.class , true));
        // Source: Knative Event
        from("servlet:hello/soap")
                .unmarshal(soapDataFormat)
                .to("direct:outbound_http");



    }
}
