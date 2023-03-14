package com.example.subway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Data
@Entity(name = "site_relationship")
@NoArgsConstructor
@AllArgsConstructor
public class SiteRelationShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fromId;
    @Column
    private int toId;
    @Column
    private String name;
    @Column
    private int subwayId;
    @Column
    private int distance;

    public SiteRelationShip(Integer fromId,Integer toId,Integer subwayId){
        this.name="";
        this.distance=1;
        this.fromId = fromId;
        this.toId = toId;
        this.subwayId = subwayId;
    }

}
