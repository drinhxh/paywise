package com.paywise.paywise;

import com.paywise.paywise.constants.Constants;
import com.paywise.paywise.dao.FundTransferDAO;
import com.paywise.paywise.dao.RolesDAO;
import com.paywise.paywise.dao.UserDAO;
import com.paywise.paywise.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PaywiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaywiseApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
						"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
						"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
						"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


	@Bean
	public CommandLineRunner commandLineRunner(UserDAO userDAO,
																						 FundTransferDAO fundTransferDAO,
																						 RolesDAO rolesDAO){
		return runner -> {
//			createUser(userDAO, rolesDAO);
//			findUser(userDAO);
//			deleteUser(userDAO);
//			printDetails(userDAO);
//			transferFunds(userDAO, fundTransferDAO);
//			getSenderTransferFund(userDAO, fundTransferDAO);
//			queryForFundTransfers(fundTransferDAO);
//			updateUser(userDAO);
//			addUserRole(userDAO, rolesDAO);
//			findUserByUsername(userDAO);
		};
	}

	public void findUserByUsername(UserDAO userDAO){
		String username = "fake thug";
		User user = userDAO.findUserByUsername(username);
	}

	private void createUser(UserDAO userDAO, RolesDAO rolesDAO) {
		User user = new User("Ja", "Morant", "fake thug", "test123");

//		String encodedPassword = Constants.BCRYPT + userDAO.encodeBCryptEncode(user.getPassword());

		BankAccount bankAccount = new BankAccount("AT52 8888", 700.00);
		Card card = new Card("123 1234", "jamorant", "01/24", "111");
		user.setBankAccount(bankAccount);
		user.setCard(card);
		user.setPassword("test123");
//		System.out.println("ENCODED PASSWORD: " + encodedPassword);

		userDAO.saveUser(user);
		System.out.println("Created user with id -> " + user.getId());
//		addUserRole(userDAO, rolesDAO, user.getId());

		User user2 = new User("drin", "baba", "baba", "test123");
		BankAccount bankAccount2 = new BankAccount("AT52 2222", 800.00);
		Card card2 = new Card("123 1234", "baba", "02/24", "222");

		user2.setBankAccount(bankAccount2);
		user2.setCard(card2);
		userDAO.saveUser(user2);
		System.out.println("Created user with id -> " + user2.getId());
//		addUserRole(userDAO, rolesDAO, user2.getId());

		User user3 = new User("lebron", "james", "kingjames", "test123");
		BankAccount bankAccount3 = new BankAccount("AT52 3333", 700.00);
		Card card3 = new Card("123 1234", "james", "03/24", "333");
		user3.setBankAccount(bankAccount3);
		user3.setCard(card3);
		userDAO.saveUser(user3);
		System.out.println("Created user with id -> " + user3.getId());

		addUserAdminRole(userDAO, rolesDAO, user3.getId());
//		addUserRole(userDAO, rolesDAO, user3.getId());
	}

	public void addUserAdminRole(UserDAO userDAO, RolesDAO rolesDAO, int userId){
		User user = userDAO.findUserById(userId);

		Roles role = rolesDAO.addRole(userId, Constants.ADMIN_USER);
		if(role == null){
			System.out.println("NULL IN COMMANDLINERUNNER");
		}

		user.addRole(role);
	}

	private void addUserRole(UserDAO userDAO, RolesDAO rolesDAO, int userId) {
		User user = userDAO.findUserById(userId);

		Roles role = rolesDAO.addRole(userId, Constants.SIMPLE_USER);
		if(role == null){
			System.out.println("NULL IN COMMANDLINERUNNER");
		}
		System.out.println("Role: " + role);
		System.out.println("Adding role to user with id: " + userId);
		user.addRole(role);
		System.out.println("Added role");
	}

	public void transferFunds(UserDAO userDAO, FundTransferDAO fundTransferDAO){
		int senderId = 2;
		int receiverId = 1;
		double amount = 200.00;
		User sender = userDAO.findUserById(senderId);
		User receiver = userDAO.findUserById(receiverId);

		System.out.println("Transferring the amount of: " + amount);
		FundTransfer fundTransfer = fundTransferDAO.transferFunds(senderId, receiverId, amount);
		System.out.println("Transfer completed!");

		System.out.println("Setting up SENDER transfer...");
		sender.addSenderFundTransfer(fundTransfer);
		System.out.println("SENDER transfer done!");

		System.out.println("Setting up RECEIVER transfer...");
		receiver.addReceiverFundTransfer(fundTransfer);
		System.out.println("RECEIVER transfer done!");
	}

	private void updateUser(UserDAO userDAO) {
		int id = 1;
		User user = userDAO.findUserById(id);
		user.setUsername("drama queen");
		userDAO.updateUser(user);
	}

	public void queryForFundTransfers(FundTransferDAO fundTransferDAO){
		List<FundTransfer> funds = fundTransferDAO.findAllFundTransfers();

		for(FundTransfer ft : funds){
			System.out.println(ft.getDateTime());
		}
	}

	private void getSenderTransferFund(UserDAO userDAO, FundTransferDAO fundTransferDAO) {
		int userId = 1;
		User user = userDAO.findUserById(userId);
		List<FundTransfer> senderFunds = user.getSenderFundTransfers();
		List<FundTransfer> receiverFunds = user.getReceiverFundTransfers();
		System.out.println("++++++++++SENDER++++++++++");
		for (FundTransfer ft : senderFunds) {
			System.out.println("ID: "  + ft.getId());
			System.out.println("Amount: " + ft.getAmount());
			System.out.println("Source Account Number: " + ft.getSourceAccountNumber());
			System.out.println("Destination Account Number" + ft.getDestinationAccountNumber());
			System.out.println("Date and Time of Transfer: " + ft.getDateTime());
		}

		System.out.println("++++++++++RECEIVER++++++++++");
		if(receiverFunds.size() == 0){
			System.out.println("EMPTY RECEIVER FUNDS LIST");
		}
		for (FundTransfer ft : receiverFunds) {
			System.out.println("ID: "  + ft.getId());
			System.out.println("Amount: " + ft.getAmount());
			System.out.println("Source Account Number: " + ft.getSourceAccountNumber());
			System.out.println("Destination Account Number: " + ft.getDestinationAccountNumber());
			System.out.println("Date and Time of Transfer: " + ft.getDateTime());
		}
	}

	private void printDetails(UserDAO userDAO) {
		int id = 3;

		User user = userDAO.findUserById(id);
		System.out.println(user.getUsername());
		System.out.println(user.getRoles());
	}

	private void deleteUser(UserDAO userDAO) {
		int id = 5;
		System.out.println("Deleting user with id " + id);
		userDAO.deleteUserById(id);
		System.out.println("Deleted!");
	}

	private void findUser(UserDAO userDAO) {
		Integer id = 2;
		User user = userDAO.findUserById(id);
		System.out.println("USER: " + user);
	}
}




















