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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
	public void setUp() {
		List<Profile> profiles = profileRepo.saveAll(asList(
				new Profile("profile1"),
				new Profile("profile2")
		));
		
		List<User> users = userRepo.saveAll(asList(
				new User("user1", profiles.get(0)),
				new User("user2", profiles.get(1))
		));
		
		commentRepo.saveAll(asList(
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
	public void dynamicOptionalTest() {
		Optional<Comment> commentOptional = commentRepo.findByUserName("user1", Comment.class);
		assertThat(commentOptional).isNotNull();
	}
	
	@Test
	public void dynamicPagedTest() {
		Page<Comment> commentPage = commentRepo.getAllByTextLike(new PageRequest(0, 10), "comment%", Comment.class);
		assertThat(commentPage).isNotNull();
	}
	
	@Test
	public void dynamicStreamTest() {
		Stream<Comment> commentStream = commentRepo.findStreamByTextLike("comment%", Comment.class);
		assertThat(commentStream).isNotNull();
	}
	
	@Test
	public void dynamicTest() {
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