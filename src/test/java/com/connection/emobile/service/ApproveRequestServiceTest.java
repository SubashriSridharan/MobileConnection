package com.connection.emobile.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.connection.emobile.dto.ApproveRequestDto;
import com.connection.emobile.dto.ApproveResponseDto;
import com.connection.emobile.entity.Track;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.repository.TrackRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ApproveRequestServiceTest {

	@InjectMocks
	private ApproveRequestServiceImpl approveRequestService;
	@Mock
	private TrackRepository trackRepository;
	ApproveResponseDto approveResponseDto;
	ApproveRequestDto approveRequestDto;
	Track track;

	@BeforeEach
	public void setUp() {

		track = new Track();
		track.setStatus("In-progress");
		track.setUserId(1);
		track.setPlanId(1);
		track.setApproverComments("accepted");
		track.setAdminId(1);

		approveRequestDto = new ApproveRequestDto();
		approveRequestDto.setStatus("Approved");
		approveRequestDto.setApproverComments("Accepted");

		approveResponseDto = new ApproveResponseDto();
		approveResponseDto.setMessage("Success");
		approveResponseDto.setStatusCode("200");

	}

	@Test

	public void approveRequestByAdminTest() throws InvalidTrackIdException {

		Mockito.when(trackRepository.findById(1)).thenReturn(Optional.of(track));

		ApproveResponseDto result = approveRequestService.approveRequestByAdmin(approveRequestDto, 1);
		assertNotNull(result);

		assertEquals(approveResponseDto.getMessage(), result.getMessage());
		assertEquals(approveResponseDto.getStatusCode(), result.getStatusCode());

	}

	@Test

	public void approveRequestByAdminForException() throws InvalidTrackIdException {

		Assertions.assertThrows(InvalidTrackIdException.class, () -> {
			approveRequestService.approveRequestByAdmin(approveRequestDto, 2);
		});
	}

}
