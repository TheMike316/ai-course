package bfs.webcrawler


fun main(args: Array<String>) {
    WebCrawler("https://www.google.com", 50, "http[s]*://(\\w+\\.)*(\\w+)").bfs()
}
