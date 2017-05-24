package de.pandigo.repositories;

import org.springframework.data.repository.CrudRepository;

import de.pandigo.domain.MountainEntity;

public interface MountainRepository extends CrudRepository<MountainEntity, Long> {

	/*
	 * Because for every objects it would very similar to that:
	 *
	 * getAllMountains()
	 * getMountain(Long id)
	 * updateMountain(Mountain m)
	 * deleteMountain(Long id)
	 *
	 * The Spring data JPA can guess that and creates the implementation for us.
	 */

}
