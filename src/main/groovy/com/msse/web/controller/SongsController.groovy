package com.msse.web.controller

import com.msse.web.domain.Release
import com.msse.web.domain.Songs
import com.msse.web.repository.ArtistRepository
import com.msse.web.repository.ReleaseRepository
import com.msse.web.repository.SongsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import java.nio.file.Path

/**
 * Created by z001hk8 on 3/9/17.
 */

@RestController
class SongsController {

    @Autowired
    SongsRepository songsRepository

    @Autowired
    ReleaseRepository releaseRepository

    @Autowired
    ArtistRepository artistRepository

    @PostMapping("/song")
    Songs createSongReleaseArtist(@RequestBody Songs song) {
        artistRepository.save(song.release.artist)
        releaseRepository.save(song.release)
        return songsRepository.save(song)
    }

    @PostMapping("/song/releaseId/{releaseId}")
    Release addSongToRelease(@RequestBody Songs song, @PathVariable Long releaseId) {

        song = songsRepository.save(song)
        def release = releaseRepository.findOne(releaseId)
        List<Songs> songs = new ArrayList<>();
        songs.add(song)

        release.setSongs(songs)
        System.out.println("&&&&&&&& " + release.songs)
        try {
            release = releaseRepository.save(release)
             System.out.println("&&&&&&&& " + release.songs)
        } catch(Exception e) {
            System.out.println("Error saving release to repository: " + e.getMessage())
        }
        System.out.println("&&&&&&&& " + release.getSongs())
        return release
    }

    @GetMapping("/song/title/{songTitle}/artist/{artistName}")
    Songs getSongByTitleAndArtistName(@PathVariable songTitle, @PathVariable artistName) {
        return songsRepository.findSongBasedOnTitleAndArtistName(songTitle, artistName)
    }
}
