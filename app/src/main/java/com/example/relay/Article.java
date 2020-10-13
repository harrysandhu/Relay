package com.example.relay;

import org.json.JSONException;
import org.json.JSONObject;

public class Article {
    private String source;
    private String author;
    private String title;
    private String description;
    private String publishedAt;
    private String url;
    private String urlToImage;
    private String content;

    public Article(String source, String author, String title, String description, String publishedAt, String url,
                   String urlToImage, String content){
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
    }

    public Article(JSONObject article) {
        try {
            this.source = article.getJSONObject("source").get("name").toString();
            this.author = article.get("author").toString();
            this.title =  article.get("title").toString();
            this.description =  article.get("description").toString();
            this.publishedAt =  article.get("publishedAt").toString();
            this.url =  article.get("url").toString();
            this.urlToImage =  article.get("urlToImage").toString();
            this.content =  article.get("content").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }



    @Override
    public String toString() {
        return "Article{" +
                "source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
