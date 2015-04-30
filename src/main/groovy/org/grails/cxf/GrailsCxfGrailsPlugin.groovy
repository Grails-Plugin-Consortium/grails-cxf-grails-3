package org.grails.cxf

import grails.plugins.*
import grails.util.GrailsNameUtils
import org.apache.cxf.bus.spring.SpringBus
import org.apache.cxf.frontend.ServerFactoryBean
import org.grails.cxf.artefact.EndpointBeanConfiguration
import org.grails.cxf.artefact.EndpointExposureType
import org.grails.cxf.artefact.GrailsEndpointClass
import org.grails.cxf.frontend.DelegatingServerFactoryBean
import org.grails.cxf.frontend.GrailsJaxWsServerFactoryBean
import org.grails.cxf.frontend.GrailsSimpleServerFactoryBean
import org.grails.cxf.servlet.GrailsCxfServlet
import org.grails.cxf.test.BookStoreEndpoint
import org.grails.cxf.test.BookStoreService
import org.grails.cxf.test.CarService
import org.grails.cxf.test.CoffeeMakerEndpoint
import org.grails.cxf.test.HelloWorld
import org.grails.cxf.test.HelloWorldImpl
import org.grails.cxf.test.ICarService
import org.grails.cxf.test.PlaneService
import org.grails.cxf.test.soap.HelloGroovy
import org.grails.cxf.test.soap.HelloGroovyImpl
import org.grails.cxf.test.soap.interceptor.CustomLoggingInInterceptor
import org.grails.cxf.utils.GrailsCxfUtils
import org.springframework.boot.context.embedded.ServletRegistrationBean

import javax.xml.ws.soap.SOAPBinding

class GrailsCxfGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.0.1 > *"
    def author = 'Grails Plugin Consortium'
    def authorEmail = ''
    def title = 'CXF plug-in for Grails'
    def description = 'Brings easy exposure of service and endpoint classes as Apache CXF SOAP Services to Grails.'

    def developers = [[name: "Christian Oestreich", email: "acetrike@gmail.com"]]

    def documentation = "https://github.com/Grails-Plugin-Consortium/grails-cxf"
    def license = 'APACHE'

    def issueManagement = [system: 'JIRA', url: 'https://github.com/Grails-Plugin-Consortium/grails-cxf/issues']
    def scm = [url: "https://github.com/Grails-Plugin-Consortium/grails-cxf"]
    def profiles = ['web']

    def pluginExcludes = [
            'grails-app/conf/spring/ApplicationResources.groovy',
            'grails-app/conf/spring/resources.groovy',
            'grails-app/conf/spring/resources.groovy',
            'grails-app/conf/ApplicationResources.groovy',
            'grails-app/conf/BootStrap.groovy',
            'grails-app/conf/DataSource.groovy',
            'grails-app/conf/UrlMappings.groovy',
            'grails-app/conf/codenarc.groovy',
            'grails-app/conf/codenarc.ruleset.all.groovy.txt',
            'grails-app/domain/**',
            'grails-app/endpoints/**',
            'grails-app/i18n/**',
            'grails-app/services/**',
            'grails-app/controllers/**',
            'grails-app/views/**',
            'src/groovy/org/grails/cxf/test/**',
            'src/java/org/grails/cxf/test/**',
            'web-app/**',
            'codenarc.properties'
    ]

    Closure doWithSpring() {
        {  ->
            cxf(SpringBus)

            cxfServlet(ServletRegistrationBean, new GrailsCxfServlet(), "/services/*") {
                loadOnStartup = 10
            }

            "carServiceFactory"(GrailsSimpleServerFactoryBean) {
                address = '/car'
                serviceClass = ICarService
                serviceBean = ref('carService')
                ignoredMethods = GrailsEndpointClass.DEFAULT_GROOVY_EXCLUDES
                bus = ref(cxf)
            }

            "carServiceBean"(('carServiceFactory'): 'create')



//            EndpointBeanConfiguration bc = new EndpointBeanConfiguration(grailsApplication)
//
//            with bc.cxfBeans()
//            with bc.endpointBeans()
//            with bc.factoryBeans()



        }
    }




    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
