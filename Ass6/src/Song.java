public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private int duration;
    private User owner;

    public Song() {
        this("", "", 0, 0);
    }

    public Song(String t, String a, int m, int s) {
        title = t;
        artist = a;
        duration = m * 60 + s;
        owner = null;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public int getMinutes() {
        return duration / 60;
    }

    public int getSeconds() {
        return duration % 60;
    }

    public String toString() {
        return ("\"" + title + "\" by " + artist + " " +
                (duration / 60) + ":" + (duration % 60));
    }

    public void setOwner(User x){
        this.owner = x;
    }

    public User getOwner(){return owner;}

    public boolean equals(Object obj) {
        if (!(obj instanceof Song)) return false;
        return title.equals(((Song)obj).title);
    }

    public int hashCode() {
        return title.hashCode();
    }

    public int compareTo(Song s) {
        return title.compareTo(s.title);
    }
}
