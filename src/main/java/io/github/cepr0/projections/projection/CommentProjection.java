package io.github.cepr0.projections.projection;

import io.github.cepr0.projections.model.User;
import lombok.Data;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@Data
public class CommentProjection {

	// @Value("#{target.text}")
	// private final String theText; -> throw PropertyReferenceException: No property theText found for type Comment!
	private final String text;
	
	// @Value("#{target.theUser}")
	// private final User theUser; -> throw PropertyReferenceException: No property user found for type Comment!
	private final User user;
	
	// @Value("#{target.theUser.profile.data}")
	// private final String theUserProfileData; -> throw PropertyReferenceException: No property theUser found for type Comment!
	private final String userProfileData;
}
