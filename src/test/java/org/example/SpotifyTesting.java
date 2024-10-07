package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SpotifyTesting
{

    String token="BQACgvittL8q_l26OH7jjBVaJb4SJDoEO8CX0mLf097C4WrEX458SZtw_Zz0X50kA9XD4pdCd7XOlW0vbV34uB6_OI46H2NUINAAb10AjEsTwQe-NDsCcZ8Wp2VADc29DNintKBymj_iZc2yrdjTflXmCbRuloAet9Rj1uNA7W2gfWj3eIYS8aegoyipcTuwV_zJ79FOrglbB5lcsDGGS1Jq7_jyv-sWuDhwhK8SCSgkE7s9V_F_D7xrtMr8Ig_SGsxd64eWBL8mVNcJueg8YR-idiX8DXxlGcL54v0n95rPLlHG5ehNofKf20tb08XIcczH3ik93a7bMQjfcFJeLT4";
    String userid;
    String userEmail;
    String username;


    @Test(priority = 1)
    public void getCurrentUserProfile(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me");

        response.prettyPrint();
        userid=response.path("id");
        userEmail=response.path("email");
        username=response.path("display_name");
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);

        System.out.println(userid);
        System.out.println(userEmail);
        System.out.println(username);
    }

    @Test(priority = 2)
    public void getUserTopItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/top/artists ");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void getUserProfile(){
        String un="kseetaram";
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/users/"+un);
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 4)
    public void followPlaylist(){
        String playlistid="3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistid+"/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 5)
    public void unfollowPlaylist(){
        String playlistid="3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/"+playlistid+"/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 6)
    public void getFollowedArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/following?type=artist&after=6VuMaDnrHyPL1p4EHjYLi7");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 7)
    public void followArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"6VuMaDnrHyPL1p4EHjYLi7\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist&ids=6VuMaDnrHyPL1p4EHjYLi7");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(),204);
    }

    @Test(priority = 8)
    public void unfollowArtistOrUsers(){
        String userplaylistid = "3MsR9p5xhhfmQYNp2XWJ2Z";
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"0oOet2f43PA68X5RxKobEy\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/"+userplaylistid+"/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 9)
    public void checkIfUserFollowsArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 10)
    public void checkIfCurrentUserFollowsPlaylist(){
        String userplaylistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+userplaylistid+"/followers/contains");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 11)
    public void getTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ token)
                .when()
                .get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }


    @Test(priority = 12)
public void search1(){
    Response res1 = given()
            .header("accept","application/json")
            .header("Content-Type","application/json")
            .header("Authorization","Bearer "+token)
            .pathParam("q","Vijay")
            .pathParam("type","album")
            .when()
            .get("https://api.spotify.com/v1/search?q={q}&type={type}");

    res1.prettyPrint();
    res1.then().assertThat().statusCode(200);
}

    @Test(priority = 13)
    public void search2(){
        Response res2 = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+token)
                .queryParam("q","Vijay")
                .queryParam("type","album")
                .when()
                .get("https://api.spotify.com/v1/search");
//                    https://api.spotify.com/v1/search?q=Vijay&type=album
        res2.prettyPrint();
        res2.then().assertThat().statusCode(200);
    }

    @Test(priority = 14)
    public void GetAvailableMarkets(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    @Test(priority = 15)
    public void GetAvailableGenreSeeds(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    @Test(priority = 16)
    public void GetaChapter(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");
        res.prettyPrint();
        // res.then().statusCode(200);
    }

    @Test(priority = 17)
    public void GetSeveralChapters(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/chapters?ids=0IsXVP0JmcB2adSE338GkK%2C3ZXb8FKZGU0EHALYX6uCzU%2C0D5wENdkdwbqlrHoaJ9g29");
        res.prettyPrint();
        // res.then().statusCode(200);
    }


    @Test(priority = 18)
    public void GetSeveralBrowseCategories(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories?locale=IN");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 19)
    public void GetSingleBrowseCategory(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 20)
    public void GetArtist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
        res.prettyPrint();
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        System.out.println("ArtistID   " + artistid);
        res.then().statusCode(200);
    }

    @Test(priority = 21)
    public void GetSeveralArtists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 22)
    public void GetArtistsTopTracks(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/"+ artistid +"/top-tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 23)
    public void GetArtistsAlbums(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/"+ artistid +"/albums");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 24)
    public void GetArtistsRelatedArtists(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/artists/" +artistid+ "/related-artists");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    @Test(priority = 25)
    public void GetAlbum(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
        res.prettyPrint();
        String albumid=res.path("id");
        System.out.println(albumid);
        res.then().statusCode(200);
    }

    @Test(priority = 26)
    public void GetSeveralAlbums(){
        String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/albums?ids="+ albumid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 27)
    public void GetAlbumTracks(){
        String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/albums/"+albumid+"/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 28)
    public void GetUsersSavedAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 29)
    //    1kDZSmw3mKQeAjcmPTLS3M-og
    public void CheckUsersSavedAlbums(){
        String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums/contains?ids="+albumid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 30)
    public void GetNewReleases(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 31)
    //   1kDZSmw3mKQeAjcmPTLS3M
    public void SaveAlbumsforCurrentUser(){
        String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/albums?ids="+albumid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 32)
    public void RemoveUsersSavedAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums?ids=382ObEPsp2rxGrnsizN5TX%2C1A2GTWGtFfWp7KSQTwWOyo%2C2noRn2Aes5aoNVsU6iWThc");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    @Test(priority = 33)
    public void GetanAudiobook(){
        String audiobookid = "7iHfbu1YPACw6oZPAFJtqe";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci");
        res.prettyPrint();
        audiobookid = res.path("id");
        System.out.println(audiobookid);
        res.then().statusCode(200);
    }

    @Test(priority = 34)
    public void GetSeveralAudiobooks(){
        String audiobookid = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 35)
    public void GetUsersSavedAudiobooks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 36)
    public void GetAudiobookChapters(){
        String audiobookid = "7iHfbu1YPACw6oZPAFJtqe";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 37)
    public void CheckUsersSavedAudiobooks(){
        String audiobookid = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 38)
    public void SaveAudiobooksforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .body("{\"ids\": [\"18yVqkdbdRvS24c0Ilj2ci\", \"1HGw3J3NxZO1TP1BTtVhpZ\", \"7iHfbu1YPACw6oZPAFJtqe\"]}")
                .put("https://api.spotify.com/v1/me/audiobooks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 39)
    public void RemoveUsersSavedAudiobooks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    @Test(priority = 40)
    public void GetSeveralShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .queryParam("ids","https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ")
                .when()
                .get("https://api.spotify.com/v1/shows");

        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Test(priority = 41)
    public void GetShow(){
        String showid = "38bS44xjbVVZ3No3ByF1dJ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe");
        res.prettyPrint();
        showid=res.path("id");
        System.out.println(showid);
        res.then().statusCode(200);
    }

    @Test(priority = 42)
    public void GetShowEpisodes(){
        String showid = "38bS44xjbVVZ3No3ByF1dJ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes");
        res.prettyPrint();
        res.then().statusCode(404);
    }

    @Test(priority = 43)
    public void GetUsersSavedShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/shows");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 44)
    public void SaveShowsforCurrentUser(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 45)
    public void RemoveUsersSavedShows(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 46)
    public void CheckUsersSavedShows(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 47)
    public void GetTrack(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks/5zCnGtCl5Ac5zlFHXaZmhy");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 48)
    public void GetSeveralTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 49)
    public void GetUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 50)
    public void CheckUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 51)
    public void SaveTracksforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 52)
    public void GetSeveralTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 53)
    public void GetTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 54)
    public void   GetRecommendations(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 55)
    public void   RemoveUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 56)
    public void GetPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n");
        res.prettyPrint();
        String play=res.path("id");
        System.out.println(play);
        res.then().statusCode(200);
    }

    @Test(priority = 57)
    public void ChangePlaylistDetails(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "   \n" +
                        "    \"description\": \"Updated playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 58)
    public void   GetPlaylistItems(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 59)
    public void   UpdatePlaylistItems(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"range_start\": 1,\n" +
                        "    \"insert_before\": 4,\n" +
                        "    \"range_length\": 2\n" +
                        "}\n")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 60)
    public void AddItemstoPlaylist(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:5urYiIXu1ZhfMAOsp7WDTc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority = 61)
    public void GetCurrentUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 62)
    public void   GetUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/users/31nakixlkshe2banry5tx3d2hnoe/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 63)
    public void   CreatePlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"name\": \"New Playlist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/MyPLayList/playlists");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority = 64)
    public void   GetFeaturedPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 65)
    public void   GetCategorysPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 66)
    public void   GetPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/images");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 67)
    public void  AddCustomPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("/9j/2wCEABoZGSccJz4lJT5CLy8vQkc9Ozs9R0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0cBHCcnMyYzPSYmPUc9Mj1HR0dEREdHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR//dAAQAAf/uAA5BZG9iZQBkwAAAAAH/wAARCAABAAEDACIAAREBAhEB/8QASwABAQAAAAAAAAAAAAAAAAAAAAYBAQAAAAAAAAAAAAAAAAAAAAAQAQAAAAAAAAAAAAAAAAAAAAARAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwAAARECEQA/AJgAH//Z")
                .when()
                .post("https://api.spotify.com/v1/playlists/0WioKBo6Sf6JLGz42Pr4Fv/images");
        res.prettyPrint();
        //  res.then().statusCode(200);
    }

    @Test(priority = 68)
 
    //exmaple 3na8lYc2MmdGtCCz5cs4KM    4w2p5chl38Mp35dAubmjzX-og
    public void   GetEpisode(){
        String episodeid = "512ojhOuo1ktJprKbVcKyQ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes/2EtOmHpIHwfRefGGZbutMT");
        res.prettyPrint();
        episodeid=res.path("id");
        System.out.println("True");
        res.then().statusCode(200);
    }

    @Test(priority = 69)
    public void   GetSeveralEpisodes(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 70)
    public void   GetUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 71)
    public void  SaveEpisodesforCurrentUser(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/episodes?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 72)
    public void   CheckUsersSavedEpisodes(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 73)
    public void  RemoveUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/episodes?ids=3na8lYc2MmdGtCCz5cs4KM");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 74)
    public void  TransferPlayback(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"device_ids\": [\n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority = 75)
    public void   GetCurrentlyPlayingTrack() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 76)
    public void   GetAvailableDevices() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 77)
    public void   StartResumePlayback() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                        "    \"offset\": {\n" +
                        "        \"position\": 5\n" +
                        "    },\n" +
                        "    \"position_ms\": 0\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority = 78)
    public void   GettheUsersQueue() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority = 79)
    public void   GetRecentlyPlayedTracks() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");
        res.prettyPrint();
        res.then().statusCode(200);
    }


}
