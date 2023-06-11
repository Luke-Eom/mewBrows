package com.book.mew;

import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.userFeign.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MewApplicationTests {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate rt;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ScheduleRepository scheduleRepo;

//	@Test
//	public void testInsertSchedule() {
//
//		User user = new User(1L,"123","luke","123@456.com", LoginType.KAKAO);
//		userRepo.save(user);
//
//		// Create the request payload
//		ScheduleRequest scheduleRequest = new ScheduleRequest("user123", SurgeryTypes.SHAPE1, LocalDateTime.now(), Status.CONFIRM_WAIT);
//
//		// Create the HTTP request entity
//		HttpEntity<ScheduleRequest> requestEntity = new HttpEntity<>(scheduleRequest);
//
//		// Send the HTTP request to the endpoint
//		ResponseEntity<ScheduleRegisterResponse> responseEntity = restTemplate.exchange(
//				"http://localhost:" + port + "/api/schedule", // Replace with your actual endpoint URL
//				HttpMethod.POST,
//				requestEntity,
//				ScheduleRegisterResponse.class
//		);
//
//		// Verify the HTTP response and assertions
//		assertEquals(200, responseEntity.getStatusCodeValue());
//
//		ScheduleRegisterResponse response = responseEntity.getBody();
//		assertEquals(1L, response.getId().longValue());
//
//		// Verify the interactions with the repositories
//		verify(userRepo, times(1)).findByUserId("user123");
//		verify(scheduleRepo, times(1)).save(any(Schedule.class));
//	}
//}


	@Test
	void contextLoads() {
	}

}
