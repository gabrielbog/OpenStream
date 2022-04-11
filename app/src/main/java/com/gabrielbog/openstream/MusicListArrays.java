package com.gabrielbog.openstream;

import com.gabrielbog.openstream.models.MusicModel;

import java.util.ArrayList;

public class MusicListArrays {
    private ArrayList<MusicModel> normalMusicList;
    private ArrayList<MusicModel> favoriteMusicList;
    private int isPlaying;

    private static MusicListArrays inst = null;

    private MusicListArrays() {
        normalMusicList = new ArrayList<>();
        favoriteMusicList = new ArrayList<>();
        isPlaying = 0;
    }

    public static MusicListArrays getInstance() {
        if(inst == null) {
            inst = new MusicListArrays();
        }
        return inst;
    }

    public ArrayList<MusicModel> getNormalMusicList() {
        return normalMusicList;
    }

    public void setNormalMusicList(ArrayList<MusicModel> normalMusicList) {
        this.normalMusicList = normalMusicList;
    }

    public ArrayList<MusicModel> getFavoriteMusicList() {
        return favoriteMusicList;
    }

    public void setFavoriteMusicList(ArrayList<MusicModel> favoriteMusicList) {
        this.favoriteMusicList = favoriteMusicList;
    }

    //
    //insert/remove elements from arrays
    //

    public Boolean removeMusicElement(String title, String author, String link) {

        //search element in the original list
        for(MusicModel element : normalMusicList) {
            if(element.getTitle().equals(title) && element.getAuthor().equals(author) && element.getLink().equals(link)) {

                //remove said element
                normalMusicList.remove(element);

                //remove element from favorites too
                if(element.getFavorite() == true) {

                    //find it
                    for(MusicModel favElement : favoriteMusicList) {
                        if(favElement.getTitle().equals(title) && favElement.getAuthor().equals(author) && favElement.getLink().equals(link)) {

                            //remove that too
                            favoriteMusicList.remove(favElement);
                            break;
                        }
                    }
                }

                //get out of the function
                return true;
            }
        }

        //exit if it doesn't exist
        return false;
    }

    public Boolean removeMusicElement(MusicModel foundElem) {

        //search element in the original list
        normalMusicList.remove(foundElem);

        if(foundElem.getFavorite() == true) {
            //remove it from the fav array too
            favoriteMusicList.remove(foundElem);
        }

        //exit
        return true;
    }

    public Boolean removeFavoriteMusicElement(String title, String author, String link) {

        //remove favorite element
        for(MusicModel favElement : favoriteMusicList) {
            if(favElement.getTitle().equals(title) && favElement.getAuthor().equals(author) && favElement.getLink().equals(link)) {

                //remove said element
                favoriteMusicList.remove(favElement);

                //set element as not favorite
                if(favElement.getFavorite() == true) {

                    //find it
                    for(MusicModel element : normalMusicList) {
                        if(element.getTitle().equals(title) && element.getAuthor().equals(author) && element.getLink().equals(link)) {

                            //set it
                            element.setFavorite(false);
                            break;
                        }
                    }
                }

                //get out of the function
                return true;

            }
        }

        //exit if it doesn't exist
        return false;
    }

    public Boolean removeFavoriteMusicElement(MusicModel foundElem) {

        //search element in the original list
        favoriteMusicList.remove(foundElem);

        if(foundElem.getFavorite() == true) {
            for(MusicModel element : normalMusicList) {

                //find it
                if(element.equals(foundElem)) {

                    //set it
                    element.setFavorite(false);
                    break;
                }
            }
        }

        //exit
        return true;
    }

    public void insertMusicElement(String title, String author, String link)
    {
        normalMusicList.add(new MusicModel(title, author, link));
    }

    public void insertMusicElement(MusicModel foundElem)
    {
        normalMusicList.add(foundElem);
    }

    public Boolean insertFavoriteMusicElement(String title, String author, String link)
    {
        //set the element as favorite
        for(MusicModel element : normalMusicList) {
            if(element.getTitle().equals(title) && element.getAuthor().equals(author) && element.getLink().equals(link)) {
                //add it if it actually exists
                element.setFavorite(true);
                favoriteMusicList.add(element);
                return true;
            }
        }

        //exit if false
        return false;
    }

    public Boolean insertFavoriteMusicElement(MusicModel foundElem)
    {
        //set the element as favorite
        for(MusicModel element : normalMusicList) {
            if(element.equals(foundElem)) {
                //add it if it actually exists
                element.setFavorite(true);
                favoriteMusicList.add(element);
                return true;
            }
        }

        //exit if false
        return false;
    }

    public int getNormalArraySize()
    {
        return normalMusicList.size();
    }

    public int getFavoriteArraySize()
    {
        return favoriteMusicList.size();
    }

    public MusicModel getNormalArrayElement(int index)
    {
        return normalMusicList.get(index);
    }

    public MusicModel getFavoriteArrayElement(int index)
    {
        return favoriteMusicList.get(index);
    }

    public ArrayList<MusicModel> getNormalArray()
    {
        return normalMusicList;
    }

    public ArrayList<MusicModel> getFavoriteArray()
    {
        return favoriteMusicList;
    }

    public void setNormalArray(ArrayList<MusicModel> normalMusicList)
    {
        this.normalMusicList = normalMusicList;
    }

    public void setFavoriteArray(ArrayList<MusicModel> favoriteMusicList)
    {
        this.favoriteMusicList = favoriteMusicList;
    }

    public int getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(int isPlaying) {
        this.isPlaying = isPlaying;
    }
}
