package Scrumbags.logic;

import java.util.Objects;

public class Podcast {
    String name;
    String publisher;
    String url;
    String rrs;
    
    public Podcast(String name, String publisher, String url, String rrs){
        this.name = name;
        this.publisher = publisher;
        this. url = url;
        this.rrs = rrs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRrs() {
        return rrs;
    }

    public void setRrs(String rrs) {
        this.rrs = rrs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Podcast other = (Podcast) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.rrs, other.rrs)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String text = "";
        return "\nNimi: " + this.name 
                + "\nJulkaisija: " + this.publisher
                + "\n url: " + this.url
                + "\n rrs: " + this.rrs;
    }
    
    
}
