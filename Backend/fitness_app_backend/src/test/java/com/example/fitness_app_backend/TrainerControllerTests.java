package com.example.fitness_app_backend;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TrainerControllerTests {

	@InjectMocks
	TrainerController trainerController;

	@Mock
	TrainerRepository trainerRepoMock;


	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Nested
	class CreateTrainerTests {
		@Test
		public void testCreateTrainerFailure() {
			String result = trainerController.createTrainer(null);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}

		@Test
		public void testCreateTrainerSuccess() {
			Trainer trainer = new Trainer();
			String result = trainerController.createTrainer(trainer);
			verify(trainerRepoMock).save(trainer);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"success\"}");
		}
	}
	
	
	@Nested
	class GetTrainerTests {
		@Test
		public void testGetTrainerSuccess() {
			Trainer trainerBob = new Trainer("Bob", "Password");
			trainerBob.setId(10);
			doReturn(1L).when(trainerRepoMock).count();
			doReturn(true).when(trainerRepoMock).existsById(1);
			doReturn(trainerBob).when(trainerRepoMock).findById(1);
			doReturn(trainerBob).when(trainerRepoMock).getOne(1);
			String result = trainerController.getTrainer(trainerBob);
			Assertions.assertThat(result).isEqualTo("{ \"id\" : " + 10 +" }");
		}

		@Test
		public void testGetTrainerWrongTrainer() {
			Trainer trainerBob = new Trainer("Bob", "Password");
			trainerBob.setId(10);
			Trainer trainerBobby = new Trainer("Bobby", "Password");
			doReturn(1L).when(trainerRepoMock).count();
			doReturn(true).when(trainerRepoMock).existsById(1);
			doReturn(trainerBobby).when(trainerRepoMock).findById(1);
			doReturn(trainerBob).when(trainerRepoMock).getOne(1);
			String result = trainerController.getTrainer(trainerBob);
			Assertions.assertThat(result).isEqualTo("There is no trainer with the name Bob");
		}
	}
	
	@Nested
	class UpdateTrainerTests {
		@Test
		public void testUpdateTrainerNullCase() {
			doReturn(null).when(trainerRepoMock).findById(0);
			Trainer result = trainerController.updateTrainer(0, null);
			Assertions.assertThat(result).isEqualTo(null);
		}

		@Test
		public void testUpdateTrainerNotNullCase() {
			doReturn(new Trainer()).when(trainerRepoMock).findById(0);
			Trainer request = new Trainer();
			doReturn(request).when(trainerRepoMock).findById(0);
			Trainer result = trainerController.updateTrainer(0, request);
			verify(trainerRepoMock).save(request);
			Assertions.assertThat(result).isEqualTo(request);
		}

	}
	
	
	
}
