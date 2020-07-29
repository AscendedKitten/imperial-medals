package at.imperial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private Long id;
    private String[] medals;
    @Column("user_name")
    private String userName;
}
