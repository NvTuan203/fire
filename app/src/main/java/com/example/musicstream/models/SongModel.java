package com.example.musicstream.models;

public class SongModel {
    private String id;
    private String title;
    private String subtitle;
    private String url;
    private String coverUrl;

    public SongModel(String id, String title, String subtitle, String url, String coverUrl){
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.url = url;
        this.coverUrl = coverUrl;
    }
    public SongModel(){this("", "", "", "", "");}

    public String getId() {return id;}
    public String getTitle() {return title;}
    public String getSubtitle() {return subtitle;}
    public String getUrl() {return  url;}
    public String getCoverUrl() {return coverUrl;}
}
