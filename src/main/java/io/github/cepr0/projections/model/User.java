package io.github.cepr0.projections.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Entity
public class User extends BaseEntity {
	
	@NaturalId
	private String name;
	
	@OneToOne
	private Profile profile;
}
