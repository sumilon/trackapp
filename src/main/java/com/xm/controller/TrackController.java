package com.xm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.Track;
import com.xm.repository.TrackRepository;

@RestController
public class TrackController {

	@Autowired
	TrackRepository dao;

	@GetMapping("/getTrack")
	public List<Track> getTrack() {

		return dao.findAllByOrderByIdDesc();
	}

	@PostMapping("/saveTrack")
	public String saveTrack(@RequestBody Track track) {

		try {

			Track tt = track;
			tt.setTrackDate(new Date());
			dao.save(tt);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
}
