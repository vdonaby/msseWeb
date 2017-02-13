package com.msse.web.Repository

import com.msse.web.domain.Playlist
import org.springframework.data.repository.CrudRepository

/**
 * Created by z001hk8 on 2/9/17.
 */
interface PlaylistRepository extends CrudRepository<Playlist, Long> {

}