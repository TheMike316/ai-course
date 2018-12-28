package bfs.webcrawler

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors

class WebCrawler(private val firstUrl: String = "", private val maxUrlCount: Int = 0, urlPattern: String = "") {

    private val pattern: Pattern = Pattern.compile(urlPattern)

    fun bfs(): Set<String> {
        if (!pattern.matcher(firstUrl).matches())
            return emptySet()

        val visitedUrls = mutableSetOf<String>()
        val queue: Queue<String> = LinkedList()
        queue.add(firstUrl)

        while (visitedUrls.size < maxUrlCount && !queue.isEmpty()) {

            queue.remove()
                .takeIf { pattern.matcher(it).matches() }
                ?.let { currentUrl ->
                    readLinesOrNull(currentUrl)
                        ?.let { lines ->

                            println("Crawled url $currentUrl")
                            visitedUrls.add(currentUrl)

                            if (visitedUrls.size < maxUrlCount)
                                addContainedUrlsToQueue(lines, queue) { url -> !visitedUrls.contains(url) }
                        }
                }
        }

        println("Crawling completed. ${visitedUrls.size} urls crawled")
        return visitedUrls
    }

    private fun addContainedUrlsToQueue(lines: String, queue: Queue<String>, filter: (String) -> Boolean) {
        val matcher = pattern.matcher(lines)

        while (matcher.find()) {
            matcher.group()
                .takeIf(filter::invoke)
                ?.let {
                    if (!queue.contains(it))
                        queue.add(it)
                }
        }
    }

    private fun readLinesOrNull(url: String): String? {
        try {
            return BufferedReader(InputStreamReader(URL(url).openStream())).use {
                it.lines().collect(Collectors.joining())
            }
        } catch (e: Exception) {
            println("Error reading url $url. Reason: ${e.message}")

        }
        return null
    }

}
