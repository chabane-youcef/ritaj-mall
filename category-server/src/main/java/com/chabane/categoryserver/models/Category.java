package com.chabane.categoryserver.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String image;
    private String description;
}
