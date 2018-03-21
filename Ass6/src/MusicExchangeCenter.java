import java.util.*;

public class MusicExchangeCenter {

    //ArrayList of user logged in or out
    private ArrayList<User> users;

    public MusicExchangeCenter() {
        //sets the attributes properly
        this.users = new ArrayList<>();
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
            }
        }
        return s;
    }

    }
