package tuckersoft.autotests.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "story_nodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String nodeCode;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sceneText;

    @Column(nullable = false)
    private Integer branchCapacity;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentBranches = 0;

    // Strings, NO llaves foraneas: se resuelven al decidir, no al crear.
    private String primaryBranchCode;

    private String glitchBranchCode;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "currentNode")
    @Builder.Default
    private List<Playthrough> playthroughs = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "node")
    @Builder.Default
    private List<Decision> decisions = new ArrayList<>();
}
