package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InquiryOutlet.
 */
@Entity
@Table(name = "T_INQUIRY_OUTLET")
public class InquiryOutlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "schedule")
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InquiryOutlet inquiryOutlet = (InquiryOutlet) o;

        if ( ! Objects.equals(id, inquiryOutlet.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
