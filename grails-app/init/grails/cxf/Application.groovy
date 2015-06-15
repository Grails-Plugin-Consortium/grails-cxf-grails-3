package grails.cxf

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.grails.cxf.test.soap.interceptor.CustomLoggingInInterceptor
import org.grails.cxf.test.soap.interceptor.InjectedBean
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages=['org.grails'])
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application)
    }

    @Override
    Closure doWithSpring() {
        { ->
            injectedBean(InjectedBean)

            customLoggingInInterceptor(CustomLoggingInInterceptor)
        }
    }
}