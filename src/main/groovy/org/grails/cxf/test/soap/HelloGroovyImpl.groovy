package org.grails.cxf.test.soap

import org.springframework.stereotype.Component

@Component
class HelloGroovyImpl implements HelloGroovy {
    @Override
    String sayHi() {
        return "hello groovy"
    }
}
