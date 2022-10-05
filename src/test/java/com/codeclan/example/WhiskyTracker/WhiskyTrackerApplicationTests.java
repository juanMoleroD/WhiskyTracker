package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskysByYear() {
		List<Whisky> found = whiskyRepository.findByYear(1994);
		assertEquals(0, found.size());
		found = whiskyRepository.findByYear(2014);
		assertEquals(4, found.size());
	}

	@Test
	public void canGetDistilleryFromRegion() {
		List<Distillery> found = distilleryRepository.findByRegion("Highland");
		System.out.println(found.size());
		assertEquals(3 ,found.size());
	}

	@Test
	public void canGetWhiskysFromDistilleryAndAge() {
		List<Whisky> found = whiskyRepository.findByDistilleryNameAndAge("Glendronach", 15);
		assertEquals(1, found.size());
	}

	@Test
	public void canGetAllWhiskysFromRegion() {
		List<Whisky> found = whiskyRepository.findByDistilleryRegion("Highland");
		assertEquals(7, found.size());
	}

	@Test
	public void canGetDistilleriesThatHaveWhiskiesByAge() {
		List<Distillery> found = distilleryRepository.findByWhiskiesAge(12);
		assertEquals(6, found.size());
	}
}
