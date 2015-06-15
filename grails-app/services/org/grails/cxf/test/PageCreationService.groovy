package org.grails.cxf.test

import grails.transaction.Transactional

@Transactional
class PageCreationService {

    def createChapter(Map params, Boolean addPages = false, Boolean addWords = false) {
        Chapter chapter = Chapter.findOrCreateWhere(params)
        Integer pageNumber = 0
        if (addPages && !chapter?.pages) {
            def page = Page.findOrCreateWhere(name: "Page 1, no version", number: pageNumber++)
            if (addWords) {
                page.words = createWords(null)
            }
            chapter.addToPages(page)
        }
        chapter
    }

    def createPage(Map params, Boolean addWords = false) {
        Page page = Page.findOrCreateWhere(params)
        if (addWords && !page?.words) {
            createWords(page)
        }
        return page
    }

    def List<Word> createWords(Page page = null) {
        def words = []
        words << new Word(text: "i")
        words << new Word(text: "am")
        words << new Word(text: "the")
        words << new Word(text: "doctor")
        if (page && !page.words)
            words.each { page.addToWords(it) }
        words
    }
}
