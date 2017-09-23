package io.github.cepr0.projections;

import io.github.cepr0.projections.model.Comment;
import io.github.cepr0.projections.model.Profile;
import io.github.cepr0.projections.model.User;
import io.github.cepr0.projections.projection.CommentProjection;
import io.github.cepr0.projections.projection.ICommentProjection;
import io.github.cepr0.projections.repo.CommentRepo;
import io.github.cepr0.projections.repo.ProfileRepo;
import io.github.cepr0.projections.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ProjectionTests extends BaseTest {
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProfileRepo profileRepo;
	
	@Before
	public void setUp() throws Exception {
		List<Profile> profiles = profileRepo.save(asList(
				new Profile("profile1"),
				new Profile("profile2")
		));
		
		List<User> users = userRepo.save(asList(
				new User("user1", profiles.get(0)),
				new User("user2", profiles.get(1))
		));
		
		commentRepo.save(asList(
				new Comment("comment1", users.get(0)),
				new Comment("comment2", users.get(1))
		));
	}
	
	@Test
	public void classProjectionTest() throws Exception {
		Optional<CommentProjection> projectionOptional = commentRepo.findByUserName("user1");
		checkProjection(projectionOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	@Test
	public void ifaceProjectionTest() throws Exception {
		Optional<ICommentProjection> projectionOptional = commentRepo.getByUserName("user1");
		checkProjection(projectionOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	@Test
	public void dynamicClassProjectionTest() throws Exception {
		Optional<CommentProjection> projectionOptional = commentRepo.findByUserName("user1", CommentProjection.class);
		checkProjection(projectionOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	@Test
	public void dynamicIfaceProjectionTest() throws Exception {
		Optional<ICommentProjection> projectionOptional = commentRepo.findByUserName("user1", ICommentProjection.class);
		checkProjection(projectionOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	@Test
	public void dynamicEntityTest() throws Exception {
		Optional<Comment> commentOptional = commentRepo.findByUserName("user1", Comment.class);
		checkComment(commentOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	@Test
	public void dynamicTest() throws Exception {
		List<Comment> comments = commentRepo.findAllByTextLike("comment%", Comment.class);
		assertThat(comments).hasSize(2);
		
		List<CommentProjection> commentProjections = commentRepo.findAllByTextLike("comment%", CommentProjection.class);
		assertThat(commentProjections).hasSize(2);
		
		Comment comment = commentRepo.findByText("comment1", Comment.class);
		assertThat(comment).isNotNull();
		
		CommentProjection commentProjection = commentRepo.findByText("comment1", CommentProjection.class);
		assertThat(commentProjection).isNotNull();
	}
	
	@Test
	public void entityTest() throws Exception {
		Optional<Comment> commentOptional = commentRepo.findCommentByUserName("user1");
		checkComment(commentOptional.orElseThrow(() -> new Exception("Comment not found!")));
	}
	
	private void checkProjection(CommentProjection projection) {
		assertThat(projection.getText()).isEqualTo("comment1");
		assertThat(projection.getUser().getName()).isEqualTo("user1");
		assertThat(projection.getUserProfileData()).isEqualTo("profile1");
	}
	
	private void checkProjection(ICommentProjection projection) {
		assertThat(projection.getText()).isEqualTo("comment1");
		assertThat(projection.getUser().getName()).isEqualTo("user1");
		assertThat(projection.getUserData()).isEqualTo("profile1");
	}
	
	private void checkComment(Comment comment) {
		assertThat(comment.getText()).isEqualTo("comment1");
		assertThat(comment.getUser().getName()).isEqualTo("user1");
	}
}