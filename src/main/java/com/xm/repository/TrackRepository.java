package com.xm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.xm.model.Track;

public interface TrackRepository extends CrudRepository<Track, Long> {

	public List<Track> findAllByOrderByIdDesc();
}
