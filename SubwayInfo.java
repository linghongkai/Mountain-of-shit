package com.example.subway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Data
@Entity(name = "subway_info")
@NoArgsConstructor
@AllArgsConstructor
public class SubwayInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int siteSize;

    @Column
    private int startSiteId;
    @Column
    private int endSiteId;

}
