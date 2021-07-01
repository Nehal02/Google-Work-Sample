package com.google;

import com.google.inject.internal.util.ImmutableCollection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  List <Video> vid = new VideoLibrary().getVideos() .stream()
          .sorted(Comparator.comparing(Video :: getTitle))
          .collect(Collectors.toList());



   Video currVid = null;

   Boolean PauVid = false;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.printf("Here's a list of all available videos:%n");
    for(Video video : vid){
      System.out.printf(video.getTitle() + " (" + video.getVideoId() + ") [" + video.getTags().stream().collect(Collectors.joining(" ")) + "]%n");
    }
  }

  public void playVideo(String videoId) {
    PauVid = false;
    if(currVid == null && vid.stream().anyMatch(video -> video.getVideoId().equals(videoId))){
      currVid = vid.stream().filter(video -> video.getVideoId().equals(videoId)).findAny().orElse(null);
      System.out.println("Playing video: " + currVid.getTitle());
    } else if(currVid != null && !vid.stream().noneMatch(video -> video.getVideoId().equals(videoId))){
      System.out.println("Stopping video: " + currVid.getTitle());
      currVid = vid.stream().filter(video -> video.getVideoId().equals(videoId)).findAny().orElse(null);
      System.out.println("Playing video: " + currVid.getTitle());
    } else{
      System.out.println("Cannot play video: Video does not exist");
    }
  }

  public void stopVideo() {
    if(currVid != null){
      System.out.println("Stopping video: " + currVid.getTitle());
      currVid = null;
    } else{
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo() {
    Random rdm = new Random();
    int randomVideoIndex = rdm.nextInt(vid.size());
    PauVid = false;
    if(currVid != null){
      stopVideo();
      playVideo(vid.get(randomVideoIndex).getVideoId());
    } else if(currVid == null){
      playVideo(vid.get(randomVideoIndex).getVideoId());
    }
  }

  public void pauseVideo() {
    if(currVid != null){
      if(!PauVid){
        PauVid = true;
        System.out.println("Pausing video: " + currVid.getTitle());
      } else if (PauVid){
        System.out.println("Video already paused: " + currVid.getTitle());
      }
    }else{
      System.out.println("Cannot pause video: No video is currently playing");
    }
  }

  public void continueVideo() {
    if(currVid != null){
      if(PauVid){
        System.out.println("Continuing video: " + currVid.getTitle());
        PauVid = false;
      } else if(!PauVid){
        System.out.println("Cannot continue video: Video is not paused");
      }

    } else{
      System.out.println("Cannot continue video: No video is currently playing");
    }
  }

  public void showPlaying() {
    if(currVid != null){
      String pauseStatus;
      if(PauVid){
        pauseStatus = " - PAUSED";
      } else{
        pauseStatus = "";
      }
      System.out.println("Currently playing: " + currVid.getTitle() + " (" + currVid.getVideoId() + ") [" + currVid.getTags().stream().collect(Collectors.joining(" ")) + "]"+ pauseStatus);
    } else{
      System.out.print("No video is currently playing");
    }
  }
  List<String> alPlists = new ArrayList<>();
  public void createPlaylist(String playlistName) {


    if(!alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase) && !playlistName.isEmpty()){
      alPlists.add(playlistName);
      System.out.println("Successfully created new playlist: " + playlistName);
    } else {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if(alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase) && vid.stream().anyMatch(video -> video.getVideoId().equals(videoId))){
      System.out.println("Added video to my_playlist: "+ playlistName  );}
    else if(!alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase))
    { System.out.println("Cannot add video to another_playlist: Playlist does not exist");
    }
    else if(!alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase))
    { System.out.println("Cannot add video to "+ playlistName+":"+" Playlist does not exist");
    }
      else if(!alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase))
    { System.out.println("Cannot add video to my_playlist: Playlist does not exist");
    }
  }

  public void showAllPlaylists() {

    if ((alPlists == null)){
    System.out.println("Showing all playlists:"); alPlists.forEach(System.out::println);
  }

    else {System.out.println("No playlists exist yet");}


  }

  public void showPlaylist(String playlistName) {
    if(alPlists.stream().anyMatch(playlistName :: equalsIgnoreCase))
    {System.out.println("Showing playlist:"+ playlistName);}
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    if (!(alPlists == null)){
      alPlists.clear();
      System.out.println("Successfully removed all videos from " + playlistName);
    }
    else if(alPlists.stream() == null && alPlists == null)

    { System.out.println("Cannot clear playlist: Playlist does not exist");
    }
     else {
      System.out.println("Cannot clear playlist: Playlist does not exist");}
  }

  public void deletePlaylist(String playlistName) {
    if (!(alPlists == null)){
      alPlists.clear();
      System.out.println("Deleted playlist: " + playlistName);
    }else {
      System.out.println("Cannot delete playlist my_playlist: Playlist does not exist");}
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}