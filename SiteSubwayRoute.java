package com.example.subway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Data
@Entity(name = "site_subwayroute")
@NoArgsConstructor
@AllArgsConstructor
public class SiteSubwayRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int siteId;

    @Column
    private int subwayId;

    public SiteSubwayRoute(Integer siteId,Integer subwayId){

        this.siteId = siteId;
        this.subwayId = subwayId;
    }

}
