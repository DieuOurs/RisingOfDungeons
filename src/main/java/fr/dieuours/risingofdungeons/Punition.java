package fr.dieuours.risingofdungeons;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type Punition.
 */
@Entity
public class Punition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID punisher;

    @ManyToOne
    private Account punished;
    private LocalDateTime startDate, endDate;
    private String reason, ip;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets punisher.
     *
     * @return the punisher
     */
    public UUID getPunisher() {
        return punisher;
    }

    /**
     * Sets punisher.
     *
     * @param punisher the punisher
     * @return the punisher
     */
    public Punition setPunisher(UUID punisher) {
        this.punisher = punisher;
        return this;
    }

    /**
     * Gets punished.
     *
     * @return the punished
     */
    public Account getPunished() {
        return punished;
    }

    /**
     * Sets punished.
     *
     * @param punished the punished
     * @return the punished
     */
    public Punition setPunished(Account punished) {
        this.punished = punished;
        return this;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     * @return the start date
     */
    public Punition setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     * @return the end date
     */
    public Punition setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Gets reason.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets reason.
     *
     * @param reason the reason
     * @return the reason
     */
    public Punition setReason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     * @return the ip
     */
    public Punition setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
