import java.util.*;

public class User {
    private String userName;
    private boolean online;
    private ArrayList<Song> songList;

    public User() { this(""); }

    public User(String u) {
        userName = u;
        online = false;
        songList = new ArrayList<>();
    }

    public String getUserName() { return userName; }

    public boolean isOnline() { return online; }

    public String toString() {
        String s = "" + userName + ": " + songList.size() + " songs (";
        if (!online) s += "not ";
        return s + "online)";
    }

    public ArrayList<Song> getsongList() {return songList;}

    public void addSong(Song s){
        //adds a given song to the song list
        songList.add(s);
        //set the owner properly
        // song object may only be owned by one user and many user can have copies
        s.setOwner(this);
    }

    public int totalSongTime(){
        //total duration of the song in seconds, of all of the songs
        int totalTime = 0;
        for(Song s: songList){
            totalTime += s.getSeconds();
        }
        return totalTime;
    }

    public void register(MusicExchangeCenter m){ m.registerUser(this); }

    public void logon(MusicExchangeCenter m){
        //simulates a user going online with given musicenter
        //use userwithname()
        //only allow if registered
            if (m.userWithName(getUserName())!= null) {
                this.online = true;
            }
        }

    public void logoff(MusicExchangeCenter m){
        if (m.userWithName(getUserName())!=null) {
            this.online = false;
        }
    }

    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {
        //gather the list of all available songs from online users
        //use get method
        ArrayList<String> output = new ArrayList<>();
        int counter = 1;
        String temp = "";
        temp += String.format("%10s","Title");
        temp += String.format("%32s", "Artist");
        temp += String.format("%17s", "Time");
        temp += String.format("%18s", "Owner\n");
        output.add(temp);
        for (Song s: m.allAvailableSongs()){
            temp = String.format("%2d",counter) + ". " + String.format("%-30s", s.getTitle());
            temp += String.format("%-20s",s.getArtist());
            temp += String.format("%2d:%2d",s.getMinutes(),s.getSeconds());
            temp += String.format("          %-20s",s.getOwner().userName);
            output.add(temp);
            counter++;

        }
        return output;
    }

    public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
        //all available songs by the given artist from all users that
        //are online at the given music exchange center
        ArrayList<String> outputByArtist = new ArrayList<>();
        int counter = 1;
        String temp = "";
        temp += String.format("%10s", "Title");
        temp += String.format("%30s", "Artist");
        temp += String.format("%19s", "Time");
        temp += String.format("%18s", "Owner\n");
        outputByArtist.add(temp);
        for (Song s : m.availableSongsByArtist(artist)) {
            temp = String.format("%2d", counter) + ". " + String.format("%-30s", s.getTitle());
            temp += String.format("%-20s", s.getArtist());
            temp += String.format("%2d:%2d", s.getMinutes(), s.getSeconds());
            temp += String.format("%20s", s.getOwner().userName);
            outputByArtist.add(temp);
            counter++;
        }
        return outputByArtist;
    }

    public Song songWithTitle(String title) {
        for (Song s : getsongList()) {
            if (s.getTitle().equals(title)) {
                return s;
            }
        }
        return null;
    }

    public void downloadSong(MusicExchangeCenter m, String title, String ownerName){
        Song DownloadedSong = m.getSong(title, ownerName);
        if (DownloadedSong != null) {
            Song s= new Song(DownloadedSong.getTitle(),DownloadedSong.getArtist(),DownloadedSong.getMinutes(),DownloadedSong.getSeconds());
            this.addSong(s);
        }
    }

    // Various Users for test purposes
    public static User DiscoStew() {
        User discoStew = new User("Disco Stew");
        discoStew.addSong(new Song("Hey Jude", "The Beatles", 4, 35));
        discoStew.addSong(new Song("Barbie Girl", "Aqua", 3, 54));
        discoStew.addSong(new Song("Only You Can Rock Me", "UFO", 4, 59));
        discoStew.addSong(new Song("Paper Soup Cats", "Jaw", 4, 18));
        return discoStew;
    }
    public static User SleepingSam() {
        User sleepingSam = new User("Sleeping Sam");
        sleepingSam.addSong(new Song("Meadows", "Sleepfest", 7, 15));
        sleepingSam.addSong(new Song("Calm is Good", "Waterfall", 6, 22));
        return sleepingSam;
    }
    public static User RonnieRocker() {
        User ronnieRocker = new User("Ronnie Rocker");
        ronnieRocker.addSong(new Song("Rock is Cool", "Yeah", 4, 17));
        ronnieRocker.addSong(new Song("My Girl is Mean to Me", "Can't Stand Up", 3, 29));
        ronnieRocker.addSong(new Song("Only You Can Rock Me", "UFO", 4, 52));
        ronnieRocker.addSong(new Song("We're Not Gonna Take It", "Twisted Sister", 3, 9));
        return ronnieRocker;
    }
    public static User CountryCandy() {
        User countryCandy = new User("Country Candy");
        countryCandy.addSong(new Song("If I Had a Hammer", "Long Road", 4, 15));
        countryCandy.addSong(new Song("My Man is a 4x4 Driver", "Ms. Lonely", 3, 7));
        countryCandy.addSong(new Song("This Song is for Johnny", "Lone Wolf", 4, 22));
        return countryCandy;
    }
    public static User PeterPunk() {
        User peterPunk = new User("Peter Punk");
        peterPunk.addSong(new Song("Bite My Arms Off", "Jaw", 4, 12));
        peterPunk.addSong(new Song("Where's My Sweater", "The Knitters", 3, 41));
        peterPunk.addSong(new Song("Is that My Toenail ?", "Clip", 4, 47));
        peterPunk.addSong(new Song("Anvil Headache", "Clip", 4, 34));
        peterPunk.addSong(new Song("My Hair is on Fire", "Jaw", 3, 55));
        return peterPunk;
    }
}

