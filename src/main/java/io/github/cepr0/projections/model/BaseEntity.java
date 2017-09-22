package io.github.cepr0.projections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@MappedSuperclass
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class BaseEntity implements Identifiable<Integer> {
	@Id
	@GeneratedValue
	private Integer id;
}
