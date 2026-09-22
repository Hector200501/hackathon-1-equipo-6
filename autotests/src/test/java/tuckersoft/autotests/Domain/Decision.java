package tuckersoft.autotests.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playthrough_id", nullable = false)
    private Playthrough playthrough;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "node_id", nullable = false)
    private StoryNode node; // nodo de ORIGEN (currentNode al momento de decidir)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String rawInput;

    private String branchType;     // OBEDIENCIA | REBELDIA | SOSPECHA | RUPTURA_CUARTA_PARED | ENTRADA_CORRUPTA

    @Column(nullable = false)
    private String impactLevel;    // LEVE | MODERADO | GRAVE | CRITICO (viene en el request)

    private String handlerUnit;    // derivado del branchType
    private String outcomeCode;    // derivado del branchType

    private String resolvedNodeCode; // nullable

    @Column(nullable = false)
    private String status; // REGISTRADA | PROCESANDO | ESTABILIZADA | ERROR

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RealityLog> realityLogs = new ArrayList<>();
}
