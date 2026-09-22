package tuckersoft.autotests.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "reality_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "decision_id", nullable = false)
    private Decision decision;

    @Column(nullable = false)
    private String recipientEmail;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String logStatus; // SENT | FAILED

    @Column(columnDefinition = "TEXT")
    private String errorMessage; // nullable

    private Instant sentAt; // nullable, solo si logStatus = SENT

    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}