package com.example.subway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Data
@Entity(name = "site_info")
@NoArgsConstructor
@AllArgsConstructor
public class SiteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String Abstract;

    public SiteInfo(String name,String Abstract){
        this.name = name;
        this.Abstract = Abstract;
    }

}
