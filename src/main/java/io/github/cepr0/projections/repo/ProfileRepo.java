package io.github.cepr0.projections.repo;

import io.github.cepr0.projections.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
}
