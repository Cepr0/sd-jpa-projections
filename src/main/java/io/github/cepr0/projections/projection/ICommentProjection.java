package io.github.cepr0.projections.projection;

import io.github.cepr0.projections.model.User;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
public interface ICommentProjection {
	
	@Value("#{target.text}")
	String getText();
	
	@Value("#{target.user}")
	User getUser();
	
	@Value("#{target.user.profile.data}")
	String getUserData();
}
