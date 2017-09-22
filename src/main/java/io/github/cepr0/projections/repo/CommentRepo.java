package io.github.cepr0.projections.repo;

import io.github.cepr0.projections.model.Comment;
import io.github.cepr0.projections.projection.CommentProjection;
import io.github.cepr0.projections.projection.ICommentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	// doesn't work - throw NoSuchElementException
	<T> Optional<T> findByUserName(String name, Class<T> type);
	<T> Page<T> getAllByTextLike(Pageable pageable, String text, Class<T> type);

	// work
	<T> T findByText(String text, Class<T> type);
	<T> List<T> findAllByTextLike(String text, Class<T> type);
	
	Optional<CommentProjection> findByUserName(String name);
	Optional<ICommentProjection> getByUserName(String name);
	Optional<Comment> findCommentByUserName(String name);
}
