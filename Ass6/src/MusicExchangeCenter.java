import javafx.util.Pair;
import java.util.*;

public class MusicExchangeCenter {
    //ArrayList of user logged in or out
    private ArrayList<User> users;
    private HashMap<String, Float> royalties;
    private ArrayList<Song> downloadedSongs;
    private TreeSet<Song> unique;

    public MusicExchangeCenter() {
        //sets the attributes properly
        users = new ArrayList<>();
        royalties = new HashMap<>();
        downloadedSongs = new ArrayList<>();
        unique = new TreeSet<>(downloadedSongs);
    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }

    public ArrayList<User> onlineUsers() {
        //users that are currently online
        //creates a new array list each time its called
        ArrayList<User> onlineUsers = new ArrayList<>();
        for (User u : users) {
            if (u.isOnline()) {
                onlineUsers.add(u);
            }
        }
        return onlineUsers;
    }

    public ArrayList<Song> allAvailableSongs() {
        //a arrayList of all songs currently available to download(Songs from logged on users)
        //Creates a new arraylist
        ArrayList<Song> availableSongs = new ArrayList<>();
        for (User u : onlineUsers()) {
            availableSongs.addAll(u.getsongList());
        }
        return availableSongs;
    }

    public String toString() {
        String output = "";
        output += "Music Exchange Center (" + onlineUsers().size() + " users on line, "
                + allAvailableSongs().size() + " songs available)";
        return output;
    }

    public User userWithName(String s) {
        //finds and returns the user object with given name if in the list
        for (User u : users) {
            if (s.equals(u.getUserName())) {
                return u;
            }
        }
        return null;
    }

    public void registerUser(User x) {
        //add a given user to the list of users
        //given that there are no one else with the same name

        if (userWithName(x.getUserName()) == null) {
            users.add(x);
        }
    }

    public ArrayList<Song> availableSongsByArtist(String artist) {
        //return arraylist of all songs available for download by artist
        ArrayList<Song> SongsByArtist = new ArrayList<>();
        for (Song s : allAvailableSongs()) {
            if (s.getArtist().equals(artist)) {
                SongsByArtist.add(s);
            }
        }
        return SongsByArtist;
    }

    public Song getSong(String title, String ownerName) {
        // user is currently online and song exists
        //search centers users and find user with ownername
        Song s = null;
        for (User u : this.users) {
            if (u.getUserName().equals(ownerName) && u.isOnline()) {
                s = u.songWithTitle(title);
                break;
            }
        }

        if (s != null)
        {
            downloadedSongs.add(s);
            Float amt = royalties.get(s.getArtist());
            if( amt != null)
            {
                royalties.put(s.getArtist(), royalties.get(s.getArtist()) + 0.25f);
            }
            else{
                royalties.put(s.getArtist(), 0.25f);
            }
        }

        return s;
    }

    public void displayRoyalties() {
        // print the header lines
        System.out.print(String.format("%-10s  %-10s\n","Amount", "Artist"));
        System.out.println("---------------------");
        // iterate through has map and print the amount and artist
        for (String artist : royalties.keySet()) {
            String str = String.format("$%1.2f      %-10s\n", royalties.get(artist), artist);
            System.out.print(str);
        }

    }

    public TreeSet<Song> uniqueDownloads(){
        unique.addAll(downloadedSongs);
        return unique;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity(){
        // Integer is number of songs downloaded
        // the list should return sorted in DECREASING order
        ArrayList<Pair<Integer, Song>> popular = new ArrayList<Pair<Integer, Song>>();
        for (Song s: uniqueDownloads()) {
            Integer numOfDownloads = Collections.frequency(downloadedSongs,s);
            popular.add(new Pair<>(numOfDownloads, s));
        }
        Collections.sort(popular, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                return p2.getKey().compareTo(p1.getKey());
            }
        });
        return popular;
    }
}
