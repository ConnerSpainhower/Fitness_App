package com.example.fitness_app_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminTests {
	
	@InjectMocks
	AdminController adminController;
	
	@Mock
	AdminRepository adminRepo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateAdmin() {
		Admin admin = new Admin("Sean", "password123");
		admin.setId(1);
		
		when(adminRepo.findById(1)).thenReturn(admin);
		
		assertEquals("{\"message\":\"success\"}", adminController.createAdmin(admin));
		verify(adminRepo).save(admin);
		
	}
	
	@Test
	public void testCreateAdminFailure() {
		String output = adminController.createAdmin(null);
		assertEquals("{\"message\":\"failure\"}", output);
	}
	
	@Test
	public void testGetAllAdmins() {
		List<Admin> adminList = adminController.getAllAdmins();
		assertEquals(adminList.size(), 0);
	}
	
	@Test
	public void testGetAdmin() {
		Admin admin = new Admin("Sean", "password123");
		admin.setId(1);
		doReturn(1L).when(adminRepo).count();
		doReturn(true).when(adminRepo).existsById(1);
		doReturn(admin).when(adminRepo).findById(1);
		doReturn(admin).when(adminRepo).getOne(1);
		String output = adminController.getAdmin(admin);
		assertEquals("{ \"id\" : " + 1 +" }", output);
	}
	
	@Test
	public void testGetAdminFailure() {
		Admin admin = new Admin("Sean", "password123");
		admin.setId(1);
		Admin admin2 = new Admin("Michael", "password123");
		doReturn(1L).when(adminRepo).count();
		doReturn(true).when(adminRepo).existsById(1);
		doReturn(admin2).when(adminRepo).findById(1);
		doReturn(admin).when(adminRepo).getOne(1);
		String output = adminController.getAdmin(admin);
		assertEquals("There is no admin with the name Sean", output);
	}
	
	@Test
	public void testGetAdminById() {
		when(adminRepo.findById(1)).thenReturn(new Admin("Sean", "password123"));
		Admin admin = adminController.getAdminById(1);
		verify(adminRepo).findById(1);
		assertEquals("Sean", admin.getName());
		assertEquals("password123", admin.getPassword());
	}
	
	@Test
	public void testDeleteAdmin() {
		Admin admin = new Admin("Sean", "password123");
		admin.setId(1);
		when(adminRepo.findById(1)).thenReturn(admin);
		adminController.createAdmin(admin);
		assertEquals("{\"message\":\"success\"}", adminController.deleteAdmin(1));
		verify(adminRepo).deleteById(1);
	}

	@Test
	public void testUpdateAdmin() {
		doReturn(new Admin()).when(adminRepo).findById(0);
		Admin admin = new Admin();
		doReturn(admin).when(adminRepo).findById(0);
		Admin output = adminController.updateAdmin(0, admin);
		verify(adminRepo).save(admin);
		assertEquals(admin, output);
	}
	
	@Test
	public void testUpdateNullAdmin() {
		doReturn(null).when(adminRepo).findById(0);
		Admin admin = adminController.updateAdmin(0, null);
		assertEquals(null, admin);
	}
}
