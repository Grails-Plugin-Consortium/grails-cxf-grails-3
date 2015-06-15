package org.grails.cxf.test

import grails.core.GrailsApplication
import org.grails.cxf.utils.GrailsCxfEndpoint
import org.springframework.beans.factory.annotation.Autowired

import javax.jws.WebMethod
import javax.jws.WebResult

@GrailsCxfEndpoint
class PageService {

    @Autowired
    PageCreationService pageCreationService

    @WebResult(name = 'hello')
    @WebMethod(operationName = 'sayHello')
    String sayHello() {
        'hello'
    }

    @WebResult(name = 'chapters')
    @WebMethod(operationName = 'getSomeChapters')
    List<Chapter> getSomeChapters() {
        List<Chapter> chapters = []
        chapters << pageCreationService.createChapter([name: "Chapter1"], false, false)
        chapters << pageCreationService.createChapter([name: "Chapter2"], true, false)
        chapters << pageCreationService.createChapter([name: "Chapter3"], true, true)
        chapters
    }

    @WebResult(name = 'words')
    @WebMethod(operationName = 'getSomeWords')
    List<Word> getSomeWords() {
        pageCreationService.createWords()
    }

    @WebResult(name = 'page')
    @WebMethod(operationName = 'getMeSomePersistedPagesWithWords')
    List<Page> getMeSomePersistedPagesWithWords() {
        List<Page> pages = []
        pages << pageCreationService.createPage([name: "saved page 1", number: 1], true).save(flush: true)
        pages << pageCreationService.createPage([name: "saved page 2", number: 2], true).save(flush: true)
        pages << pageCreationService.createPage([name: "saved page 3", number: 3], true).save(flush: true)
        pages
    }

    @WebResult(name = 'page')
    @WebMethod(operationName = 'getMeSomePagesWithWords')
    List<Page> getMeSomePagesWithWords() {
        List<Page> pages = []
        pages << pageCreationService.createPage([name: "test1", number: 2], true)
        pages << pageCreationService.createPage([name: "hihi", number: 8], true)
        pages << pageCreationService.createPage([name: "hoho", number: 32], true)
        pages
    }

    @WebResult(name = 'page')
    @WebMethod(operationName = 'getMeSomePages')
    List<Page> getMeSomePages() {
        List<Page> pages = []
        pages << pageCreationService.createPage([name: "Page 1, version should increment", number: 2], false)
        pages << pageCreationService.createPage([name: "Page 2, version should increment", number: 8], false)
        pages << pageCreationService.createPage([name: "Page 3, version should increment", number: 32], true)
        pages
    }
}
