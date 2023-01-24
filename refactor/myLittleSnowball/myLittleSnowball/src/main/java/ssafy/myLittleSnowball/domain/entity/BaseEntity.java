package ssafy.myLittleSnowball.domain.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    /*
    매핑되는 컬럼에대한 특성을 지정해주는데, created_at 컬럼은 최초에 값을 insert 할 때
    들어가는 값이 쭉 유지가 되어야 하므로 Entity가 Update 될 때
    created_at이 update가 되지 않게하기위해 작성을 해준다.
     */
    @Column(updatable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        created_at = now;
        updated_at = now;
    }
    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }
}
