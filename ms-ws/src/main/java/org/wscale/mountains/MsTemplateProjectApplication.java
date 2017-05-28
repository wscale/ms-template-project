package org.wscale.mountains;

import org.springframework.context.annotation.ComponentScan;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.service.MountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


// We want the rule that there should be no main files, this is the only exception.
// That's why we turn checkstyle off here and keep the rule.

//CHECKSTYLE:OFF
@SpringBootApplication
@ComponentScan("org.wscale") // TODO I dont know what to think about this.
public class MsTemplateProjectApplication {

	@Autowired
	private MountainService mountainService;

	public static void main(String[] args) {
		SpringApplication.run(MsTemplateProjectApplication.class, args);
	}

	/**
	 * This method is just for test purpose! Because our H2 database is cleared every restart we need to add some data
	 * so you don't have to enter test data every time you restart the application. This can be removed as soon as you
	 * connect a persistent database to this application.
	 * @param apx - Application context.
	 * @return - CommandLineRunner.
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext apx) {
		return args -> {

			final MountainEntity montblanc = new MountainEntity(
					"Mont Blanc", 4200, 1786,
					new String[]{"Jacques Balmat", "Michel-Gabriel Paccard"}, LocalDate.now());
			montblanc.setMountainId(0L);
			mountainService.addMountain(montblanc);

			final MountainEntity mounteverest = new MountainEntity(
					"Mount Everest", 8848, 1953,
					new String[]{"Edmund Hillary", "Tenzing Norgay"}, LocalDate.now());
			mounteverest.setMountainId(0L);
			mountainService.addMountain(mounteverest);

			final MountainEntity lhotse = new MountainEntity(
					"Lhotse", 8516, 1956,
					new String[]{"Ernst Reiss", "Fritz Luchsinger"}, LocalDate.now());
			lhotse.setMountainId(0L);
			mountainService.addMountain(lhotse);

			final MountainEntity chopolu = new MountainEntity(
					"Cho Polu", 6735, 1984,
					new String[]{"Nil Bohigas"}, LocalDate.now());
			chopolu.setMountainId(0L);
			mountainService.addMountain(chopolu);
		};
	}
}
//CHECKSTYLE:ON