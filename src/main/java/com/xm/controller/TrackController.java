package com.xm.controller;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.EmailObj;
import com.xm.model.SmsObj;
import com.xm.model.Track;
import com.xm.repository.TrackRepository;
import com.xm.service.EmailService;
import com.xm.service.SMSService;

@RestController
@RequestMapping("/track")
public class TrackController {

	@Autowired
	private TrackRepository dao;

	@Autowired
	private EmailService service;

	@Autowired
	private SMSService smsService;

	@GetMapping("/getTrack")
	public List<Track> getTrack() {

		return dao.findAllByOrderByIdDesc();
	}

	@GetMapping("/getTrackByDeviceId/{deviceId}")
	public List<Track> getTrackByDeviceId(@PathVariable("deviceId") String deviceId) {

		System.out.println("deviceId : " + deviceId);

		return dao.findByDeviceIdOrderByIdDesc(deviceId);
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

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailObj email) throws MessagingException {

		return service.sendEmail(email);
	}

	@PostMapping("/sendSMS")
	public String sendSMS(@RequestBody SmsObj sms) {

		return smsService.sendCampaign("S5H06CM84RR9DPW69QAG1R3R8Y6TJH5D", "JSFKQA0D43NET6VZ", "stage", sms.getPhone(),
				sms.getMessage(), sms.getPhone());
	}

}
