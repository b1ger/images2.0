package org.ontario.images.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@ToString
@MappedSuperclass
public class BaseUser implements Serializable {

    private static final long serialVersionUID = -3988499137919577054L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    public BaseUser() {
        super();
    }

    public BaseUser(Long id) {
        this.setId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final BaseUser baseUser = (BaseUser) obj;
        return Objects.equals(this.getId(), baseUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
