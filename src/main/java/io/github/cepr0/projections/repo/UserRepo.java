package io.github.cepr0.projections.repo;

import io.github.cepr0.projections.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
public interface UserRepo extends JpaRepository<User, Integer> {
}
