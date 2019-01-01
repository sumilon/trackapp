package com.xm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xm.model.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {

	public List<Track> findAllByOrderByIdDesc();
	
	public List<Track> findByDeviceIdOrderByIdDesc(String deviceId);
	
	//public List<Track> findAllBy
}
