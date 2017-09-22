package io.github.cepr0.projections.repo;

import io.github.cepr0.projections.model.Comment;
import io.github.cepr0.projections.projection.CommentProjection;
import io.github.cepr0.projections.projection.ICommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	<T> Optional<T> findByUserName(String name, Class<T> type);
	Optional<CommentProjection> findByUserName(String name);
	Optional<ICommentProjection> getByUserName(String name);
	Optional<Comment> findCommentByUserName(String name);
}
