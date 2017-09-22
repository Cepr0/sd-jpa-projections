package io.github.cepr0.projections.projection;

import io.github.cepr0.projections.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@Data
public class CommentProjection implements ICommentProjection {

	@Value("#{target.text}")
	private final String text;
	
	@Value("#{target.user}")
	private final User user;
	
	@Value("#{target.user.profile.data}")
	private String userData;
}
